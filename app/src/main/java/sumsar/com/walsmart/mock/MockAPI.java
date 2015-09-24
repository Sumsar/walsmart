package sumsar.com.walsmart.mock;

import com.google.gson.Gson;

import sumsar.com.walsmart.model.ProductList;
import sumsar.com.walsmart.service.API;
import sumsar.com.walsmart.service.ApiCallback;

/**
 * Created by rasmusgohs on 22/09/15.
 */
public class MockAPI implements API {


    private static MockAPI instance;
    private final Gson mGson = new Gson();


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
        final ProductList list = mGson.fromJson(MockResponse.JSON, ProductList.class);
        callback.onSuccess(list);
    }

    @Override
    public void getProductList(int pageNumber, int pageSize, ApiCallback<ProductList> callback) {
        getProductList(pageNumber, callback);
    }
}
