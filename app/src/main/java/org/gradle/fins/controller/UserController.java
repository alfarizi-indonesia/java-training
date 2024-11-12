package org.gradle.fins.controller;

import org.gradle.fins.model.UserModel;
import org.gradle.fins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/auth")
    @CrossOrigin
    public String getAuthDescription(Authentication authentication) {
        return "Hello " + authentication.getName() + ", You try to access User API with Authorization only for ADMIN";
    }

    @GetMapping("/user")
    @CrossOrigin
    public List<UserModel> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{userId}")
    @CrossOrigin
    public ResponseEntity<UserModel> getUserById(@PathVariable String userId) {
        Optional<UserModel> userModel = userService.getUserById(userId);
        return userModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/like/{userName}")
    @CrossOrigin
    public List<UserModel> getUserLikeUserName(@PathVariable String userName) {
        List<UserModel> userModelList = userService.getUserLikeUserName(userName);
        return userModelList;
    }

    @PostMapping("/user/create")
    @CrossOrigin
    public ResponseEntity createUser(@RequestBody UserModel userModel) {
        ResponseEntity responseEntity;
        String response = "";

        try
        {
            Optional<UserModel> findUserModel = userService.getUserById(userModel.getUserId());
            if (!findUserModel.isPresent())
            {
                userService.createUser(userModel);

                response = userModel.getUserId() + " created successfully";
                responseEntity = ResponseEntity.ok(response);
            }
            else
            {
                response = userModel.getUserId() + " already existed";
                responseEntity = new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception exc)
        {
            response = exc.getMessage();
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping("/user/update")
    @CrossOrigin
    public ResponseEntity updateUser(@RequestBody UserModel userModel) {
        ResponseEntity responseEntity;
        String response = "";

        try
        {
            Optional<UserModel> findUserModel = userService.getUserById(userModel.getUserId());
            if (findUserModel.isPresent())
            {
                UserModel newUserModel = findUserModel.get();
                newUserModel.setUserName(userModel.getUserName());
                userService.updateUser(newUserModel);

                response = userModel.getUserId() + " updated successfully";
                responseEntity = ResponseEntity.ok(response);
            }
            else
            {
                response = userModel.getUserId() + " not found";
                responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exc)
        {
            response = exc.getMessage();
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("/user/delete")
    @CrossOrigin
    public ResponseEntity deleteUser(@RequestBody UserModel userModel) {
        ResponseEntity responseEntity;
        String response = "";

        try
        {
            Optional<UserModel> findUserModel = userService.getUserById(userModel.getUserId());
            if (findUserModel.isPresent())
            {
                userService.deleteUser(userModel);

                response = userModel.getUserId() + " deleted successfully";
                responseEntity = ResponseEntity.ok(response);
            }
            else
            {
                response = userModel.getUserId() + " does not exist";
                responseEntity = new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception exc)
        {
            response = exc.getMessage();
            responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}