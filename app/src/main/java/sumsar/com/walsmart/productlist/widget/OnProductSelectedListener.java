package sumsar.com.walsmart.productlist.widget;

import android.widget.ImageView;

import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 21/09/15.
 */
public interface OnProductSelectedListener {

    void onProductSelected(Product product, int index, ImageView productImageView);
}