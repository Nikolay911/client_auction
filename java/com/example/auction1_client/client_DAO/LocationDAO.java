package com.example.auction1_client.client_DAO;

import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocationDAO {

    private final URLconfigProperties urLconfigProperties;

    public LocationDAO(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Boolean createLocation(int idCustomer, JsonNode body){
        RestTemplate restTemplate = new RestTemplate();
        Boolean cust = restTemplate.postForObject(urLconfigProperties.getCreateCustomerLocation() + idCustomer, body, Boolean.class);

        return cust;
    }

}
