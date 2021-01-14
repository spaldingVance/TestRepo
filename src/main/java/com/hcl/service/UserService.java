package com.hcl.service;

import com.hcl.dao.UserDao;
import com.hcl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void updateUser(long id, String name, String email, String password) {
        User user = userDao.findById(id).get();
        if (isNotEmpty(name)) user.setName(name);
        if (isNotEmpty(email)) user.setEmail(email);
        if (isNotEmpty(password)) user.setPassword(password);
        userDao.save(user);
    }

    public void updateUser(User updatedUserData) {
        User user = userDao.findById(updatedUserData.getId()).get();
        user = updatedUserData;
        userDao.save(user);
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }
    
    private boolean isNotEmpty(String s) {
        return s != "";
    }


}