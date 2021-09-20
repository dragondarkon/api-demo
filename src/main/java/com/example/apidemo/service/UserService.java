package com.example.apidemo.service;

import com.example.apidemo.common.UserNotFoundException;
import com.example.apidemo.entity.User;
import com.example.apidemo.gateway.PostGateway;
import com.example.apidemo.gateway.PostResponse;
import com.example.apidemo.model.UserModel;
import com.example.apidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostGateway postGateway;

    public User inquiryUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserModel create(UserModel user) {
        User newUser = new User(user.getName(), user.getState(), user.getMessage());
        newUser = userRepository.save(newUser);
        return new UserModel(newUser.getId(),
                newUser.getName(),
                newUser.getState());
    }

    public void setUserState(User user){
        user.setState("state-2");
        userRepository.save(user);
    }

    public PostResponse sendMessage(User user, String message){
        String state = user.getState();
        PostResponse response = new PostResponse();
        if (state.equals("state-1")) {
            System.out.println("state-1");
            user.setMessage(message);
            userRepository.save(user);
            response = postGateway.sendMessageToEndPoint1(user.getId(),user.getState(),user.getMessage());
        } else if (state.equals("state-2")) {
            System.out.println("state-2");
            user.setMessage(message);
            userRepository.save(user);
            response = postGateway.sendMessageToEndPoint2(user.getId(),user.getState(),user.getMessage());
        } else {
            System.out.println("state-0");
            response = postGateway.getPostById(user.getId()).get();
        }
        return response;
    }
}
