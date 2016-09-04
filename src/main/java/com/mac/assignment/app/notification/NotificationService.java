/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.notification;

import com.mac.assignment.app.notification.model.Notification;
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
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    
    public List<Notification> getNotification(Integer user) {
        return notificationRepository.findByUser(user);
    }

    public void consumeNotifications(Integer user) {
        notificationRepository.deleteByUser(user);
    }

    public void newPlaceOrderNotification(Integer user) {
        List<Notification> notifications = notificationRepository.findByUser(user);

        Notification notification;
        if (!notifications.isEmpty()) {
            notification = notifications.get(0);
            notification.setPlaceOrder(notification.getPlaceOrder() + 1);
        } else {
            notification = new Notification();
            notification.setUser(user);
            notification.setPlaceOrder(1);
            notification.setConfirmOrder(0);
        }

        notificationRepository.save(notification);
    }

    public void newConfirmOrderNotification(Integer user) {
        List<Notification> notifications = notificationRepository.findByUser(user);

        Notification notification;
        if (!notifications.isEmpty()) {
            notification = notifications.get(0);
            notification.setConfirmOrder(notification.getConfirmOrder() + 1);
        } else {
            notification = new Notification();
            notification.setUser(user);
            notification.setPlaceOrder(0);
            notification.setConfirmOrder(1);
        }
        
        notificationRepository.save(notification);
    }

}
