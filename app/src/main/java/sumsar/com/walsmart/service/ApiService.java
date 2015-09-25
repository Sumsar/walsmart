package sumsar.com.walsmart.service;

import android.content.Context;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.cache.Cache;
import sumsar.com.walsmart.service.cache.InMemoryCache;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ApiService implements API {


    private static ApiService     instance;
    private        String         API_KEY;
    private        WalMartService mWalMartService;

    private Cache mCache = new InMemoryCache();

    public static synchronized ApiService getInstance() {
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

        final String key = GET_PRODUCT_CACHE_KEY + pageNumber + pageSize;
        if (mCache.contains(key)) {
            MyLog.d(ApiService.class.getSimpleName(), "Found in cache: " + key);
            callback.onSuccess((ProductList) mCache.get(key));
            return;
        }

        final Call<ProductList> productList = mWalMartService.getProductList(API_KEY, pageNumber, pageSize);
        callback.setCache(mCache, key);
        callback.execute(productList);
    }

    @Override
    public void getProductList(final int pageNumber, final ApiCallback<ProductList> callback) {
        getProductList(pageNumber, PAGE_SIZE, callback);
    }

}
