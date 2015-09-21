package sumsar.com.walsmart.productdetail.presenter;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.ApiService;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public class ProductDetailPagerPresenterImpl implements ProductDetailPagerPresenter {


    private static final int PRODUCT_PER_REQUEST = 5;
    private int                    mCurrentSelectedIndex;
    private ProductDetailPagerView mProductDetailPagerView;
    private List<Product> mProducts = new ArrayList<>();

    public ProductDetailPagerPresenterImpl(ProductDetailPagerView productDetailPagerView) {
        mProductDetailPagerView = productDetailPagerView;
    }

    @Override
    public void requestProductList(int currentSelectedIndex) {
        mCurrentSelectedIndex = currentSelectedIndex;
        int pageNumber = (int) Math.ceil(mCurrentSelectedIndex / PRODUCT_PER_REQUEST);

        ApiService.getInstance().getProductList(pageNumber, PRODUCT_PER_REQUEST, new ApiService.ApiCallback<ProductList>() {
            @Override
            public void onSuccess(ProductList productList) {
                setProducts(productList.getProducts());
            }

            @Override
            public void onFailed(Throwable t) {
                MyLog.e(ProductDetailPagerPresenterImpl.class.getSimpleName(), "Failed to get products", t);
            }
        });
    }


    public void setProducts(List<Product> products) {
        mProducts.addAll(products);
        mProductDetailPagerView.setProducts(mProducts);
    }
}
