package com.example.auction1_client.client_services.login;

import com.example.auction1_client.client_models.User;
import com.example.auction1_client.security.WebSecurityConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneralService {

    private final PasswordEncoder passwordEncoder;

    public GeneralService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public JSONObject getUsers() {

        JSONParser parser = new JSONParser();
        JSONObject arrJson = null;
        try (FileReader reader = new FileReader("users.json")) {

            arrJson = (JSONObject) parser.parse(reader);

        } catch (Exception e) {
            System.out.println("Parsing error" + e.toString());
        }
        return arrJson;
    }

    public JSONObject postUser(JsonNode body) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(body, User.class);

        if(findByUsername(user.getLogin()) != null){
            JSONObject loginError = new JSONObject();
            loginError.put("login", "Пользователь с таким именем уже существует");
            return loginError;
        }


        JSONArray jsonArray = (JSONArray) getUsers().get("users");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", user.getLogin());
        jsonObject.put("password", passwordEncoder.encode(user.getPassword()));

        jsonArray.add(jsonObject);

        JSONObject jsonName = new JSONObject();
        jsonName.put("users", jsonArray);

        File file = new File("users.json");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(jsonName.toJSONString());
        printWriter.close();

        return jsonName;
    }

    public User findByUsername(String username) {

        JSONArray jsonArray = (JSONArray) getUsers().get("users");

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> user = objectMapper.convertValue(jsonArray, objectMapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, User.class));

        List<User> userLog = user.stream().filter(x-> x.getLogin().equals(username)).collect(Collectors.toList());
        if(userLog.size()==1){
            User findUser = new User(userLog.get(0).getLogin(), userLog.get(0).getPassword());
            return findUser;
        }
        else{
            User badUser = null;
            return badUser;
        }
    }
}
