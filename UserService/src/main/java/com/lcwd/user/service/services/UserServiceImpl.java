package com.lcwd.user.service.services;

import com.lcwd.user.service.Exceptions.ResourceNotFoundException;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserServices{


    @Autowired
    private userRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;


    //Autowire feign client service
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {

        // Generate Unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserid(randomUserId);


        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUSers() {
        return userRepo.findAll();
    }

    @Override
    public User getuser(String userid) {

        //get user from database with the help of user repository
        User user = userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException(" user not found for given id "+userid));

        //fetch the ratings for the above user from RATING-SERVICE
        //URL = http://localhost:8083/ratings/users/1

        Rating[] arrOfRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserid() , Rating[].class);
        System.out.println("listOfRatings------------>>"+arrOfRatings.toString());

        List<Rating> listofRatingconvertinlist = Arrays.stream(arrOfRatings).toList();

        List<Rating> ratinglistwithHotel = listofRatingconvertinlist.stream().map(rating ->{

            System.out.println("rating.getHotelId()-------->>"+rating.getHotelId());


            //call hotel service and get the hotel details and set it in the rating model
            //We are using here restTemplate
            //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(),Hotel.class);

                                //OR

            //We are trying to use feign client //open feign
            Hotel hotel =hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);
          return rating;
        }).collect(Collectors.toList());

        //fetch the Hotel for the above ratings from HOTEL-SERVICE

        System.out.println("ratinglistwithHotel-------->>"+ratinglistwithHotel.toString());

        user.setRating(ratinglistwithHotel);

        return user;
    }
}
