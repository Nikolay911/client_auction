package com.example.auction1_client.client_customerController;

import com.example.auction1_client.client_services.login.GeneralService;
import org.json.simple.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeneralRestContoller {

    private GeneralService generalService;

    public GeneralRestContoller(GeneralService generalService) {
        this.generalService = generalService;
    }


    @GetMapping("/client/users")
    public JSONObject getUsers() {
        return generalService.getUsers();
    }

    @PostMapping("/user")
    public String postUser(@Nullable @RequestBody JSONObject body) throws IOException {
        return generalService.postUser(body);
    }
}
