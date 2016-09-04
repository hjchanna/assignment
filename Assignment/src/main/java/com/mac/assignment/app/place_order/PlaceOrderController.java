/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.place_order;

import com.mac.assignment.app.place_order.respond.PlaceOrderRequest;
import com.mac.assignment.app.place_order.respond.PlaceOrderRespond;
import com.mac.assignment.config.security.UserImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PlaceOrderController {

    @Autowired
    private PlaceOrderService orderService;

    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    public PlaceOrderRespond processOrder(@RequestBody List<PlaceOrderRequest> orders) {
        UserImpl user = (UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        orderService.processOrder(user.getIndexNo(), orders);
        PlaceOrderRespond orderRespond = new PlaceOrderRespond("Order submitted successfully.");

        return orderRespond;
    }

}
