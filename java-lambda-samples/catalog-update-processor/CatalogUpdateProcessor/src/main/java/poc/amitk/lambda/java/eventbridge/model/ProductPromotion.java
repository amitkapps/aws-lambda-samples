package poc.amitk.lambda.java.eventbridge.model;

import java.util.Date;

/**
 * @author amitkapps
 */
public class ProductPromotion {
    private String sku;
    private String manufacturer;
    private double productPriceUSD;
    private String sponsor;
    private String promotionCode;
    private double percentDiscount;
    private Date promotionExpires;

    public ProductPromotion(String sku, String manufacturer, double productPriceUSD) {
        this.sku = sku;
        this.manufacturer = manufacturer;
        this.productPriceUSD = productPriceUSD;
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

    public double getProductPriceUSD() {
        return productPriceUSD;
    }

    public void setProductPriceUSD(double productPriceUSD) {
        this.productPriceUSD = productPriceUSD;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public double getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public Date getPromotionExpires() {
        return promotionExpires;
    }

    public void setPromotionExpires(Date promotionExpires) {
        this.promotionExpires = promotionExpires;
    }

    @Override
    public String toString() {
        return "ProductPromotion{" +
                "sku='" + sku + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productPriceUSD=" + productPriceUSD +
                ", sponsor='" + sponsor + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                ", percentDiscount=" + percentDiscount +
                ", promotionExpires=" + promotionExpires +
                '}';
    }
}
