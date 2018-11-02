package com.petr.petr.service;

import com.petr.petr.persistence.entity.User;
import com.petr.petr.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }



}
