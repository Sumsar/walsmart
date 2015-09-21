package sumsar.com.walsmart.productlist.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {


    private OnProductSelectedListener mOnProductSelectedListener;
    private List<Product> mProducts = new ArrayList<>();

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(item, mOnProductSelectedListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(mProducts.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<Product> products) {
        mProducts.clear();
        mProducts.addAll(products);
    }

    public void setOnProductClickListener(OnProductSelectedListener onProductSelectedListener) {
        mOnProductSelectedListener = onProductSelectedListener;
    }
}
