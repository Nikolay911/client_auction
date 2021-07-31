package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.AuctionService;
import com.example.auction1_client.client_models.Auction;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class AuctionController {

    private final AuctionService auctionDAO;

    public AuctionController(AuctionService auctionDAO) {
        this.auctionDAO = auctionDAO;
    }


    @PostMapping("/auction/customer/{customerId}/product/{productId}")
    public Auction createAuction(@PathVariable int customerId,
                                 @PathVariable int productId,
                                 @Nullable @RequestBody JsonNode body){
        return auctionDAO.createAuction(customerId, productId, body);
    }
}
