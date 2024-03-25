package com.developer.hroauth.services;

import com.developer.hroauth.entities.User;
import com.developer.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findUserByEmail(email).getBody();
        if (user == null) {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: " + email);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userFeignClient.findUserByEmail(username).getBody();
            if (Objects.isNull(user)) {
                logger.error("Email not found: " + username);
                throw new UsernameNotFoundException("Email not found: " + username);
            }
            logger.info("Email found: " + user.getEmail());
            return user;
        } catch (Exception e) {
            logger.error("Error while finding user by email: " + username, e);
            throw new UsernameNotFoundException("Error while finding user by email: " + username, e);
        }
    }
}
