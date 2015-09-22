package sumsar.com.walsmart.productdetail.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;

import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductDetailParallaxFragment extends ProductDetailFragment {


    @Override
    @LayoutRes
    protected int getLayout(Context context) {
        return R.layout.product_detail_fragment_parallax;
    }


}
