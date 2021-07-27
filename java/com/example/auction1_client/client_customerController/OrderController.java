package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_DAO.OrderDAO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class OrderController {

    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @PostMapping("/bid/{idAuction}/{idCustomer}")
    public String bid(@PathVariable int idCustomer,
                      @PathVariable int idAuction,
                      @Nullable @RequestBody JsonNode customerPrice) {
        return orderDAO.bid(idCustomer, idAuction, customerPrice);
    }

}
