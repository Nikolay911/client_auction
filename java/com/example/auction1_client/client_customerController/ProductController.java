package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_DAO.ProductDAO;
import com.example.auction1_client.client_models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @PostMapping("/product/{idcustomer}")
    public Product createProduct(@PathVariable int idcustomer,
                                 @Nullable @RequestBody JsonNode body){
        return productDAO.createProduct(idcustomer, body);
    }

    @GetMapping("/product/{idproduct}")
    public Product getProduct(@PathVariable int idproduct){
        return productDAO.getProduct(idproduct);
    }

}
