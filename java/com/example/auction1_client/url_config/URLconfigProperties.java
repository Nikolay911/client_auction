package com.example.auction1_client.url_config;

import com.example.auction1_client.client_models.Customer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component()
@ConfigurationProperties(prefix = "url", ignoreUnknownFields = false)
public class URLconfigProperties {

    private String getCustomer;
    private String getCustomerLocations;
    private String createCustomer;
    private String getAllCustomerWithLocations;
    private String createCustomerLocation;
    private String createProduct;
    private String getProduct;
    private String createAuction;
    private String bid;

}
