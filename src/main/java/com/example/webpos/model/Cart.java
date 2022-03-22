package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public Item findItem(Item item){
        for(Item it: items){
            if(it.getProduct().getId().equals(item.getProduct().getId())){
                return it;
            }
        }
        return null;
    }

    public boolean addItem(Item item) {
        Item temp = this.findItem(item);
        if(temp != null){
            temp.setQuantity(temp.getQuantity() + item.getQuantity());
            return true;
        }
        else{
            return items.add(item);
        }

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
