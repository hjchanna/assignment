/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.config.security;

import com.mac.assignment.config.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mohan
 */
public interface SecurityRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
