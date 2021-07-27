package com.example.auction1_client.client_DAO;

import com.example.auction1_client.client_models.Customer;
import com.example.auction1_client.client_models.Location;
import com.example.auction1_client.client_models.Product;
import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductDAO {

    private final URLconfigProperties urLconfigProperties;

    public ProductDAO(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Product createProduct(int idCustomer, JsonNode body){
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.postForObject(urLconfigProperties.getCreateProduct() + idCustomer, body, Product.class);

        return product;
    }

    public Product getProduct(int idProduct){
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(urLconfigProperties.getGetProduct() + idProduct, Product.class);

        return product;
    }
}
