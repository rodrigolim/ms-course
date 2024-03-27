package com.developer.hroauth.feignclients;

import com.developer.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/hr-user/api/users")
public interface UserFeignClient {
     @GetMapping("/search")
     ResponseEntity<User> findUserByEmail(@RequestParam String email) ;
}
