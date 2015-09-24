package sumsar.com.walsmart.util.log;

/**
 * Created by rasmusgohs on 21/09/15.
 */
interface Logger {

    void d(String tag, String message);

    void e(String tag, String message, Throwable t);

    void e(String tag, String message);
}
