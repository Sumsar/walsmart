package sumsar.com.walsmart.service;

import com.sumsar.ProductList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface WalMartService {
    @GET("/walmartproducts/{apiKey}/{pageNumber}/{pageSize}")
    Call<ProductList> getProductList(@Path("apiKey") String apiKey, @Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize);
}
