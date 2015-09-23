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

import sumsar.com.walsmart.BR;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductDetailFragment extends Fragment{


    private ViewDataBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayout(getContext()), container, false);
        return mDataBinding.getRoot();
    }

    @LayoutRes
    protected int getLayout(Context context) {
        return R.layout.product_detail_fragment;
    }

    public void setProduct(Product product) {

        if (product == null) {
            MyLog.e(ProductDetailFragment.class.getSimpleName(), "Product is null");
            return;
        }
        mDataBinding.setVariable(BR.product, product);
        mDataBinding.executePendingBindings();

    }

}
