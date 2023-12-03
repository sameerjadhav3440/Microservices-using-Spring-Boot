package com.lcwd.rating.Service;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {

    //create
    Rating saveRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by user id
    List<Rating> getbyuserID (String userId);

    // get all by hotel
    List<Rating> getbyhotel( String hotelId);

}
