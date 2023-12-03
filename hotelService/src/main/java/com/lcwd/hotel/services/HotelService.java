package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface  HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get Hotel by id
    Hotel getHotelById(String hotelId);

    //get All Hotel list
    List<Hotel> getAllHotels();


}
