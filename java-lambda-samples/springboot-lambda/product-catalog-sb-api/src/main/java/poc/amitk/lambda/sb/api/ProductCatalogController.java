package poc.amitk.lambda.sb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amitkapps
 */
@RestController()
@RequestMapping("/products")
public class ProductCatalogController {

    @GetMapping("")
    public String getProducts(){
        return """
                {"productId": "1", "productName": "Samsung Galaxy S8"}
                """;
    }
}
