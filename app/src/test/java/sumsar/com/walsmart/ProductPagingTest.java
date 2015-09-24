package sumsar.com.walsmart;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import sumsar.com.walsmart.mock.MockAPI;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenter;
import sumsar.com.walsmart.productlist.presenter.ProductListPresenterImpl;
import sumsar.com.walsmart.productlist.presenter.ProductListView;
import sumsar.com.walsmart.service.API;

/**
 * Created by rasmusgohs on 24/09/15.
 */
public class ProductPagingTest implements ProductListView {

    private final ProductListPresenter mPresenter = new ProductListPresenterImpl(this, MockAPI.getInstance());
    private boolean loadingShown;
    private boolean loadingHidden;

    @Test
    public void testPaging() {
        mPresenter.requestProductList();
    }

    @Override
    public void setProducts(List<Product> products) {
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);
        Assert.assertTrue(loadingShown);
        Assert.assertTrue(loadingHidden);
    }

    @Override
    public void onFailedToGetProducts() {

    }

    @Override
    public void showLoading() {
        loadingShown = true;
    }

    @Override
    public void hideLoading() {
        loadingHidden = true;
    }
}
