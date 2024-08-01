package com.home.service;

import com.home.entity.Home;
import com.home.payload.HomeDto;

import java.util.List;

public interface HomeService {


    List<HomeDto> getAllHomes(int pageNo, int pageSize, String sortBy);
    HomeDto createHome(Home home);
    HomeDto getHomeById(Long id);
}
