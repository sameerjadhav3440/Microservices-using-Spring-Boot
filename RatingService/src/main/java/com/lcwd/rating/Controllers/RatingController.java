package com.lcwd.rating.Controllers;


import com.lcwd.rating.Service.RatingService;
import com.lcwd.rating.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    private RatingService service;

    //create Rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating r = service.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    //Get All Ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> list = service.getAllRatings();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    //Get All Rating by UserID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUSerID(@PathVariable("userId") String userId){
        List<Rating> list1 = service.getbyuserID(userId);
        return ResponseEntity.status(HttpStatus.OK).body(list1);
    }



    //Get All Rating by Hotel Id
    @GetMapping("hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelID(@PathVariable("hotelId") String hotelId){
        List<Rating> list2 = service.getbyuserID(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(list2);
    }

    
}
