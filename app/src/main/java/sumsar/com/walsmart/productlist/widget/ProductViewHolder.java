package sumsar.com.walsmart.productlist.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnProductSelectedListener mOnProductSelectedListener;
    @Bind(R.id.product_list_item_title)
    TextView mTitleTextView;

    @Bind(R.id.product_list_item_sub_title)
    TextView mSubtitleTextView;

    @Bind(R.id.product_list_item_image)
    ImageView mImageView;
    private Product mProduct;
    private int     mIndex;


    public ProductViewHolder(View itemView, OnProductSelectedListener onProductSelectedListener) {
        super(itemView);
        mOnProductSelectedListener = onProductSelectedListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(final Product product, final int index) {
        mProduct = product;
        mIndex = index;
        mTitleTextView.setText(product.getProductName());
        mSubtitleTextView.setText(product.getPrice());
        Glide.with(itemView.getContext()).load(product.getProductImage()).crossFade().placeholder(R.drawable.walmart).into(mImageView);
    }

    @Override
    public void onClick(View v) {
        if (mOnProductSelectedListener != null) {
            mOnProductSelectedListener.onProductSelected(mProduct, mIndex, mImageView);
        }
    }
}

