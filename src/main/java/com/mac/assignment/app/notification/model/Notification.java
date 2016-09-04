/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.notification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mohan
 */
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer indexNo;
    private Integer user;
    private Integer placeOrder;
    private Integer confirmOrder;

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(Integer placeOrder) {
        this.placeOrder = placeOrder;
    }

    public Integer getConfirmOrder() {
        return confirmOrder;
    }

    public void setConfirmOrder(Integer confirmOrder) {
        this.confirmOrder = confirmOrder;
    }

}
