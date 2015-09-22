package sumsar.com.walsmart.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 22/09/15.
 */

/**
 * Helper for type safety
 */
public class ExtraHelper {

    private static final String PRODUCT = "PRODUCT";

    public static void setProduct(final Bundle bundle, final Product product) {
        bundle.putParcelable(PRODUCT, product);
    }

    public static void setProduct(final Intent intent, final Product product) {
        intent.putExtra(PRODUCT, product);
    }

    @Nullable
    public static Product getProduct(final Bundle bundle) {
        Product product = null;
        if (bundle != null) {
            product = bundle.getParcelable(PRODUCT);
        }
        return product;
    }

}
