package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

// We use repositories to manipulate data from database
@Repository
public class CustomerOrderDAO {
    private final EntityManager entityManager;

    // Dependency Injection of entityManager via Constructor Injection
    @Autowired
    public CustomerOrderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public CustomerOrder save(CustomerOrder customerOrder) {
        // persist = save in this context
        return entityManager.merge(customerOrder);
    }

    public List<CustomerOrder> findAll() {
        TypedQuery<CustomerOrder> query = entityManager.createQuery("FROM CustomerOrder", CustomerOrder.class);
        return query.getResultList();
    }
}
