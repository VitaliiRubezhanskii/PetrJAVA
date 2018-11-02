package com.petr.petr.service;

import com.petr.petr.persistence.entity.User;

public interface UserService {
    User getByLogin(String login);
}
