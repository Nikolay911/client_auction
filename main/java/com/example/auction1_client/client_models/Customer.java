package com.example.auction1_client.client_models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private java.sql.Date dateOfBirth;
    private List<Location> locations;
    private String login;
    private String password;

    public Customer(String name, String surname, String patronymic, Date dateOfBirth, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
    }
}

