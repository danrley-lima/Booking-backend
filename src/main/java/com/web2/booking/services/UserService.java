package com.web2.booking.services;

import com.web2.booking.models.UserModel;
import com.web2.booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }
}
