package poc.amitk.lambda.sb.api;

import org.springframework.data.repository.Repository;

/**
 * @author amitkapps
 */
public interface ProductRepository extends Repository<ProductEntity, String> {
    ProductEntity findByProductSku(String productSku);
}
