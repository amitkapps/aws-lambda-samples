package poc.amitk.lambda.sb.api;

import org.springframework.web.bind.annotation.*;

/**
 * @author amitkapps
 */
@RestController()
@RequestMapping("/products")
public class ProductCatalogController {

    @GetMapping("")
    public String getProducts(){
        return """
                [
                    {"productId": "1", "productName": "Samsung Galaxy S8"},
                    {"productId": "2", "productName": "iPhone 15 pro max"}
                ]
                """;
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("iPhone SE");
        return product;
    }
}
