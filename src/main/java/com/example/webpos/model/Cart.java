package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();
    private double total_cost = 0;

    public Item findItem(String productId){
        for(Item it: items){
            if(it.getProduct().getId().equals(productId)){
                return it;
            }
        }
        return null;
    }

    public boolean addItem(Item item) {
        Item temp = this.findItem(item.getProduct().getId());
        if(temp != null){
            temp.setQuantity(temp.getQuantity() + item.getQuantity());
            total_cost += item.getQuantity() * item.getProduct().getPrice();
            return true;
        }
        else{
            total_cost += item.getQuantity() * item.getProduct().getPrice();
            return items.add(item);
        }
    }

    //delete the item by productID
    public boolean deleteItem(String productId){
        Item temp = this.findItem(productId);
        if(temp != null){
            items.remove(temp);
            total_cost -= temp.getQuantity() * temp.getProduct().getPrice();
            return true;
        }
        else{
            return false;
        }
    }

    //modify the amount of the cart by productID
    public boolean modifyItem(String productId, int amount) {
        Item item = this.findItem(productId);
        if(item != null){
            total_cost = amount * item.getProduct().getPrice();
            item.setQuantity(item.getQuantity()+amount);
            if (item.getQuantity() <= 0){
                items.remove(item);
            }
            return true;
        }
        else{
            if(item.getQuantity() > 0) {
                total_cost = item.getQuantity() * item.getProduct().getPrice();
                return items.add(item);
            }
            else
                return false;
        }
    }

    //clear the cart
    public boolean emptyCart(){
        if(items.size() > 0){
            total_cost = 0;
            items.clear();
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
