package com.example.auction1_client.inf_models;

import com.example.auction1_client.client_models.Customer;
import com.example.auction1_client.client_models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInf {

    private Customer customer;
    private List<Location> location;

}
