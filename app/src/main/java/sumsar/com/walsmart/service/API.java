package sumsar.com.walsmart.service;

import sumsar.com.walsmart.model.ProductList;

/**
 * Created by rasmusgohs on 22/09/15.
 */
public interface API {


    int PAGE_SIZE = 20;

    void getProductList(final int pageNumber, final ApiCallback<ProductList> callback);

    void getProductList(final int pageNumber, final int pageSize, final ApiCallback<ProductList> callback);


}
