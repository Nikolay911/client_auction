package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.ProductService;
import com.example.auction1_client.client_models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/product/{customerId}")
    public Product createProduct(@PathVariable int customerId,
                                 @Nullable @RequestBody JsonNode body){
        return productService.createProduct(customerId, body);
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId){
        return productService.getProduct(productId);
    }

}
