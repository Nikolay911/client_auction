package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_DAO.LocationDAO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LocationController {

    private final LocationDAO locationDAO;

    public LocationController(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }


    @PostMapping("/location/{idCustomer}")
    public Boolean createLocation(@PathVariable int idCustomer,
                                  @Nullable @RequestBody JsonNode body){
        return locationDAO.createLocation(idCustomer, body);
    }
}
