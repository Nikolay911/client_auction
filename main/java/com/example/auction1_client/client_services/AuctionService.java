package com.example.auction1_client.client_services;

import com.example.auction1_client.client_models.Auction;
import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class AuctionService extends RestTemplateService {

    private final URLconfigProperties urLconfigProperties;

    public AuctionService(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }


    public Auction createAuction(int idCustomer, int idProduct, JsonNode body){

        Auction auction = restTemplate.postForObject(
                urLconfigProperties.getCreateAuction() + idCustomer + "/" + idProduct, body, Auction.class);
        return auction;
    }
}
