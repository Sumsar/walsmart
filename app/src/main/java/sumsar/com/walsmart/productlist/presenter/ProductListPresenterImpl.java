package sumsar.com.walsmart.productlist.presenter;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.ApiService;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListPresenterImpl implements ProductListPresenter {


    private final ProductListView                     mProductListView;
    private       ApiService.ApiCallback<ProductList> mApiCallback;
    private List<Product> mProducts = new ArrayList<>();

    private static final int PRODUCTS_PER_REQUEST = 20;

    public ProductListPresenterImpl(ProductListView productListView) {
        mProductListView = productListView;
    }

    @Override
    public void requestProductList() {
        mProductListView.showLoading();
        mApiCallback = new ApiService.ApiCallback<ProductList>() {
            @Override
            public void onSuccess(ProductList data) {
                mProducts.addAll(data.getProducts());
                setProducts(mProducts);
            }

            @Override
            public void onFailed(Throwable throwable) {
                mProductListView.hideLoading();
                mProductListView.showError(throwable.getMessage());
            }
        };

        ApiService.getInstance().getProductList(mProducts.size(), PRODUCTS_PER_REQUEST, mApiCallback);

    }

    private void setProducts(final List<Product> mProducts) {
        mProductListView.hideLoading();
        mProductListView.setProducts(mProducts);

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
