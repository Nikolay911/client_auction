package com.example.auction1_client.client_DAO;

import com.example.auction1_client.client_models.Auction;
import com.example.auction1_client.client_models.Order;
import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

@Component
public class OrderDAO {

    private final URLconfigProperties urLconfigProperties;

    public OrderDAO(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public String bid(int idCustomer, int idAuction, JsonNode body){
        RestTemplate restTemplate = new RestTemplate();
        String order = restTemplate.postForObject(urLconfigProperties.getBid() + idAuction + "/" + idCustomer, body, String.class);

        return order;
    }
}

