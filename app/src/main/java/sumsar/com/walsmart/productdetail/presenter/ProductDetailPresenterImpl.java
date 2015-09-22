package sumsar.com.walsmart.productdetail.presenter;

import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public class ProductDetailPresenterImpl implements ProductDetailPresenter {


    private final ProductDetailView mProductDetailView;

    public ProductDetailPresenterImpl(ProductDetailView productDetailView) {
        mProductDetailView = productDetailView;
    }

    @Override
    public void requestProduct(String id) {
    }

    public void setProduct(Product product) {
        mProductDetailView.setProduct(product);
    }
}
