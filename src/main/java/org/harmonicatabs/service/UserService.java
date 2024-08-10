package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.UserLoginDTO;
import org.harmonicatabs.model.dtos.UserRegisterDTO;
import org.harmonicatabs.model.entity.UserEntity;
import org.modelmapper.internal.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public boolean register(UserRegisterDTO userRegisterDTO);
    List<UserEntity> getAllUsers();

    Pair<Boolean, UserEntity> getUser(long id);

    void deleteUserWithId(long id);
}
