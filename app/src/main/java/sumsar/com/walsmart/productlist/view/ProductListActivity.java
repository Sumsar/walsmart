package sumsar.com.walsmart.productlist.view;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productdetail.view.ProductDetailActivity;
import sumsar.com.walsmart.productdetail.view.ProductDetailFragment;
import sumsar.com.walsmart.util.ExtraHelper;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductListActivity extends AppCompatActivity implements OnProductSelectedListener {


    private Product mProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setIcon(R.drawable.walmart);
        setTitle(R.string.app_name);

        setProductFromBundle(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mProduct != null) {
            ExtraHelper.setProduct(outState, mProduct);
        }
        super.onSaveInstanceState(outState);
    }


    private void setProductFromBundle(Bundle bundle) {
        if (getResources().getBoolean(R.bool.dual_panel)) {
            if (bundle != null) {
                Product product = ExtraHelper.getProduct(bundle);
                if (product != null) {
                    onProductSelected(product);
                }
            }
        }
    }

    public void onProductSelected(Product product) {
        onProductSelected(product, 0, null);
    }

    @Override
    public void onProductSelected(Product product, int index, ImageView productImageView) {
        mProduct = product;
        showProductDetails(product, index, productImageView);

    }

    private void showProductDetails(Product product, int index, ImageView productImageView) {
        if (getResources().getBoolean(R.bool.dual_panel)) {
            final ProductDetailFragment fragment = (ProductDetailFragment) getSupportFragmentManager().findFragmentById(R.id.product_details_fragment);
            if (fragment != null) {
                fragment.setProduct(product);
            } else {
                MyLog.e(ProductListActivity.class.getSimpleName(), "No details fragment available!");
            }
        } else {
            ProductDetailActivity.startActivity(this, product, index, productImageView);
        }
    }
}
