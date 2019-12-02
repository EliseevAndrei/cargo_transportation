package com.eliseev.app.security;

import com.eliseev.app.repository.custom.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserDAO userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /*User user = userRepository.findByLoginAndPass();
        if (user != null) {
            Set<GrantedAuthority> roles = new HashSet<>();
            for (Role role : user.getRoles()) {
                roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException(
                "User '" + s + "' not found");*/
        return null;
    }


}

