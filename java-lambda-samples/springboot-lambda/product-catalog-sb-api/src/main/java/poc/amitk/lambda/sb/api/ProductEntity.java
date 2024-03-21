package poc.amitk.lambda.sb.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

/**
 * @author amitkapps
 */
@Entity(name = "product")
public class ProductEntity implements Serializable {
    @Id
    private String productSku;

    private String productName;

    protected ProductEntity(){}

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
