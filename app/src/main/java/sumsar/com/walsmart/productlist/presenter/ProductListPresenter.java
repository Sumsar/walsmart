package sumsar.com.walsmart.productlist.presenter;

import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.presenter.Presenter;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface ProductListPresenter extends Presenter {

    void requestProductList();

    void onDestroy();

}
