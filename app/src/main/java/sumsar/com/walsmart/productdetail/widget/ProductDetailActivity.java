package sumsar.com.walsmart.productdetail.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public class ProductDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIntent().getExtras();

        final Bundle bundle = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        initFragments(bundle);
    }


    private void initFragments(final Bundle bundle) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(ProductDetailPagerFragment.TAG) == null) {
            fragmentTransaction.replace(R.id.container, ProductDetailPagerFragment.getInstance(bundle)).commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }


    public static void startActivity(Activity activity, final Product product, final int index, final ImageView productImageView) {
        final Intent intent = new Intent(activity, ProductDetailActivity.class);
//        intent.putExtra(ProductDetailPagerFragment.SELECTED_PRODUCT_KEY, product);
        intent.putExtra(ProductDetailPagerFragment.SELECTED_PRODUCT_INDEX_KEY, index);
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, productImageView, activity.getString(R.string.product_image_view));
        ActivityCompat.startActivity(activity, intent, options.toBundle());

    }
}
