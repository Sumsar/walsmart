package sumsar.com.walsmart.productlist.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import sumsar.com.walsmart.BR;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.ObservableProductId;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnProductSelectedListener mOnProductSelectedListener;
    private final ImageView                 mImageView;
    private       Product                   mProduct;
    private final ViewDataBinding           mViewDataBinding;

    public ProductViewHolder(View itemView, OnProductSelectedListener onProductSelectedListener) {
        super(itemView);
        mViewDataBinding = DataBindingUtil.bind(itemView);

        final View root = itemView.findViewById(R.id.product_list_item_root);
        root.setOnClickListener(this);

        mImageView = (ImageView) root.findViewById(R.id.product_list_item_image);
        mOnProductSelectedListener = onProductSelectedListener;
    }

    public void bind(final Product product, final ObservableProductId selectedProductId) {
        mProduct = product;
        mViewDataBinding.setVariable(BR.product, product);
        mViewDataBinding.setVariable(BR.selectedProductId, selectedProductId);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        if (mOnProductSelectedListener != null) {
            mOnProductSelectedListener.onProductSelected(mProduct, mImageView);
        }
    }
}

