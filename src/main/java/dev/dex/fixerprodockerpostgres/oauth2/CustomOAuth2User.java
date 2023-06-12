package dev.dex.fixerprodockerpostgres.oauth2;

import org.springframework.security.core.*;
import org.springframework.security.oauth2.core.user.*;

import java.io.*;
import java.util.*;

public class CustomOAuth2User implements OAuth2User, Serializable {
    private OAuth2User oauth2User;
    private Collection<GrantedAuthority> authorities;

    public CustomOAuth2User(OAuth2User oauth2User, Collection<GrantedAuthority> authorities) {
        this.oauth2User = oauth2User;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getUsername() {
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        return oauth2User.getAttribute("email");
    }
}
