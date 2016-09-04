/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.item;

import com.mac.assignment.app.item.respond.ItemRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohan
 */
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ItemRespond findAll() {
        ItemRespond itemRespond;

        itemRespond = new ItemRespond(itemRepository.findAll());

        return itemRespond;
    }
}
