package dev.dex.fixerprodockerpostgres.security;

import org.springframework.security.core.*;

public class FixerproGrantedAuthority implements GrantedAuthority {
    private String authority;

    public FixerproGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "FixerproGrantedAuthority{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
