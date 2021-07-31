package com.example.auction1_client.client_services;

import com.example.auction1_client.client_models.Customer;
import com.example.auction1_client.client_models.Location;
import com.example.auction1_client.url_config.URLconfigProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerService extends RestTemplateService {

    private final URLconfigProperties urLconfigProperties;

    public CustomerService(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }

    public Customer getCustomer(int idCustomer) throws JsonProcessingException {

        Customer customer = restTemplate.getForObject(urLconfigProperties.getGetCustomer() + idCustomer, Customer.class);
        System.out.println(customer.toString());

        List<Location> locationList = customerLocations(idCustomer);

        customer.setLocations(locationList);

        return customer;
    }

    public JsonNode createCustomer(JsonNode body) {

        JsonNode cust = restTemplate.postForObject(urLconfigProperties.getCreateCustomer(), body, JsonNode.class);

        return cust;
    }

    public List<Customer> getAllCustomer() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject(urLconfigProperties.getGetAllCustomerWithLocations(), String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<Customer> allCustomer = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Customer.class));

        List<Customer> customerWithLocations = allCustomer.stream().peek(customer -> {
            try {
                customer.setLocations(customerLocations(customer.getId()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());

        return customerWithLocations;
    }

    public List<Location> customerLocations(int idCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject(urLconfigProperties.getGetCustomerLocations()+idCustomer , String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<Location> customerLocations = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Location.class));

        return customerLocations;
    }


    public List<Customer> selectSurnameCustomer(String sn) throws JsonProcessingException {

        List<Customer> cst = getAllCustomer();

        List<Customer> ListOfPersons =
                cst.stream().filter(x-> x.getSurname().equals(sn)).collect(Collectors.toList());

        return ListOfPersons;
    }

    public List<Customer> selectCityCustomer(String city) throws JsonProcessingException {

        List<Customer> cst = getAllCustomer();
        List<Customer> listCL1 = cst.stream().filter((x) -> x.getLocations().stream()
                .filter(y -> y.getCity().equals(city)).count()>0).collect(Collectors.toList());

        return listCL1;

    }

    public List<Customer> getAllCustomerPaginated(int page) throws JsonProcessingException {
        int sizePage = 3;

        List<Customer> list = new ArrayList<Customer>( getAllCustomer());
        if(sizePage*page > list.size() && sizePage*page-sizePage < list.size()) return list.subList(sizePage*page-sizePage, list.size());
        if(sizePage>list.size() || sizePage*page > list.size()) return list;

        return list.subList(sizePage*page-sizePage, sizePage*page);
    }

    /*public List<CustomerInf> clientAllCustomerWithLocation() throws JsonProcessingException {                         //заменена на getAllCustomer()---------------------------------------
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject("http://localhost:9091/auction/allcustomerinf", String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<CustomerInf> allCustomer = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerInf.class));

        return allCustomer;
    }*/
}
