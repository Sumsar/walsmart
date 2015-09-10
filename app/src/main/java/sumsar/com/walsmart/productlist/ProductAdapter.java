package sumsar.com.walsmart.productlist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sumsar.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> mProducts = new ArrayList<>();

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<Product> products) {
        mProducts.clear();
        mProducts.addAll(products);
    }
}
