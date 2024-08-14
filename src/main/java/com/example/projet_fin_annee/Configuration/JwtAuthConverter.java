package com.example.projet_fin_annee.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toSet;


public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
    	System.out.println(source);

        return new JwtAuthenticationToken(
                source,
                Stream.concat(
                                new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                                extractResourceRoles(source).stream())
                        .collect(toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        Collection<String> allRoles = new ArrayList<>();
        Collection<String> resourceRoles;
        Collection<String> realmRoles ;

        if(resourceAccess != null && resourceAccess.get("account") != null){
            Map<String,Object> account =  (Map<String,Object>) resourceAccess.get("account");
            if(account.containsKey("roles") ){
                resourceRoles = (Collection<String>) account.get("roles");
                allRoles.addAll(resourceRoles);
            }
        }

        if(realmAccess != null && realmAccess.containsKey("roles")){
            realmRoles = (Collection<String>) realmAccess.get("roles");
            allRoles.addAll(realmRoles);
        }


        return allRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}

