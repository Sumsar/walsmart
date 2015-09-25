package sumsar.com.walsmart.productlist.view;

import android.widget.ImageView;

import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 21/09/15.
 */
public interface OnProductSelectedListener {

    void onProductSelected(Product product, ImageView productImageView);
}
