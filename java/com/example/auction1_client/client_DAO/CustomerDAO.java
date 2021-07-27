package com.example.auction1_client.client_DAO;

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
public class CustomerDAO {

    private final URLconfigProperties urLconfigProperties;

    public CustomerDAO(URLconfigProperties urLconfigProperties) {
        this.urLconfigProperties = urLconfigProperties;
    }

    public Customer clientCustomer(int idCustomer) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(urLconfigProperties.getGetCustomer() + idCustomer, Customer.class);
        System.out.println(customer.toString());

        List<Location> locationList = clientCustomerLocations(idCustomer);

        customer.setLocations(locationList);

        return customer;

    }

    public JsonNode clientCreateCustomer(JsonNode body) {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode cust = restTemplate.postForObject(urLconfigProperties.getCreateCustomer(), body, JsonNode.class);

        return cust;
    }

    public List<Customer> clientAllCustomer() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject(urLconfigProperties.getGetAllCustomerWithLocations(), String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<Customer> allCustomer = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Customer.class));

        int[] countCustomers = new int[allCustomer.size()];

        for (int i = 0; i < countCustomers.length; i++) {
            countCustomers[i] = allCustomer.get(i).getId();
        }
        List<Customer> customerWithLocations = new ArrayList<>();

        for (int j = 0; j < countCustomers.length; j++){
            customerWithLocations.add(clientCustomer(countCustomers[j]));
        }

        return customerWithLocations;
    }

    public List<Location> clientCustomerLocations(int idCustomer) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject(urLconfigProperties.getGetCustomerLocations()+idCustomer , String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<Location> customerLocations = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Location.class));

        return customerLocations;
    }


    public List<Customer> selectSurnameCustomer(String sn) throws JsonProcessingException {

        List<Customer> cst = clientAllCustomer();

        List<Customer> ListOfPersons =
                cst.stream().filter(x-> x.getSurname().equals(sn)).collect(Collectors.toList());

        return ListOfPersons;
    }

    public List<Customer> selectCityCustomer(String city) throws JsonProcessingException {

        List<Customer> cst = clientAllCustomer();
        List<Customer> listCL1 = cst.stream().filter((x) -> x.getLocations().stream()
                .filter(y -> y.getCity().equals(city)).count()>0).collect(Collectors.toList());

        return listCL1;

    }

    public List<Customer> getAllCustomerPaginated(int page) throws JsonProcessingException {
        int sizePage = 3;

        List<Customer> list = new ArrayList<Customer>( clientAllCustomer());
        if(sizePage*page > list.size() && sizePage*page-sizePage < list.size()) return list.subList(sizePage*page-sizePage, list.size());
        if(sizePage>list.size() || sizePage*page > list.size()) return list;

        return list.subList(sizePage*page-sizePage, sizePage*page);
    }

    /*public List<CustomerInf> clientAllCustomerWithLocation() throws JsonProcessingException {                         //заменена на clientAllCustomer()---------------------------------------
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = restTemplate.getForObject("http://localhost:9091/auction/allcustomerinf", String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<CustomerInf> allCustomer = objectMapper.convertValue(jsonNode,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerInf.class));

        return allCustomer;
    }*/
}
