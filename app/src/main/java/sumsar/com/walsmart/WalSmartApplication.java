package sumsar.com.walsmart;

import android.app.Application;

import sumsar.com.walsmart.service.ApiService;
import sumsar.com.walsmart.util.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class WalSmartApplication extends Application {

    private static WalSmartApplication instance;


    public static WalSmartApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyLog.init(this);
        ApiService.getInstance().init(this);
    }


}
