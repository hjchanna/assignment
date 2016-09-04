/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.confirm_order;

import com.mac.assignment.app.confirm_order.model.Order;
import com.mac.assignment.app.confirm_order.request.ConfirmOrderRespond;
import com.mac.assignment.config.web_socket.WebSocketMessageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mohan
 */
@Service
@Transactional
public class ConfirmOrderService {

    public static final String ORDER_STATUS_PENDING = "PENDING";
    public static final String ORDER_STATUS_CONFIRM = "CONFIRM";

    @Autowired
    private ConfirmOrderRepository confirmOrderRepository;

    @Autowired
    private WebSocketMessageUtil webSocketMessageUtil;

    public List<Order> findBySupplier(Integer supplier) {
        return confirmOrderRepository.findBySupplierIndexNoAndStatus(supplier, ORDER_STATUS_PENDING);
    }

    public ConfirmOrderRespond confirmOrder(Integer orderId, Integer customer) {
        Order order = confirmOrderRepository.findOne(orderId);
        order.setStatus(ORDER_STATUS_CONFIRM);
        confirmOrderRepository.saveAndFlush(order);

        webSocketMessageUtil.confirmOrderMessage(customer);

        return new ConfirmOrderRespond("Order confirmed successfully..!");
    }

}
