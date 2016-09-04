/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.confirm_order;

import com.mac.assignment.app.confirm_order.model.Order;
import com.mac.assignment.app.confirm_order.request.ConfirmOrderRequest;
import com.mac.assignment.app.confirm_order.request.ConfirmOrderRespond;
import com.mac.assignment.app.confirm_order.request.OrderInformationRespond;
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
public class ConfirmOrderController {

    @Autowired
    private ConfirmOrderService orderInformationService;

    @RequestMapping(value = "/order-information", method = RequestMethod.POST)
    public OrderInformationRespond findAll() {
        UserImpl user = (UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Order> orderInformations = orderInformationService.findBySupplier(user.getIndexNo());

        OrderInformationRespond informationRespond = new OrderInformationRespond();
        informationRespond.setOrders(orderInformations);

        return informationRespond;
    }

    @RequestMapping(value = "/confirm-order", method = RequestMethod.POST)
    public ConfirmOrderRespond confirmOrder(@RequestBody ConfirmOrderRequest confirmOrderRequest) {
        return orderInformationService.confirmOrder(confirmOrderRequest.getOrder(), confirmOrderRequest.getCustomer());
    }

}
