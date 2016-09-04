/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.place_order;

import com.mac.assignment.app.place_order.model.Order;
import com.mac.assignment.app.place_order.respond.PlaceOrderRequest;
import com.mac.assignment.config.web_socket.WebSocketMessageUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mohan
 */
@Service
@Transactional
public class PlaceOrderService {

    public static final String ORDER_STATUS_PENDING = "PENDING";

    @Autowired
    private PlaceOrderRepository orderRepository;

    @Autowired
    private WebSocketMessageUtil webSocketMessageUtil;

    @Async("threadPoolTaskExecutor")
    public void processOrder(Integer user, List<PlaceOrderRequest> orders) {

        Set<Integer> notificationSuppliers = new HashSet<>();

        //save orders
        for (PlaceOrderRequest orderRequest : orders) {

            //create new order
            Order order = new Order();
            order.setItem(orderRequest.getItem());
            order.setQuantity(orderRequest.getQuantity());
            order.setCustomer(user);
            order.setSupplier(orderRequest.getSupplier());
            order.setStatus(ORDER_STATUS_PENDING);

            //add to notification queue
            notificationSuppliers.add(order.getSupplier());

            //save order
            orderRepository.save(order);

            //demo purpose
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
        }

        //notification
        for (Integer supplier : notificationSuppliers) {
            webSocketMessageUtil.placeOrderMessage(supplier);
        }
    }
}
