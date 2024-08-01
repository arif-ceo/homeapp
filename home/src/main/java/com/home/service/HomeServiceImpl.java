package com.home.service;

import com.home.entity.Home;
import com.home.exception.ResourceNotFoundException;
import com.home.payload.HomeDto;
import com.home.repository.HomeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private HomeRepository homeRepository;

    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public HomeDto createHome(Home home) {
        Home save = homeRepository.save(home);
        return entityToDto(save);

    }

    @Override
    public List<HomeDto> getAllHomes(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Home> all = homeRepository.findAll(pageable);
        List<Home> content = all.getContent();
        return content.stream().map(h -> entityToDto(h)).collect(Collectors.toList());
    }

    @Override
    public HomeDto getHomeById(Long id) {
        Home home = null;
        Optional<Home> byId = homeRepository.findById(id);
        if (byId.isPresent()){
           home = byId.get();

        }else {
            throw new ResourceNotFoundException("User Not Found With Id: "+id);
        }
        return entityToDto(home);
    }

    public  HomeDto entityToDto(Home home){
        HomeDto dto = new HomeDto();
        dto.setId(home.getId());
        dto.setName(home.getName());
        dto.setLocation(home.getLocation());
        dto.setPinCode(home.getPinCode());
        dto.setDate(new Date());
        return dto;
    }
}
