package com.csp.api.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.csp.api.entity.UserCredentials;

public class CustomUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;

    public CustomUser(UserCredentials userCredential) {
        this.username = userCredential.getUserName();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
