package sumsar.com.walsmart.service;

import android.content.Context;

import com.sumsar.ProductList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ApiService {

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
        final Retrofit mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        mWalMartService = mRetrofit.create(WalMartService.class);
    }


    public void getProductList(final int pageNumber, final int pageSize, final Callback<ProductList> callback) {
        final Call<ProductList> productList = mWalMartService.getProductList(API_KEY, pageNumber, pageSize);
        productList.enqueue(callback);
    }
}
