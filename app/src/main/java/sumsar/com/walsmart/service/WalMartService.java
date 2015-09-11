package sumsar.com.walsmart.service;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import sumsar.com.walsmart.model.ProductList;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public interface WalMartService {
    @Headers("Accept: Application/JSON")
    @GET("walmartproducts/{apiKey}/{pageNumber}/{pageSize}")
    Call<ProductList> getProductList(@Path("apiKey") String apiKey, @Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize);
}
