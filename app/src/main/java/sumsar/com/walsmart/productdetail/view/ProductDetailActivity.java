package sumsar.com.walsmart.productdetail.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.util.ExtraHelper;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public class ProductDetailActivity extends AppCompatActivity {


    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we are supposed to be in dual pane mode
        if (getResources().getBoolean(R.bool.dual_panel)) {
            finish();
        }

        setContentView(R.layout.details_activity);

        recoverState(savedInstanceState);
    }


    private void recoverState(Bundle savedInstanceState) {
        Product product = ExtraHelper.getProduct(savedInstanceState != null ? savedInstanceState : getIntent().getExtras());
        setProduct(product);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (mProduct != null) {
            ExtraHelper.setProduct(outState, mProduct);
        }
        super.onSaveInstanceState(outState);
    }

    private void setProduct(Product product) {
        mProduct = product;
        ProductDetailFragment productDetailFragment = (ProductDetailFragment) getSupportFragmentManager().findFragmentById(R.id.product_details_fragment);
        productDetailFragment.setProduct(product);
    }


    public static void startActivity(Activity activity, final Product product, final int index, @Nullable final ImageView productImageView) {
        if (productImageView == null) {
            startActivity(activity, product);
        } else {
            final Intent intent = getIntent(activity, product);
            final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, productImageView, product.getProductImage());
            ActivityCompat.startActivity(activity, intent, options.toBundle());
        }
    }

    public static void startActivity(Activity activity, final Product product) {
        final Intent intent = getIntent(activity, product);
        activity.startActivity(intent);
    }

    private static Intent getIntent(final Activity activity, final Product product) {
        final Intent intent = new Intent(activity, ProductDetailActivity.class);
        ExtraHelper.setProduct(intent, product);
        return intent;
    }
}
