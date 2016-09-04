/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.config.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Mohan
 */
public class UserImpl extends User {

    private final Integer indexNo;
    private final String type;

    public UserImpl(Integer indexNo, String userName, String password, String type, List<GrantedAuthority> authorities) {
        super(userName, password, true, true, true, true, authorities);
        this.indexNo = indexNo;
        this.type = type;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public String getType() {
        return type;
    }

}
