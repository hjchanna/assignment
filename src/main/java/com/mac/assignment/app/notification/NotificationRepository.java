/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.notification;

import com.mac.assignment.app.notification.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohan
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    public List<Notification> findByUser(Integer user);
    
    public void deleteByUser(Integer user);
}
