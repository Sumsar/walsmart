package sumsar.com.walsmart.service.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rasmusgohs on 25/09/15.
 */
public class InMemoryCache implements Cache {


    private final Map<String, Object> map = new HashMap<>();

    @Override
    public <T> T get(String key) {
        return (T) map.get(key);
    }

    @Override
    public <T> void put(String key, T value) {
        map.put(key, value);
    }

    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }
}
