package sumsar.com.walsmart.productlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenter;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenterImpl;
import sumsar.com.walsmart.productlist.presenter.ProductListView;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListFragment extends Fragment implements ProductListView, ProductAdapter.OnProductClickListener {


    public static final String TAG = ProductListFragment.class.getSimpleName();

    @Bind(R.id.product_list_recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.product_list_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final ProductAdapter mProductAdapter = new ProductAdapter();

    private ProductListPresenter mPresenter = new ProductListPresenterImpl(this);


    public ProductListFragment() {
        mProductAdapter.setOnProductClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mProductAdapter);
        setScrollListener(mRecyclerView, layoutManager);
        mPresenter.requestProductList();
    }

    @Override
    public void setProducts(List<Product> products) {
        mProductAdapter.setProducts(products);

        int currentNoOfItems = mProductAdapter.getItemCount();
        mProductAdapter.notifyItemRangeInserted(currentNoOfItems, products.size() - currentNoOfItems);
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    public static ProductListFragment getInstance() {
        return new ProductListFragment();
    }

    @Override
    public void onClick(Product product) {
        mPresenter.selectProduct(product);
    }

    private void setScrollListener(final RecyclerView recyclerView, final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int visibleItemCount, totalItemCount, pastVisibleItems;
            boolean hitEnd;
            boolean loading;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                hitEnd = visibleItemCount + pastVisibleItems >= totalItemCount;
                if (hitEnd && !mSwipeRefreshLayout.isRefreshing()) {
                    MyLog.d(ProductListFragment.TAG, "Hit end");
                    mPresenter.requestProductList();
                }

            }
        });
    }
}
