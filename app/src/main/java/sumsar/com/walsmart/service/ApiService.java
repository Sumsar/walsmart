package sumsar.com.walsmart.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ApiService implements API {


    private final List<Product> mProductList = new ArrayList<>();

    private static ApiService     instance;
    private        String         API_KEY;
    private        WalMartService mWalMartService;


    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    private ApiService() {
    }

    public void init(final Context context) {
        final String baseUrl = context.getString(R.string.BASE_URL);
        API_KEY = context.getString(R.string.API_KEY);
        final Retrofit mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        mWalMartService = mRetrofit.create(WalMartService.class);
    }

    @Override
    public void getProductList(final int pageNumber, final int pageSize, final ApiCallback<ProductList> callback) {
        final Call<ProductList> productList = mWalMartService.getProductList(API_KEY, pageNumber, pageSize);
        callback.execute(productList);
    }

    @Override
    public void getProductList(final int pageNumber, final ApiCallback<ProductList> callback) {
        getProductList(pageNumber, PAGE_SIZE, callback);
    }

    private void loadProducts(final sumsar.com.walsmart.service.ApiCallback<ProductList> callback) {
        getProductList(mProductList.size(), PAGE_SIZE, callback);
    }


}
