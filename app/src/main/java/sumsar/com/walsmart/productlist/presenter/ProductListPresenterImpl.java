package sumsar.com.walsmart.productlist.presenter;

import com.sumsar.Product;
import com.sumsar.ProductList;

import retrofit.Callback;
import retrofit.Response;
import sumsar.com.walsmart.service.ApiService;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListPresenterImpl implements ProductListPresenter {


    private final ProductListView mProductListView;

    public ProductListPresenterImpl(ProductListView productListView) {
        mProductListView = productListView;
    }

    @Override
    public void requestProductList() {
        mProductListView.showLoading();
        ApiService.getInstance().getProductList(0, 10, new Callback<ProductList>() {
            @Override
            public void onResponse(Response<ProductList> response) {
                mProductListView.setProductList(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                MyLog.d(ProductListPresenterImpl.class.getSimpleName(), "Failed to get product list", t);
            }
        });


    }


    @Override
    public void selectProduct(Product product) {

    }
}
