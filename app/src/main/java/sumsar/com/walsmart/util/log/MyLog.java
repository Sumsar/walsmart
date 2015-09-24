package sumsar.com.walsmart.util.log;

import android.content.Context;

import sumsar.com.walsmart.R;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class MyLog {

    private static Logger mLogger = new EmptyLogger(); // Default

    public static void init(Context context) {
        final boolean shouldLog = context.getResources().getBoolean(R.bool.LOG_ENABLED);
        mLogger = shouldLog ? new DebugLogger() : new EmptyLogger();

    }

    public static void d(String tag, String message) {
        if (mLogger == null) {
            throw new IllegalStateException("Please call init");
        }

        mLogger.d(tag, message);
    }

    public static void e(String tag, String message, Throwable t) {
        if (mLogger == null) {
            throw new IllegalStateException("Please call init");
        }

        mLogger.e(tag, message, t);

    }

    public static void e(String tag, String message) {
        if (mLogger == null) {
            throw new IllegalStateException("Please call init");
        }

        mLogger.e(tag, message);
    }


}
