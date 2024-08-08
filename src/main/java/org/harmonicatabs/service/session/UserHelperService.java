package org.harmonicatabs.service.session;

import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.model.enums.RoleEnum;
import org.harmonicatabs.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserHelperService {

    private final UserRepository userRepository;

    public UserHelperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser(){
        return this.userRepository.findByUsername(getUserDetails().getUsername()).orElse(null);
    }

    public boolean hasRole(String role){
        return getUserDetails().getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_"+ role));
    }
    public UserDetails getUserDetails(){
        return (UserDetails) getAuthentication().getPrincipal();
    }
    public boolean isAuthenticated(){
        return getAuthentication().isAuthenticated();
    }

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
