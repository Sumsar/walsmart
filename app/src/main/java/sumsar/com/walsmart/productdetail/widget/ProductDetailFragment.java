package sumsar.com.walsmart.productdetail.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sumsar.com.walsmart.BR;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productdetail.presenter.ProductDetailView;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductDetailFragment extends Fragment implements ProductDetailView {


    private ViewDataBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayout(getContext()), container, false);
        return mDataBinding.getRoot();
    }

    @LayoutRes
    private int getLayout(Context context) {
        final boolean dualPanel = context.getResources().getBoolean(R.bool.dual_panel);
        return dualPanel ? R.layout.product_detail_fragment : R.layout.product_detail_fragment_parallax;
    }

    @Override
    public void setProduct(Product product) {

        if (product == null) {
            MyLog.e(ProductDetailFragment.class.getSimpleName(), "Product is null");
            return;
        }
        mDataBinding.setVariable(BR.product, product);
        mDataBinding.executePendingBindings();

    }

}
