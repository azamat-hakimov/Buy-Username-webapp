package com.example.buyusername.service_impl;

import org.springframework.stereotype.Service;

import com.example.buyusername.entity.User;
import com.example.buyusername.repository.UserRepository;
import com.example.buyusername.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUsernameById(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
    
}
