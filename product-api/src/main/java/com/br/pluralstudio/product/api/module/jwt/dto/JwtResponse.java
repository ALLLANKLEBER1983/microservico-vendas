package com.br.pluralstudio.product.api.module.jwt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.jsonwebtoken.Claims;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Integer id;
    private String name;
    private String email;

    public static JwtResponse getUser(Claims jwtClaims){
        try {
            return new ObjectMapper().convertValue(jwtClaims.get("authUser"),JwtResponse.class);

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
