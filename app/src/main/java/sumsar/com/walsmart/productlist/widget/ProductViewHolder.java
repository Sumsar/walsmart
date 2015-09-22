package sumsar.com.walsmart.productlist.widget;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import sumsar.com.walsmart.BR;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnProductSelectedListener mOnProductSelectedListener;
    private final ImageView                 mImageView;
    private       Product                   mProduct;
    private       int                       mIndex;
    private final ViewDataBinding           mViewDataBinding;

    public ProductViewHolder(View itemView, OnProductSelectedListener onProductSelectedListener) {
        super(itemView);
        mViewDataBinding = DataBindingUtil.bind(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.product_detail_image);
        mOnProductSelectedListener = onProductSelectedListener;
        itemView.setOnClickListener(this);
    }

    public void bind(final Product product, int index) {
        mProduct = product;
        mIndex = index;
        mViewDataBinding.setVariable(BR.product, product);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        if (mOnProductSelectedListener != null) {
            mOnProductSelectedListener.onProductSelected(mProduct, mIndex, mImageView);
        }
    }
}

