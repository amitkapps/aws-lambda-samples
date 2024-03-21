package poc.amitk.lambda.sb.api.product;

import org.springframework.data.repository.Repository;
import poc.amitk.lambda.sb.api.product.ProductEntity;

import java.util.List;

/**
 * @author amitkapps
 */
public interface ProductRepository extends Repository<ProductEntity, String> {
    ProductEntity findByProductSku(String productSku);

    List<ProductEntity> findAll();

    ProductEntity save(ProductEntity productEntity);

    ProductEntity delete(ProductEntity productEntity);
}
