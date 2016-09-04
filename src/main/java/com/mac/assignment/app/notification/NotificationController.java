/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.notification;

import com.mac.assignment.app.notification.model.Notification;
import com.mac.assignment.app.notification.request.NotificationRespond;
import com.mac.assignment.config.security.UserImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohan
 */
@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/notifications", method = RequestMethod.POST)
    public NotificationRespond getNotifications() {
        UserImpl user = (UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Notification> notifications = notificationService.getNotification(user.getIndexNo());
        int placeOrder = 0;
        int confirmOrder = 0;

        for (Notification notification : notifications) {
            placeOrder += notification.getPlaceOrder();
            confirmOrder += notification.getConfirmOrder();
        }

        //notification respond
        NotificationRespond notificationRespond;
        if (placeOrder > 0 || confirmOrder > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You have ");

            if (placeOrder > 0) {
                stringBuilder.append(placeOrder).append(" new orders");
            }

            if (placeOrder > 0 && confirmOrder > 0) {
                stringBuilder.append(placeOrder).append(" and ");
            }

            if (confirmOrder > 0) {
                stringBuilder.append(confirmOrder).append(" confirmed orders.");
            }

            notificationRespond = new NotificationRespond(true, stringBuilder.toString());
        } else {
            notificationRespond = new NotificationRespond(false, null);
        }

        return notificationRespond;
    }

    @RequestMapping(value = "/consume", method = RequestMethod.POST)
    public NotificationRespond consumeNotification() {
        UserImpl user = (UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //consume notifications
        notificationService.consumeNotifications(user.getIndexNo());
        
        return new NotificationRespond(false, "Notifation consumed..!");
    }

}
