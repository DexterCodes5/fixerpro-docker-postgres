package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomerOrderService {
    private final CustomerOrderDAO customerOrderDAO;
    private final MailService mailService;

    // Dependency Injection on emailSender and orderDAO via Constructor Injection
    @Autowired
    public CustomerOrderService(CustomerOrderDAO customerOrderDAO, MailService mailService) {
        this.customerOrderDAO = customerOrderDAO;
        this.mailService = mailService;
    }

    public void processOrder(CustomerOrder customerOrder) {


        // Save it in the database
        customerOrderDAO.save(customerOrder);

        mailService.sendSimpleMessage(
                "dtanchev081@gmail.com",
                "Order N.: " + customerOrder.getId(),
                customerOrder.toString()
                );
    }

    public List<CustomerOrder> findAll() {
        return customerOrderDAO.findAll();
    }
}
