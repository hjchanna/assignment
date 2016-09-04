/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.confirm_order.request;

import com.mac.assignment.app.confirm_order.model.Order;
import java.util.List;

/**
 *
 * @author Mohan
 */
public class OrderInformationRespond {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
