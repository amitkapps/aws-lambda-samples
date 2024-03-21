package poc.amitk.lambda.sb.api;

/**
 * @author amitkapps
 */
public class ProductPojoConverter {

    public static Product toProduct(ProductEntity productEntity){
        Product product = new Product();
        product.setProductSku(productEntity.getProductSku());
        product.setProductName(productEntity.getProductName());
        return product;
    }
}
