package sumsar.com.walsmart.service;

import android.support.annotation.Nullable;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import sumsar.com.walsmart.service.cache.Cache;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Wrapper around the Retrofit Callback. To avoid having retrofit references spread across the app
 */
public abstract class ApiCallback<T> {
    private Call<T> call;

    @Nullable
    private Cache mCache;

    @Nullable
    private String mKey;

    void execute(Call<T> call) {
        this.call = call;
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Response<T> response) {
                MyLog.d(ApiService.class.getSimpleName(), response.raw().request().toString());
                final T body = response.body();
                onSuccess(body);
                cache(body);
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

    /**
     * Cancels the request
     */
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    private void cache(T body) {
        if (mCache != null && mKey != null && body != null) {
            MyLog.d(ApiCallback.class.getSimpleName(), "Add to cache: " + mKey);
            mCache.put(mKey, body);
        }
    }

    /**
     * Supply cache and key to enable caching of response
     *
     * @param cache
     * @param key
     */
    void setCache(final Cache cache, final String key) {
        mCache = cache;
        mKey = key;
    }
}
