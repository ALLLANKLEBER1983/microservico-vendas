package com.br.pluralstudio.product.api.module.jwt.service;

import com.br.pluralstudio.product.api.config.exception.AuthorizationException;
import com.br.pluralstudio.product.api.module.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class JwtService {

    private static final String BEARER = "bearer ";

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token){
        try {
            var accessToken = extractToken(token);
            var clains = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            var user = JwtResponse.getUser(clains);
            if(isEmpty(user) || isEmpty(user.getId())){
               throw new AuthorizationException("The user is not valid.");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            throw new AuthorizationException("Error while trying to proccess the Access Token.");

        }
    }

    private String extractToken(String token){
        if(isEmpty(token)){
            throw new AuthorizationException("The access token was not informed");
        }
        if(token.toLowerCase().contains(BEARER)){
            return token.replace(BEARER, Strings.EMPTY);
        }
        return token;

    }

}
