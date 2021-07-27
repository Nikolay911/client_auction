package com.example.auction1_client.client_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auction {

    private int id;
    private int customer_who_created_auction;
    private Timestamp auction_start_date;
    private Timestamp auction_completion_date;
    private String application_status;

}
