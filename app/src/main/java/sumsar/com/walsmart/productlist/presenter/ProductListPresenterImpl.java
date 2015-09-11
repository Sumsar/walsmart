package sumsar.com.walsmart.productlist.presenter;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.ApiService;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListPresenterImpl implements ProductListPresenter {


    private final ProductListView                     mProductListView;
    private       ApiService.ApiCallback<ProductList> mApiCallback;

    public ProductListPresenterImpl(ProductListView productListView) {
        mProductListView = productListView;
    }

    @Override
    public void requestProductList() {
        mProductListView.showLoading();
        mApiCallback = new ApiService.ApiCallback<ProductList>() {
            @Override
            public void onSuccess(ProductList data) {
                mProductListView.setProductList(data);
            }

            @Override
            public void onFailed(Throwable throwable) {
                mProductListView.showError(throwable.getMessage());
            }
        };

        ApiService.getInstance().getProductList(0, 10, mApiCallback);

    }


    @Override
    public void selectProduct(Product product) {

    }

    @Override
    public void onDestroy() {
        if (mApiCallback != null) {
            mApiCallback.cancel();
        }
    }
}
