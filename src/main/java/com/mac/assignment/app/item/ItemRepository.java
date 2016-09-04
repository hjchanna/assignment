/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.item;

import com.mac.assignment.app.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mohan
 */
public interface ItemRepository extends JpaRepository<Item, String> {

}
