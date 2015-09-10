package sumsar.com.walsmart.productlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumsar.ProductList;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenter;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenterImpl;
import sumsar.com.walsmart.productlist.presenter.ProductListView;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListFragment extends Fragment implements ProductListView {


    public static final String TAG = ProductListFragment.class.getSimpleName();

    @Bind(R.id.product_list_recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.product_list_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final ProductAdapter mProductAdapter = new ProductAdapter();

    private ProductListPresenter mPresenter = new ProductListPresenterImpl(this);


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
        mRecyclerView.setAdapter(mProductAdapter);
        mPresenter.requestProductList();
    }

    @Override
    public void setProductList(ProductList productList) {
        mProductAdapter.setProducts(productList.getProducts());
        mProductAdapter.notifyDataSetChanged();
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
}
