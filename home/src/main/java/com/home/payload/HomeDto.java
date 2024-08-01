package com.home.payload;

import lombok.Data;

import java.util.Date;

@Data
public class HomeDto {

    private Long id;
    private String name;
    private String location;
    private String pinCode;
    private Date date;
}
