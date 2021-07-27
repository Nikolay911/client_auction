package com.example.auction1_client.client_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private File foto;
    private String productDescription;
    private Number startPrice;

}


