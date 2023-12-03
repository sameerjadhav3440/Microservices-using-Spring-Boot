package com.lcwd.user.service.controller;


import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices service;



    //create user

    @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
      User user1 = service.saveUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(user1);
  }


  int retryCount = 1;

    //get single user
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback") //commented to implement retry concept
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback") // Commented to implement Rate Limiter

    @RateLimiter( name ="userRateLimiter" , fallbackMethod = "ratingHotelFallback" )
    public ResponseEntity<User> getUserbyId(@PathVariable("userId") String userId){

        //logging below msg for retry concept
        System.out.println("Retry cout  "+retryCount);
        retryCount++;

        User user1 = service.getuser(userId);
        return ResponseEntity.ok(user1);
    }






    //Creating fallback method for CircuitBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        System.out.println("Fallback is executed because some micro service is down : "+ex.getMessage());

        //setting the values to user
        User user = User.builder()
                .name("Sameer fallback call")
                .email("sam@gmail.com")
                .about("Fallback is calling")
                .userid("1")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //get All users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {

        List<User> userlist = service.getAllUSers();

    return ResponseEntity.ok(userlist);
    }


}
