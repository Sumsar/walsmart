package sumsar.com.walsmart.util;

import android.content.Context;
import android.util.Log;

import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class MyLog {

    private static boolean shouldLog;


    public static void init(Context context) {
        shouldLog = context.getResources().getBoolean(R.bool.LOG_ENABLED);
    }

    public static void d(String tag, String message) {
        if (shouldLog) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Throwable t) {
        if (shouldLog) {
            Log.e(tag, message, t);
        }
    }


}
