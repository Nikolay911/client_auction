package com.example.auction1_client.client_DAO;

import com.example.auction1_client.client_models.Auction;
import com.example.auction1_client.client_models.Product;
import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuctionDAO {

    private final URLconfigProperties urLconfigProperties;

    public AuctionDAO(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Auction createAuction(int idCustomer, int idProduct, JsonNode body){
        RestTemplate restTemplate = new RestTemplate();
        Auction auction = restTemplate.postForObject(urLconfigProperties.getCreateAuction() + idCustomer + "/" + idProduct, body, Auction.class);
        return auction;
    }
}
