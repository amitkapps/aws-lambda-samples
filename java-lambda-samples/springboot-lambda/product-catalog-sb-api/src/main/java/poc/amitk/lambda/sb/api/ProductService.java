package poc.amitk.lambda.sb.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ProductEntity productEntity = productRepository.findByProductSku(productSku);
        return null != productEntity ? ProductPojoConverter.toProduct(productEntity) : null;
    }

    public List<Product> getAllProducts() {
        logger.info("getting all products");
        List<ProductEntity> allProductEntities = productRepository.findAll();
        logger.info("found {} products", allProductEntities.size());
        return allProductEntities.stream()
                .map(ProductPojoConverter::toProduct)
                .toList();
    }

    @Transactional
    public Product addProductToCatalog(Product product){
        logger.info("Adding product {} to catalog", product.getProductSku());
        ProductEntity productEntity = productRepository.save(ProductPojoConverter.toProductEntity(product));
        return ProductPojoConverter.toProduct(productEntity);
    }

    @Transactional
    public ProductEntity removeProductFromCatalog(String productSku) {
        logger.info("Removing product {} from catalog", productSku);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductSku(productSku);
        return productRepository.delete(productEntity);
    }
}
