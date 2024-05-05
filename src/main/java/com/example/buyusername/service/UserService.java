package com.example.buyusername.service;

import com.example.buyusername.entity.User;

public interface UserService {
    
    User getUserById(long id);

    User getUsernameById(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    boolean usernameExists(String username);

}
