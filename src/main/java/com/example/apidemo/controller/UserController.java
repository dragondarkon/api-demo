package com.example.apidemo.controller;

import com.example.apidemo.entity.User;
import com.example.apidemo.gateway.PostResponse;
import com.example.apidemo.model.UserListResponse;
import com.example.apidemo.model.UserModel;
import com.example.apidemo.model.UserRequest;
import com.example.apidemo.model.UserResponse;
import com.example.apidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello" )
    public String echo() {
        return "Hello World!";
    }

    @GetMapping(value = "/user/{id}")
    public UserResponse getUser(@PathVariable int id) {
        // Call service
        User user = userService.inquiryUserById(id);
        // Mapping to response
        UserResponse userResponse
                = new UserResponse(id, user.getName(), user.getState(),user.getMessage());
        return userResponse;
    }

//    @PostMapping(value = "/senMessageToUser")
//    public UserResponse senMessageToUser(@RequestBody UserRequest userRequest) {
//        User user = userService.inquiryUserById(userRequest.getId());
//        user = userService.sendMessage(user,userRequest.getMessage());
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(user.getId());
//        userResponse.setName(user.getName());
//        userResponse.setState(user.getState());
//        userResponse.setMessage(user.getMessage());
//        return userResponse;
//    }

    @PostMapping(value = "/senMessageToUser")
    public PostResponse senMessageToUser(@RequestBody UserRequest userRequest) {
        User user = userService.inquiryUserById(userRequest.getId());
        PostResponse postResponse = userService.sendMessage(user,userRequest.getMessage());
        return postResponse;
    }

    @PostMapping(value = "/toggleUsers")
    public UserResponse toggleUser(@RequestBody UserRequest userRequest) {
        User user = userService.inquiryUserById(userRequest.getId());
        userService.setUserState(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setState(user.getState());
        return userResponse;
    }

    @PostMapping(value = "/user")
    public UserResponse createNewUser(@RequestBody UserRequest newUserRequest) {
        UserModel newUser = userService.create(
                new UserModel(newUserRequest.getName(), newUserRequest.getState(),newUserRequest.getMessage()));
        return new UserResponse(
                newUser.getId(), newUser.getName(), newUser.getState(), newUser.getMessage());
    }
}
