package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/bid/auction/{AuctionId}/customer/{CustomerId}")
    public String bid(@PathVariable int CustomerId,
                      @PathVariable int AuctionId,
                      @Nullable @RequestBody JsonNode customerPrice) {
        return orderService.bid(CustomerId, AuctionId, customerPrice);
    }

}
