package sumsar.com.walsmart.productlist.presenter;

import sumsar.com.walsmart.presenter.Presenter;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface ProductListPresenter extends Presenter {

    void requestNextProductList();

    void onDestroy();

}
