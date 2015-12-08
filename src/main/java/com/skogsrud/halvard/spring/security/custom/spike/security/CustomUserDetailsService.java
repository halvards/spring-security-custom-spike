package com.skogsrud.halvard.spring.security.custom.spike.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Calls an external web service to retrieve user information.
 *
 * This information will be used to make authentication decisions.
 */
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * Make call to external service here. Response contains information to populate UserDetails object.
         *
         * Throw UsernameNotFoundException if user cannot be found.
         */
//        return new CustomUser(username, "Joe", "Bloggs", true);
        return new User(username, "Bloggs", Collections.emptyList());
    }
}
