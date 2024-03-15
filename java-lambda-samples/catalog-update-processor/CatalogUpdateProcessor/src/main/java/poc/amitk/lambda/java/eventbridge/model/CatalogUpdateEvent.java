package poc.amitk.lambda.java.eventbridge.model;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author amitkapps
 */
public class CatalogUpdateEvent {
    private String sku;
    private String manufacturer;
    private String productName;
    private ZonedDateTime launchDate;
    private double priceDenomination;
    private String priceCurrency;

    @Override
    public String toString() {
        return "CatalogUpdateEvent{" +
                "sku='" + sku + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productName='" + productName + '\'' +
                ", launchDate=" + launchDate +
                ", priceDenomination=" + priceDenomination +
                ", priceCurrency='" + priceCurrency + '\'' +
                '}';
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ZonedDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(ZonedDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public double getPriceDenomination() {
        return priceDenomination;
    }

    public void setPriceDenomination(double priceDenomination) {
        this.priceDenomination = priceDenomination;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }
}
