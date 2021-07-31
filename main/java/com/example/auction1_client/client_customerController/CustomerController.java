package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.CustomerService;
import com.example.auction1_client.client_models.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController()
@RequestMapping("/client")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customer")
    public JsonNode createCustomer(@Nullable @RequestBody JsonNode body) {
        return customerService.createCustomer(body);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(@RequestParam int page) throws JsonProcessingException {

        if(page > 0) return customerService.getAllCustomerPaginated(page);
        return customerService.getAllCustomer();
    }

    @GetMapping("/selectsurname")
    public List<Customer> selectNameCustomer(@RequestParam String selectsurname) throws JsonProcessingException {
        return customerService.selectSurnameCustomer(selectsurname);
    }

   /* @GetMapping("/customersandhislocations")
    public List<CustomerInf> CustomersAndHisLocations() throws JsonProcessingException {
        return customerDAO.clientAllCustomerWithLocation();
    }*/

    @GetMapping("/selectsurcity")
    public List<Customer> selectCity(@RequestParam String city) throws JsonProcessingException {
        return customerService.selectCityCustomer(city);
    }

}
