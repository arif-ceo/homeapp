package com.home.controller;

import com.home.entity.Home;
import com.home.payload.HomeDto;
import com.home.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/homes")
public class HomeController {

    private HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("/add")
    public ResponseEntity<HomeDto> createHome(@RequestBody Home home){
        HomeDto savedHome = homeService.createHome(home);
        return new ResponseEntity<>(savedHome, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public  ResponseEntity<List<HomeDto>> getAllHome(
            @RequestParam( name ="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "2", required=false) int pageSize,
            @RequestParam(name="sortBy" ,defaultValue = "id", required = false) String sortBy
    ){
            List<HomeDto> getAll = homeService.getAllHomes(pageNo,pageSize,sortBy);
            return new ResponseEntity<>(getAll,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HomeDto> getHomeById(@RequestParam Long id){
        HomeDto dto = homeService.getHomeById(id);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
