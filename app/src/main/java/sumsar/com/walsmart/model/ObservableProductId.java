package sumsar.com.walsmart.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by rasmusgohs on 23/09/15.
 */
public class ObservableProductId extends BaseObservable {

    private String productId;

    @Bindable
    public String getProductId() {
        return productId;
    }

    @Bindable
    public void setProductId(String productId) {
        this.productId = productId;
        notifyChange();
    }
}
