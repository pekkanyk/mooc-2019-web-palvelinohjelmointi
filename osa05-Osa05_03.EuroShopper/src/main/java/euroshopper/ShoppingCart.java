/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pekka
 */
@Data @NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable{
    private Map<Item,Long> tuotteet = new HashMap<>();
    
    public Map<Item, Long> getItems(){
        return tuotteet;
    }
    
    public void addToCart(Item item){
        if (tuotteet.containsKey(item)){
            tuotteet.replace(item, tuotteet.get(item), tuotteet.get(item)+1);
        }
        else{
            tuotteet.put(item, Long.valueOf("1"));
        }
    }
    
    public void emptyCart(){
        tuotteet.clear();
    }
}
