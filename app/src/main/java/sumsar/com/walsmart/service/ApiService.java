package sumsar.com.walsmart.service;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.mock.MockResponse;
import sumsar.com.walsmart.model.Product;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ApiService {

    private static final int PAGE_SIZE = 20;

    private final List<Product> mProductList = new ArrayList<>();

    /**
     * Call back from API
     *
     * @param <T>
     */
    public static abstract class ApiCallback<T> {

        private Call<T> call;

        @Nullable
        private Interceptor interceptor;

        private void execute(Call<T> call) {
            this.call = call;
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Response<T> response) {
                    MyLog.d(ApiService.class.getSimpleName(), "Response:");
                    MyLog.d(ApiService.class.getSimpleName(), response.raw().request().toString());

                    if (interceptor != null) {
                        interceptor.onIntercept(response.body());
                    }

                    onSuccess(response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    MyLog.e(ApiService.class.getSimpleName(), "Request failed", t);
                    onFailed(t);
                }
            });
        }

        private void setInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
        }

        public abstract void onSuccess(T t);

        public abstract void onFailed(Throwable t);

        public void cancel() {
            if (call != null) {
                call.cancel();
            }
        }

        public abstract static class Interceptor<T> {
            public abstract void onIntercept(T t);

        }
    }

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


    public void getProductList(final int pageNumber, final int pageSize, final ApiCallback<ProductList> callback) {
//        final Call<ProductList> productList = mWalMartService.getProductList(API_KEY, pageNumber, pageSize);
        final ProductList list = new Gson().fromJson(MockResponse.JSON, ProductList.class);
        callback.onSuccess(list);
//        callback.execute(productList);
    }

    public void getProductList(final int pageNumber, final ApiCallback<ProductList> callback) {
        getProductList(pageNumber, PAGE_SIZE, callback);
    }

    public void loadProducts(final ApiCallback<ProductList> callback) {
        getProductList(mProductList.size(), PAGE_SIZE, callback);
    }


}
