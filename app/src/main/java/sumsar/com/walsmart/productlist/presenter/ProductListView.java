package sumsar.com.walsmart.productlist.presenter;

import com.sumsar.ProductList;

import sumsar.com.walsmart.presenter.BaseView;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface ProductListView extends BaseView {

    void setProductList(ProductList productList);


}
