/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.place_order;

import com.mac.assignment.app.place_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohan
 */
@Repository
public interface PlaceOrderRepository extends JpaRepository<Order, Integer> {

}
