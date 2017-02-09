package edu.hanoi.service;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by trungdovan on 12/1/16.
 */
public class JazzSocialAuthServiceRegistry extends SocialAuthenticationServiceRegistry {

    private List<SocialAuthenticationService<?>> authenticationServices;

    public JazzSocialAuthServiceRegistry(List<SocialAuthenticationService<?>> authenticationServices){
        this.authenticationServices = authenticationServices;
    }

    @PostConstruct
    public void init(){
        if (authenticationServices == null)
            return;
        authenticationServices.forEach(auth -> super.addAuthenticationService(auth));
    }
}
