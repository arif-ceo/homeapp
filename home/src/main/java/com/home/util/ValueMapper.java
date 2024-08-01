package com.home.util;

import com.home.entity.Home;
import com.home.payload.HomeDto;

import java.util.Date;

public class ValueMapper {

    public HomeDto homeToHomeDto(Home home){
        HomeDto homeDto = new HomeDto();
        homeDto.setId(home.getId());
        homeDto.setName(home.getName());
        homeDto.setLocation(home.getLocation());
        homeDto.setPinCode(home.getPinCode());
        homeDto.setDate(new Date());
        return homeDto;
    }
}
