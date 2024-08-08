package org.harmonicatabs.service.session;

import org.harmonicatabs.model.entity.Role;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarmonicaTabsUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public HarmonicaTabsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+ username+ " not found!"));
        return new User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name())).collect(Collectors.toList());
    }
}
