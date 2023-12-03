package com.lcwd.hotel.Controller;


import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService service;


    //create
    @PostMapping
    public ResponseEntity<Hotel> create_Hotel(@RequestBody Hotel hotel){

        Hotel h = service.createHotel(hotel);
        //return ResponseEntity.ok(h);
        return ResponseEntity.status(HttpStatus.CREATED).body(h);

    }


    //get Hotel by Id
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> create_Hotel(@PathVariable ("hotelId") String hotelId){

        Hotel h = service.getHotelById(hotelId);
        //return ResponseEntity.ok(h);
        return ResponseEntity.status(HttpStatus.OK).body(h);

    }



    //get All Hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> list = service.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
