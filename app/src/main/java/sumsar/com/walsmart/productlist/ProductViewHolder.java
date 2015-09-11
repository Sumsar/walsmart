package sumsar.com.walsmart.productlist;

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

    private final ProductAdapter.OnProductClickListener mOnProductClickListener;
    @Bind(R.id.product_list_item_title)
    TextView mTitleTextView;

    @Bind(R.id.product_list_item_sub_title)
    TextView mSubtitleTextView;

    @Bind(R.id.product_list_item_image)
    ImageView mImageView;
    private Product mProduct;


    public ProductViewHolder(View itemView, ProductAdapter.OnProductClickListener onProductClickListener) {
        super(itemView);
        mOnProductClickListener = onProductClickListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(final Product product) {
        mProduct = product;
        mTitleTextView.setText(product.getProductName());
        mSubtitleTextView.setText(product.getPrice());
        Glide.with(itemView.getContext()).load(product.getProductImage()).crossFade().placeholder(R.drawable.walmart).into(mImageView);
    }

    @Override
    public void onClick(View v) {
        if (mOnProductClickListener != null) {
            mOnProductClickListener.onClick(mProduct);
        }
    }
}

