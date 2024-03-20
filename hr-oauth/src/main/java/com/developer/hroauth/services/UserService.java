package com.developer.hroauth.services;

import com.developer.hroauth.entities.User;
import com.developer.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        try {
            User user = userFeignClient.findUserByEmail(email).getBody();
            if (Objects.isNull(user)) {
                logger.error("Email not found: " + email);
                throw new IllegalArgumentException("Email not found: " + email);
            }
            logger.info("Email found: " + user.getEmail());
            return user;
        } catch (Exception e) {
            logger.error("Error while finding user by email: " + email, e);
            throw new RuntimeException("Error while finding user by email: " + email, e);
        }
    }
}
