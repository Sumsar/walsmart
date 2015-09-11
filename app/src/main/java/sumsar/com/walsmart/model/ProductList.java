package sumsar.com.walsmart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasmusgohs on 09/09/15.
 */
public class ProductList {

    private List<Product> products = new ArrayList<>();

    private double totalProducts;
    private double pageNumber;
    private double pageSize;
    private int    status;
    private String kind;
    private String etag;

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalProducts() {
        return totalProducts;
    }

    public double getPageNumber() {
        return pageNumber;
    }

    public double getPageSize() {
        return pageSize;
    }

    public int getStatus() {
        return status;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }
}
