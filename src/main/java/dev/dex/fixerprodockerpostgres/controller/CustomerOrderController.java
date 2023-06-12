package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fixerpro/checkout")
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping
    public String goToCart() {
        return "checkout";
    }

    @PostMapping("/take-order")
    public String takeOrder(@RequestBody CustomerOrder customerOrder) {
        customerOrderService.processOrder(customerOrder);
        return "redirect:/fixerpro/checkout/successful-order";
    }

    @GetMapping("/successful-order")
    public String successfulOrder() {
        return "successful-order";
    }
}
