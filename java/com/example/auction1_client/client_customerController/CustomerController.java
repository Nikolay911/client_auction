package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.CustomerService;
import com.example.auction1_client.client_models.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/client")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id,
                                HttpServletRequest request) throws JsonProcessingException {
        return customerService.getCustomer(id, request);
    }

    @PostMapping("/customer")
    public JsonNode createCustomer(@Nullable @RequestBody JsonNode body,
                                   HttpServletRequest request) throws Exception {
        return customerService.createCustomer(body, request);
    }

    @GetMapping("/all/customer")
    public List<Customer> getAllCustomers(@RequestParam(required = false) String surname,
                                          @RequestParam(required = false) String city) throws JsonProcessingException {
        return customerService.getAllCustomer(surname, city);
    }
}
