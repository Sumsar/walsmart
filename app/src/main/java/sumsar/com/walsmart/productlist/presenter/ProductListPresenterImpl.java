package sumsar.com.walsmart.productlist.presenter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.mock.MockResponse;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.ApiService;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListPresenterImpl implements ProductListPresenter {


    private final ProductListView                     mProductListView;
    private final ApiService                          mApiService;
    private       ApiService.ApiCallback<ProductList> mApiCallback;
    private List<Product> mProducts = new ArrayList<>();

    public ProductListPresenterImpl(ProductListView productListView, ApiService apiService) {
        mProductListView = productListView;
        mApiService = apiService;
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

        mApiService.getProductList(mProducts.size(), mApiCallback);

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
