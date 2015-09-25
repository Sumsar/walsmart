package sumsar.com.walsmart.productlist.presenter;

import java.util.List;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.API;
import sumsar.com.walsmart.service.ApiCallback;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListPresenterImpl implements ProductListPresenter {


    private final ProductListView          mProductListView;
    private final API                      mApiService;
    private       ApiCallback<ProductList> mApiCallback;
    private       int                      index;

    public ProductListPresenterImpl(ProductListView productListView, API apiService) {
        mProductListView = productListView;
        mApiService = apiService;
    }

    @Override
    public void requestNextProductList() {
        mProductListView.showLoading();
        mApiCallback = new ApiCallback<ProductList>() {
            @Override
            public void onSuccess(ProductList data) {
                final List<Product> products = data.getProducts();
                index += products.size();
                setProducts(products);
            }

            @Override
            public void onFailed(Throwable throwable) {
                mProductListView.hideLoading();
                mProductListView.onFailedToGetProducts();
            }
        };

        mApiService.getProductList(index, mApiCallback);

    }

    private void setProducts(final List<Product> mProducts) {
        mProductListView.hideLoading();
        mProductListView.setProducts(mProducts);

    }


    @Override
    public void onDestroy() {
        if (mApiCallback != null) {
            mApiCallback.cancel();
        }
    }

}
