package com.example.auction1_client.security;

import com.example.auction1_client.client_services.login.GeneralService;
import com.example.auction1_client.client_models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OurUserDetailsService implements UserDetailsService {

    private final GeneralService generalService;

    public OurUserDetailsService(GeneralService generalService) {
        this.generalService = generalService;
    }


    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
        User user = generalService.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }
}
