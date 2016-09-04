/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.assignment.app.item.respond;

import com.mac.assignment.app.item.model.Item;
import java.util.List;

/**
 *
 * @author Mohan
 */
public class ItemRespond {

    private List<Item> items;

    public ItemRespond(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
