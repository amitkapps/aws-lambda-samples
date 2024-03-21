package poc.amitk.lambda.sb.api;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author amitkapps
 */
public interface ProductRepository extends Repository<ProductEntity, String> {
    ProductEntity findByProductSku(String productSku);

    List<ProductEntity> findAll();

    ProductEntity save(ProductEntity productEntity);

    ProductEntity deleteByProductSku(String productSku);
}
