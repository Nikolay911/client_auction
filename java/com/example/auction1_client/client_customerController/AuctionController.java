package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_DAO.AuctionDAO;
import com.example.auction1_client.client_models.Auction;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuctionController {

    private final AuctionDAO auctionDAO;

    public AuctionController(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }


    @PostMapping("/auction/{idcustomer}/{idproduct}")
    public Auction createAuction(@PathVariable int idcustomer,
                                 @PathVariable int idproduct,
                                 @Nullable @RequestBody JsonNode body){
        return auctionDAO.createAuction(idcustomer, idproduct, body);
    }
}
