package sumsar.com.walsmart.productlist.presenter;

import java.util.List;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.presenter.BaseView;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface ProductListView extends BaseView {

    void setProducts(List<Product> products);


}
