package sumsar.com.walsmart.productlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sumsar.Product;

import butterknife.Bind;
import butterknife.ButterKnife;
import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.product_list_item_title)
    TextView mTitleTextView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Product product) {
        mTitleTextView.setText(product.getProductName());
    }
}

