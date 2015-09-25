package sumsar.com.walsmart.productlist.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.ObservableProductId;
import sumsar.com.walsmart.model.Product;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {


    private OnProductSelectedListener mOnProductSelectedListener;
    private final List<Product> mProducts = new ArrayList<>();
    private final ObservableProductId selectedProductId;


    public ProductAdapter(ObservableProductId selectedProductId) {
        this.selectedProductId = selectedProductId;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(item, mOnProductSelectedListener);
    }


    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(mProducts.get(i), selectedProductId);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<Product> products) {
        mProducts.addAll(products);
    }

    public void setOnProductClickListener(OnProductSelectedListener onProductSelectedListener) {
        mOnProductSelectedListener = onProductSelectedListener;
    }
}
