package com.br.pluralstudio.product.api.module.jwt.dto;

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
            return JwtResponse.
                    builder().
                    id((Integer) jwtClaims.get("id")).
                    name((String) jwtClaims.get("name")).
                    email((String) jwtClaims.get("email")).
                    build();

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
