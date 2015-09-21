package sumsar.com.walsmart.productlist.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productdetail.widget.ProductDetailActivity;
import sumsar.com.walsmart.productdetail.widget.ProductDetailPagerFragment;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListActivity extends AppCompatActivity implements OnProductSelectedListener {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.drawable.walmart);
        setTitle(R.string.app_name);
    }

    @Override
    public void onProductSelected(Product product, int index, ImageView productImageView) {
        //Do we have a detail fragment - else start activity
        final ProductDetailPagerFragment fragment = (ProductDetailPagerFragment) getSupportFragmentManager().findFragmentById(R.id.product_details_pager_fragment);
        if (fragment != null) {
            fragment.setSelectedIndex(index);
        } else {
            ProductDetailActivity.startActivity(this, product, index, productImageView);
        }
    }
}
