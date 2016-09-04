/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.config.web_socket;

import com.mac.assignment.app.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author Mohan
 */
public class WebSocketMessageUtil {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendAlert(Integer user) {
        simpMessagingTemplate.convertAndSend("/alert/" + user, new Alert("new alert"));
    }

    public void placeOrderMessage(Integer supplier) {
        notificationService.newPlaceOrderNotification(supplier);

        //send alert
        sendAlert(supplier);
    }

    public void confirmOrderMessage(Integer customer) {
        notificationService.newConfirmOrderNotification(customer);

        //send alert
        sendAlert(customer);
    }

}
