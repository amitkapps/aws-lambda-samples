package poc.amitk.lambda.java.eventbridge.promotions;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.amitk.lambda.java.eventbridge.model.CatalogUpdateEvent;
import poc.amitk.lambda.java.eventbridge.model.ProductPromotion;

import java.util.concurrent.TimeUnit;

/**
 * @author amitkapps
 */
public class PromotionsCollectorService {

    private Logger logger = LoggerFactory.getLogger(PromotionsCollectorService.class);

    public ProductPromotion gatherPromotionsForProduct(CatalogUpdateEvent catalogUpdateEvent){
        logger.info("Checking product promotions");
        if("9999".equals(catalogUpdateEvent.getSku()))
            throw new RuntimeException("Error collecting promotion for sku: " + catalogUpdateEvent.getSku());
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
