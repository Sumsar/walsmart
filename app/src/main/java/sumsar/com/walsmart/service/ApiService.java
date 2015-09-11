package sumsar.com.walsmart.service;

import android.content.Context;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import sumsar.com.walsmart.R;
import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ApiService {

    /**
     * Call back from API
     *
     * @param <T>
     */
    public static abstract class ApiCallback<T> {

        private Call<T> call;

        private void execute(Call<T> call) {
            this.call = call;
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Response<T> response) {
                    MyLog.d(ApiService.class.getSimpleName(), response.raw().request().toString());

                    onSuccess(response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    MyLog.e(ApiService.class.getSimpleName(), "Request failed", t);

                    onFailed(t);
                }
            });
        }

        public abstract void onSuccess(T t);

        public abstract void onFailed(Throwable t);

        public void cancel() {
            if (call != null) {
                call.cancel();
            }
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
        final Call<ProductList> productList = mWalMartService.getProductList(API_KEY, pageNumber, pageSize);
        callback.execute(productList);
    }
}
