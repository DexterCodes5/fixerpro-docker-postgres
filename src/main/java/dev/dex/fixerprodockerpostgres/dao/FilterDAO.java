package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class FilterDAO {
    private final EntityManager entityManager;

    // Dependency Injection on entityManager via Constructor Injection
    @Autowired
    public FilterDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Filter> findAll() {
        TypedQuery<Filter> query = entityManager.createQuery("FROM Filter", Filter.class);
        return query.getResultList();
    }

    public List<Filter> findFilters(String jpql, String type, String compatibleCar) {
        TypedQuery<Filter> query = entityManager.createQuery(jpql, Filter.class);
        query.setParameter(1, type);
        query.setParameter(2, compatibleCar);
        return query.getResultList();
    }

    public Filter findFilterByNumber(String number) {
        TypedQuery<Filter> query = entityManager.createQuery("FROM Filter WHERE REPLACE(number, ' ', '') = :theNumber", Filter.class);
        query.setParameter("theNumber", number);
        return query.getSingleResult();
    }
}
