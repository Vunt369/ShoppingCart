/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NguyenTuanVu
 */
public class CartObj implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id, int quantity) {
        boolean result = false;
        //1. check id, quantity valid
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }

        //2. check existed item
        if (this.items == null) {
            this.items = new HashMap<>();
        }//end items had NOT existed
        //3. drop itemm into items
        if (this.items.containsKey(id)) {
            int quan = items.get(id); // available
            quantity = quantity + quan;
        }//end item had existed
        //4. update items
        items.put(id, quantity);
        result = true;

        return result;
    }

    // items la ngan chua do
    public boolean removeItemFromCart(String id, int quantity) {
        boolean result = false;
        //1. check id, quantity valid
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed items (check ngan chua do)
        if (this.items == null) {
            return result;
        }
        //3. check item existed
        if (this.items.containsKey(id)) {
            //4. compare quan
            int quan = this.items.get(id); //available

            if (quan < quantity) {
                return result;
            }//available quantity smalle than removed quantity

            quan = quan - quantity;

            if (quan == 0) {
                this.items.remove(id);
                if (this.items.isEmpty()) {
                    this.items = null;
                }// empty cart when items has not existed
            }
            else {
                this.items.put(id, quan);
            }
            result = true;
        }//end item had existed
        return result;
    }
}
