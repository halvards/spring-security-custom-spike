package com.skogsrud.halvard.spring.security.custom.spike.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

/**
 * Use a class such as this if you want to keep more information about the authenticated user.
 */
public class CustomUser extends User {
    private final String givenName;
    private final String familyName;

    public CustomUser(String username, String givenName, String familyName, boolean enabled) {
        super(username, familyName, enabled, true, true, true, Arrays.asList(new SimpleGrantedAuthority("USER")));
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }
}
