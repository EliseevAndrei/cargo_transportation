package com.eliseev.app.services;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserDAO>
        implements UserDetailsService {

    @Autowired
    public UserService(UserDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dao.findByUsername(s);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + s + "' not found");
    }
}

