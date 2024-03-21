package poc.amitk.lambda.sb.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amitkapps
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Product getProductBySku(String productSku){
        logger.info("Getting product: {}", productSku);
        return ProductPojoConverter.toProduct(productRepository.findByProductSku(productSku));
    }

    public List<Product> getAllProducts() {
        logger.info("getting all products");
        ArrayList<Product> products = new ArrayList<>();
        products.add(getProductBySku("1000"));
        products.add(getProductBySku("2000"));
        return products;
    }

    public void addProductToCatalog(Product product){
        logger.info("Adding product {} to catalog", product.getProductSku());
    }

    public void removeProductFromCatalog(String productSku) {
        logger.info("Adding product {} to catalog", productSku);
    }
}
