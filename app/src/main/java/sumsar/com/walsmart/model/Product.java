package sumsar.com.walsmart.model;

public class Product {

    private String  productId;
    private String  productName;
    private String  productImage;
    private String  shortDescription;
    private String  longDescription;
    private String  price;
    private double  reviewRating;
    private double  reviewCount;
    private boolean inStock;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPrice() {
        return price;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public double getReviewCount() {
        return reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    public String getProductImage() {
        return productImage;
    }
}
