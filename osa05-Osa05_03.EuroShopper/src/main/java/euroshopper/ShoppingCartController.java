/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Pekka
 */
@Controller
public class ShoppingCartController {
    
    @Autowired
    ShoppingCart shoppingCart;
    
    @Autowired
    ItemRepository itemRepository;
    
    @GetMapping ("/cart")
    public String ostoskori(Model model){
        model.addAttribute("items",shoppingCart.getItems());
        return "cart";
    }
    
    @PostMapping ("/cart/items/{id}")
    public String lisaaKoriin(@PathVariable Long id){
        shoppingCart.addToCart(itemRepository.getOne(id));
        return "redirect:/cart";
    }
    
    
}
