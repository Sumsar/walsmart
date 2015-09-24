package sumsar.com.walsmart.service;

import android.support.annotation.Nullable;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Created by rasmusgohs on 22/09/15.
 */
public abstract class ApiCallback<T> {
    private Call<T> call;

    @Nullable
    void execute(Call<T> call) {
        this.call = call;
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Response<T> response) {
                MyLog.d(ApiService.class.getSimpleName(), "Response:");
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
