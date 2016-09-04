/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.login;

import com.mac.assignment.app.login.respond.LoginRequest;
import com.mac.assignment.app.login.respond.LoginRespond;
import com.mac.assignment.config.security.UserImpl;
import java.util.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohan
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginRespond login(@RequestBody LoginRequest loginRequest) {
        String key = loginRequest.getUsername() + ":" + loginRequest.getPassword();
        String token = Base64.getEncoder().encodeToString(key.getBytes());
        UserImpl user = (UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LoginRespond loginRespond = new LoginRespond(user.getIndexNo(), token, user.getType());

        return loginRespond;
    }

}
