package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    public boolean register(UserRegisterDTO userRegisterDTO);
}
