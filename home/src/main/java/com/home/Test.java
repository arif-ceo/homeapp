package com.home;
import java.util.Date;

import com.home.payload.HomeDto;

public class Test {

    public static void main(String[] args) {
        HomeDto home = new HomeDto();
        home.setId(0L);
        home.setName("");
        home.setLocation("");
        home.setPinCode("");
        home.setDate(new Date());


    }
}
