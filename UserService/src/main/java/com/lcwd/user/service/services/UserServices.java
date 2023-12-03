package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserServices {

    //All User related operations


    //create user
    public User saveUser(User user);

    public List<User> getAllUSers();


    public User getuser(String userid);

}
