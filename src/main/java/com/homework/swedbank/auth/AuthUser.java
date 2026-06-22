package com.homework.swedbank.auth;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.homework.swedbank.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthUser implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();
    }

    @Override
    public @Nullable String getPassword() {

        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

}
