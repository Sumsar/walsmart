package sumsar.com.walsmart.productdetail.presenter;

import sumsar.com.walsmart.presenter.Presenter;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public interface ProductDetailPresenter extends Presenter {

    void requestProduct(String id);
}
