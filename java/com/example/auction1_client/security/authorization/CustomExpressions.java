package com.example.auction1_client.security.authorization;

import com.example.auction1_client.client_models.User;
import com.example.auction1_client.client_services.login.GeneralService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomExpressions {

    public final GeneralService generalService;

    public CustomExpressions(GeneralService generalService) {
        this.generalService = generalService;
    }


    public boolean isHisCustomer(int customerId, HttpServletRequest request){
        String username = request.getRemoteUser();
        User user = generalService.findByUsername(username);
        if(user.getCustomerId()==customerId || user.getRole().equals("ROLE_admin"))
            return true;
        return false;
    }

}
