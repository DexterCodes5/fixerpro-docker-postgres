package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("fixerpro/admin")
public class AdminController {
    private final EngineOilService engineOilService;
    private final FilterService filterService;
    private final CustomerOrderService customerOrderService;
    private final AccountService accountService;

    @Autowired
    public AdminController(EngineOilService engineOilService, FilterService filterService,
                           CustomerOrderService customerOrderService, AccountService accountService) {
        this.engineOilService = engineOilService;
        this.filterService = filterService;
        this.customerOrderService = customerOrderService;
        this.accountService = accountService;
    }

    @GetMapping("/engine-oil")
    public String getEngineOil(Model model) {
        List<EngineOil> engineOils = engineOilService.findAll();
        model.addAttribute("table", "Engine Oil");
        model.addAttribute("engineOils", engineOils);
        return "admin/admin-engine-oil";
    }

    @GetMapping("/accounts")
    public String getAccounts(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("table", "Accounts");
        model.addAttribute("accounts", accounts);
        return "admin/admin-accounts";
    }

    @GetMapping("/customer-orders")
    public String getCustomerOrders(Model model) {
        List<CustomerOrder> customerOrders = customerOrderService.findAll();
        model.addAttribute("table", "Customer Orders");
        model.addAttribute("customerOrders", customerOrders);
        return "admin/admin-customer-orders";
    }

    @GetMapping("/filters")
    public String getFilters(Model model) {
        List<Filter> filters = filterService.findAll();
        model.addAttribute("table", "Filters");
        model.addAttribute("filters", filters);
        return "admin/admin-filters";
    }
}
