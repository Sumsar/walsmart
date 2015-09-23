package sumsar.com.walsmart.productdetail.view;

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
import android.widget.ScrollView;

import sumsar.com.walsmart.BR;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductDetailFragment extends Fragment {


    private ViewDataBinding mDataBinding;
    /**
     * Is only present in the tablet layout. Thus it can be null
     */
    @Nullable
    private ScrollView      mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ScrollView) view.findViewById(R.id.product_detail_scroll_view);
    }

    @LayoutRes
    protected int getLayout() {
        return R.layout.product_detail_fragment;
    }

    public void setProduct(Product product) {
        scrollDescriptionToTop();

        if (product == null) {
            MyLog.e(ProductDetailFragment.class.getSimpleName(), "Product is null");
            return;
        }

        mDataBinding.setVariable(BR.product, product);
        mDataBinding.executePendingBindings();

    }

    private void scrollDescriptionToTop() {
        if (mScrollView != null) {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        }

    }

}
