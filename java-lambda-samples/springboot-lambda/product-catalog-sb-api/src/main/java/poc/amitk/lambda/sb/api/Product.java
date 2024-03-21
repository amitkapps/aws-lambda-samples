package poc.amitk.lambda.sb.api;

/**
 * @author amitkapps
 */
public class Product {
    Long productId;
    String productName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
