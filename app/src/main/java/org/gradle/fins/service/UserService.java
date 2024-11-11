package org.gradle.fins.service;

import org.gradle.fins.model.UserModel;
import org.gradle.fins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<UserModel> getUserLikeUserName(String userName) {
        return userRepository.findLikeUserName(userName);
    }

    public void createUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    public void updateUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    public void deleteUser(UserModel userModel) {
        userRepository.deleteById(userModel.getUserId());
    }
}