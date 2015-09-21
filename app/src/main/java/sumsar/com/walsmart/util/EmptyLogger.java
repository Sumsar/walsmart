package sumsar.com.walsmart.util;

/**
 * Created by rasmusgohs on 21/09/15.
 */
public class EmptyLogger implements Logger {
    @Override
    public void d(String tag, String message) {
        //Empty
    }

    @Override
    public void e(String tag, String message, Throwable t) {
        //Empty
    }

    @Override
    public void e(String tag, String message) {
        //Empty
    }
}
