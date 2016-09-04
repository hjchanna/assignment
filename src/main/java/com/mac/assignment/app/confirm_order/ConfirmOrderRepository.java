/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.confirm_order;

import com.mac.assignment.app.confirm_order.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohan
 */
@Repository
public interface ConfirmOrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findBySupplierIndexNoAndStatus(Integer supplierIndexNo, String status);

}
