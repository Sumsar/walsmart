package sumsar.com.walsmart.mock;

import com.google.gson.Gson;

import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.API;
import sumsar.com.walsmart.service.ApiCallback;
import sumsar.com.walsmart.service.cache.Cache;
import sumsar.com.walsmart.service.cache.InMemoryCache;
import sumsar.com.walsmart.util.log.MyLog;

/**
 * Created by rasmusgohs on 22/09/15.
 */
public class MockAPI implements API {


    private static MockAPI instance;
    private final Gson  mGson  = new Gson();
    private final Cache mCache = new InMemoryCache();


    private MockAPI() {
    }

    public static synchronized MockAPI getInstance() {
        if (instance == null) {
            instance = new MockAPI();
        }

        return instance;
    }

    @Override
    public void getProductList(int pageNumber, ApiCallback<ProductList> callback) {

        final String cacheKey = GET_PRODUCT_CACHE_KEY + pageNumber;
        if (mCache.contains(cacheKey)) {
            callback.onSuccess((ProductList) mCache.get(cacheKey));
            MyLog.d(MockAPI.class.getSimpleName(), "Found in cache: " + cacheKey);
            return;
        }

        final ProductList list = mGson.fromJson(MockResponse.JSON, ProductList.class);
        MyLog.d(MockAPI.class.getSimpleName(), "Add to cache: " + cacheKey);

        mCache.put(cacheKey, list);
        callback.onSuccess(list);
    }

    @Override
    public void getProductList(int pageNumber, int pageSize, ApiCallback<ProductList> callback) {
        getProductList(pageNumber, callback);
    }
}
