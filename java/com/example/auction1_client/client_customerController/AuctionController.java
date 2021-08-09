package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_models.AuctionInformation;
import com.example.auction1_client.client_services.AuctionService;
import com.example.auction1_client.client_models.Auction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @PostMapping("/auction/customer/{customerId}/product/{productId}")
    public Auction createAuction(@PathVariable int customerId,
                                 @PathVariable int productId,
                                 @Nullable @RequestBody JsonNode body){
        return auctionService.createAuction(customerId, productId, body);
    }

    @GetMapping("/auction/{auctionId}")
    public AuctionInformation getAuctionInformation(@PathVariable int auctionId) throws JsonProcessingException {
        return auctionService.getAuctionInformation(auctionId);
    }

    @GetMapping("/auctions")
    public List<AuctionInformation> getAllAuctionsInformations() throws JsonProcessingException {
        return auctionService.getAllAuctionsInformation();
    }
}
