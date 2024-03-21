package poc.amitk.lambda.sb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author amitkapps
 */
@RestController()
@RequestMapping("/products")
public class ProductCatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productSku}")
    public Product getProductById(@PathVariable String productSku){
        return productService.getProductBySku(productSku);
    }

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product){
        return productService.addProductToCatalog(product);
    }

    @DeleteMapping("/{productSku}")
    public ProductEntity removeProduct(@PathVariable String productSku){
        return productService.removeProductFromCatalog(productSku);
    }
}
