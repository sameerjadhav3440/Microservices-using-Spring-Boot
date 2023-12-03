package com.lcwd.rating.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RatingServiceIMPL implements RatingService{

    @Autowired
    private RatingRepo repo;

    @Override
    public Rating saveRating(Rating rating) {
        return repo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getbyuserID(String userid) {
        return repo.findByUserId(userid);
    }

    @Override
    public List<Rating> getbyhotel(String hotelid) {
        return repo.findByHotelId(hotelid);
    }
}
