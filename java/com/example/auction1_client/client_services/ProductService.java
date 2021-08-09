package com.example.auction1_client.client_services;

import com.example.auction1_client.client_models.Product;
import com.example.auction1_client.config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class ProductService extends RestTemplateService{

    private final URLconfigProperties urLconfigProperties;

    public ProductService(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Product createProduct(int idCustomer, JsonNode body){

        Product product = restTemplate.postForObject(urLconfigProperties.getCreateProduct() + idCustomer, body, Product.class);

        return product;
    }

    public Product getProduct(int idProduct){

        Product product = restTemplate.getForObject(urLconfigProperties.getGetProduct() + idProduct, Product.class);

        return product;
    }
}
