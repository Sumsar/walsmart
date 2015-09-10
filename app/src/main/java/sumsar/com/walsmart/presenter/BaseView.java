package sumsar.com.walsmart.presenter;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface BaseView {
    void showError(final String message);

    void showLoading();

    void hideLoading();
}
