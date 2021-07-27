package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_DAO.CustomerDAO;
import com.example.auction1_client.client_models.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class CustomerController {

    private final CustomerDAO customerDAO;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id) throws JsonProcessingException {
        return customerDAO.clientCustomer(id);
    }

    @PostMapping("/customer")
    public JsonNode createCustomer(@Nullable @RequestBody JsonNode body) throws JsonProcessingException {
        return customerDAO.clientCreateCustomer(body);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(@RequestParam int page) throws JsonProcessingException {

        if(page > 0) return customerDAO.getAllCustomerPaginated(page);
        return customerDAO.clientAllCustomer();
    }

    @GetMapping("/selectsurname")
    public List<Customer> selectNameCustomer(@RequestParam String selectsurname) throws JsonProcessingException {
        return customerDAO.selectSurnameCustomer(selectsurname);
    }

   /* @GetMapping("/customersandhislocations")
    public List<CustomerInf> CustomersAndHisLocations() throws JsonProcessingException {
        return customerDAO.clientAllCustomerWithLocation();
    }*/

    @GetMapping("/1")
    public List<Customer> selectCity(@RequestParam String city) throws JsonProcessingException {
        return customerDAO.selectCityCustomer(city);
    }

}
