package com.home.payload;

import lombok.Data;

@Data
public class JWTTokenDto {
    private String type;
    private String token;
}
