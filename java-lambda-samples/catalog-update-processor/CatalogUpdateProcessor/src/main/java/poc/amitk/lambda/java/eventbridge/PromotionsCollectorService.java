package poc.amitk.lambda.java.eventbridge;

import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

/**
 * @author amitkapps
 */
public class PromotionsCollectorService {

    public ProductPromotion gatherPromotionsForProduct(CatalogUpdateEvent catalogUpdateEvent){
        ProductPromotion productPromotion = new ProductPromotion(catalogUpdateEvent.getSku(),
                                                                    catalogUpdateEvent.getManufacturer(),
                                                                    catalogUpdateEvent.getPriceDenomination());
        Faker faker = new Faker();
        productPromotion.setSponsor(faker.company().name());
        productPromotion.setPromotionCode(faker.commerce().promotionCode(2));
        productPromotion.setPercentDiscount(faker.number().randomDouble(1,0, 12));
        productPromotion.setPromotionExpires(faker.date().future(30, TimeUnit.DAYS));
        return productPromotion;
    }
}
