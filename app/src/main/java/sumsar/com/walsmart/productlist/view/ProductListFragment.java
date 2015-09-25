package sumsar.com.walsmart.productlist.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.mock.MockAPI;
import sumsar.com.walsmart.model.ObservableProductId;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenter;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenterImpl;
import sumsar.com.walsmart.productlist.presenter.ProductListView;
import sumsar.com.walsmart.service.ApiService;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListFragment extends Fragment implements ProductListView, OnProductSelectedListener {

    private static final String RECYCLER_POSITION       = "RECYCLER_POSITION";
    private static final String SELECTED_PRODUCT_ID_KEY = "SELECTED_PRODUCT_ID_KEY";

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private final ObservableProductId selectedProductId = new ObservableProductId();

    private final ProductAdapter mProductAdapter = new ProductAdapter(selectedProductId);

    private final ProductListPresenter mPresenter = new ProductListPresenterImpl(this, ApiService.getInstance());
    private OnProductSelectedListener mOnProductClickListener;


    public ProductListFragment() {
        mProductAdapter.setOnProductClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            restoreSelectedProduct(savedInstanceState);
        }
    }

    private void restoreSelectedProduct(Bundle savedInstanceState) {
        selectedProductId.setProductId(savedInstanceState.getString(SELECTED_PRODUCT_ID_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_list_fragment, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RECYCLER_POSITION, mRecyclerView.getLayoutManager().onSaveInstanceState());
        outState.putString(SELECTED_PRODUCT_ID_KEY, selectedProductId.getProductId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(RECYCLER_POSITION);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnProductClickListener = (OnProductSelectedListener) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_list_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.product_list_swipeRefreshLayout);
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mProductAdapter);
        setScrollListener(mRecyclerView, layoutManager);
        mPresenter.requestNextProductList();
    }

    @Override
    public void setProducts(List<Product> products) {
        mProductAdapter.setProducts(products);

        int currentNoOfItems = mProductAdapter.getItemCount();
        mProductAdapter.notifyItemRangeInserted(currentNoOfItems, products.size() - currentNoOfItems);
        selectedProductId.notifyChange();
    }


    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }


    private void setScrollListener(final RecyclerView recyclerView, final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int visibleItemCount, totalItemCount, pastVisibleItems;
            private boolean hitEnd;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                hitEnd = hasHitEnd();
                if (hitEnd && !isRefreshing()) {
                    MyLog.d(ProductListFragment.class.getSimpleName(), "Hit end");
                    mPresenter.requestNextProductList();
                }

            }

            private boolean hasHitEnd() {
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                return visibleItemCount + pastVisibleItems >= totalItemCount;
            }
        });
    }

    @Override
    public void onFailedToGetProducts() {
        Snackbar.make(getView(), R.string.error_failed_to_download_product, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onProductSelected(Product product, ImageView productImageView) {
        selectedProductId.setProductId(product.getProductId());
        mOnProductClickListener.onProductSelected(product, productImageView);
    }
}
