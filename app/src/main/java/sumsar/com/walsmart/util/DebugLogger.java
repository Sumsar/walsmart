package sumsar.com.walsmart.util;

import android.util.Log;

/**
 * Created by rasmusgohs on 21/09/15.
 */
public class DebugLogger implements Logger {
    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message, Throwable t) {
        Log.e(tag, message, t);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }
}
