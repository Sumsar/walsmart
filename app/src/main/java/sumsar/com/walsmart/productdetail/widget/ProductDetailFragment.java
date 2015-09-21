package sumsar.com.walsmart.productdetail.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productdetail.presenter.ProductDetailView;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductDetailFragment extends Fragment implements ProductDetailView {


    private static final String PRODUCT_KEY = "PRODUCT_KEY";

    @Bind(R.id.product_detail_image)
    ImageView mProductImage;

    @Bind(R.id.product_detail_name)
    TextView mProductName;

    @Bind(R.id.product_detail_rating_bar)
    RatingBar mRatingBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private Product mProduct;


    public static final String TAG = ProductDetailFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        mProduct = bundle.getParcelable(PRODUCT_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.product_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        setProduct(mProduct);
    }

    @Override
    public void setProduct(Product product) {

        if (product == null) {
            MyLog.e(ProductDetailFragment.TAG, "Product is null");
            return;
        }

        Glide.with(getActivity()).load(product.getProductImage()).into(mProductImage);
        mProductName.setText(product.getProductName());
        mRatingBar.setRating(product.getReviewRating());
    }

    public static ProductDetailFragment newInstance(final Product product) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_KEY, product);
        final ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);
        return productDetailFragment;
    }

}
