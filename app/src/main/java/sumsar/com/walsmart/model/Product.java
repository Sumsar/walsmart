package sumsar.com.walsmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private final String  productId;
    private final String  productName;
    private final String  productImage;
    private final String  shortDescription;
    private final String  longDescription;
    private final String  price;
    private final float   reviewRating;
    private final long    reviewCount;
    private final boolean inStock;
    private boolean selected = false;

    protected Product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        productImage = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        price = in.readString();
        reviewRating = in.readFloat();
        reviewCount = in.readLong();
        inStock = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public float getReviewRating() {
        return reviewRating;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    public String getProductImage() {
        return productImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productImage);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeString(price);
        dest.writeFloat(reviewRating);
        dest.writeLong(reviewCount);
        dest.writeByte((byte) (inStock ? 1 : 0));
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
