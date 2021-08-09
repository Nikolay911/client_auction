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
    private int customerWhoCreatedAuction;
    private Timestamp auctionStartDate;
    private Timestamp auctionCompletionDate;
    private String applicationStatus;

}
