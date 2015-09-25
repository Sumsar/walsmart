package sumsar.com.walsmart.service.cache;

/**
 * Created by rasmusgohs on 25/09/15.
 */
public interface Cache {

    <T> T get(String key);

    <T> void put(String key, T value);

    boolean contains(String key);
}
