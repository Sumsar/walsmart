package sumsar.com.walsmart.productdetail.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.productdetail.presenter.ProductDetailPagerPresenter;
import sumsar.com.walsmart.productdetail.presenter.ProductDetailPagerPresenterImpl;
import sumsar.com.walsmart.productdetail.presenter.ProductDetailPagerView;

/**
 * Created by rasmusgohs on 11/09/15.
 */
public class ProductDetailPagerFragment extends Fragment {


    public static final String SELECTED_PRODUCT_KEY       = "SELECTED_PRODUCT_KEY";
    public static final String SELECTED_PRODUCT_INDEX_KEY = "SELECTED_PRODUCT_INDEX_KEY";

    public static final String TAG = "ProductDetailPagerFragment";


    private ViewPager              mViewPager;
    private ProductFragmentAdapter mProductFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewPager = (ViewPager) inflater.inflate(R.layout.product_detail_pager_fragment, container, false);
        return mViewPager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter(savedInstanceState != null ? savedInstanceState : getArguments());

    }

    private void initAdapter(final Bundle bundle) {

        int currentIndex = bundle != null ? bundle.getInt(SELECTED_PRODUCT_INDEX_KEY) : -1;
        mProductFragmentAdapter = new ProductFragmentAdapter(getFragmentManager(), currentIndex);
        mViewPager.setAdapter(mProductFragmentAdapter);
    }


    public void setSelectedIndex(final int index) {
        mViewPager.setCurrentItem(index);
    }

    public static ProductDetailPagerFragment getInstance(final int index) {
        final Bundle bundle = new Bundle();
        bundle.putInt(SELECTED_PRODUCT_INDEX_KEY, index);

        return getInstance(bundle);
    }

    public static ProductDetailPagerFragment getInstance(final Bundle bundle) {
        final ProductDetailPagerFragment productDetailPagerFragment = new ProductDetailPagerFragment();
        productDetailPagerFragment.setArguments(bundle);
        return productDetailPagerFragment;
    }


    public static class ProductFragmentAdapter extends FragmentStatePagerAdapter implements ProductDetailPagerView {
        private List<Product> mProducts = new ArrayList<>();
        private ProductDetailPagerPresenter mProductDetailPagerPresenter;
        private int                         currentIndex;

        public ProductFragmentAdapter(FragmentManager fm, final int currentIndex) {
            super(fm);
            this.currentIndex = currentIndex;
            mProductDetailPagerPresenter = new ProductDetailPagerPresenterImpl(this);
            mProductDetailPagerPresenter.requestProductList(currentIndex);
        }

        @Override
        public Fragment getItem(int position) {
            currentIndex = position;
            if (position == getCount() - 1) {
                mProductDetailPagerPresenter.requestProductList(currentIndex);
            }
            return ProductDetailFragment.newInstance(mProducts.get(position));

        }


        @Override
        public int getCount() {
            return mProducts.size();
        }

        @Override
        public void setProducts(List<Product> products) {
            mProducts = products;
            notifyDataSetChanged();
        }
    }
}
