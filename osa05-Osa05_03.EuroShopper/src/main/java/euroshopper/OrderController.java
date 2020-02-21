package euroshopper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ShoppingCart shoppingCart;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        List <OrderItem> tilatut = new ArrayList<>();
        //shoppingCart.getItems().forEach(Item i ,Long lkm)->System.out.println(i + lkm);;
        shoppingCart.getItems().entrySet().forEach((rivi) -> {
            tilatut.add(new OrderItem(rivi.getKey(),rivi.getValue()));
        });
        order.setOrderItems(tilatut);

        orderRepository.save(order);
        shoppingCart.emptyCart();

        return "redirect:/orders";
    }
}
