package sumsar.com.walsmart;

import android.app.Application;

import sumsar.com.walsmart.service.ApiService;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class WalSmartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Bind singletons
        MyLog.init(this);
        ApiService.getInstance().init(this);
    }


}
