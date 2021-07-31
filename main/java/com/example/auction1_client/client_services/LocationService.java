package com.example.auction1_client.client_services;

import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class LocationService extends RestTemplateService{

    private final URLconfigProperties urLconfigProperties;

    public LocationService(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Boolean createLocation(int idCustomer, JsonNode body){

        Boolean cust = restTemplate.postForObject(urLconfigProperties.getCreateCustomerLocation() + idCustomer, body, Boolean.class);

        return cust;
    }

}
