package dev.dex.fixerprodockerpostgres.oauth2;

import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private AccountService accountService;

    @Autowired
    public CustomOAuth2UserService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        Collection<GrantedAuthority> authorities = accountService.findAuthoritiesByUsername(
                user.getAttribute("name"));
        return new CustomOAuth2User(user, authorities);
    }
}
