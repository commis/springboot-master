package com.commis.service.user.impl;

import com.commis.dao.UserRepository;
import com.commis.dao.entity.UserBean;
import com.commis.service.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserBean> list() {
        return userRepository.findAll();
    }

    @Override
    public UserBean findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
