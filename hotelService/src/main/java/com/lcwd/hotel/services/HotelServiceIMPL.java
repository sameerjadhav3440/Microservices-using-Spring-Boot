package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class HotelServiceIMPL implements HotelService{

    @Autowired
    private HotelRepo repo;


    @Override
    public Hotel createHotel(Hotel hotel) {

        String hotelid = UUID.randomUUID().toString();
        hotel.setId(hotelid);
       return repo.save(hotel);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
       return repo.findById(hotelId).orElseThrow( ()-> new ResourceNotFoundException(" The Hotel Resource Not found in the Database for given hotel id !!!"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repo.findAll();
    }
}
