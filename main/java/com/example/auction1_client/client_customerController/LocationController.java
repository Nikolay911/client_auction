package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.LocationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/client")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/location/customer/{CustomerId}")
    public Boolean createLocation(@PathVariable int CustomerId,
                                  @Nullable @RequestBody JsonNode body){
        return locationService.createLocation(CustomerId, body);
    }
}
