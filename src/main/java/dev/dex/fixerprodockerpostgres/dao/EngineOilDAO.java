package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.util.*;
import org.springframework.stereotype.*;

import java.util.*;

// This annotation makes the class a Spring Bean
@Repository
public class EngineOilDAO {
    private final int LIMIT = 20;
    // this is a bean provided by JPA
    private final EntityManager entityManager;

    // Dependency Injection on entityManager
    @Autowired
    public EngineOilDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EngineOil> findAll() {
        TypedQuery<EngineOil> query = entityManager.createQuery("FROM EngineOil", EngineOil.class);
        return query.getResultList();
    }

    public Pair<List<EngineOil>, Long> findByPage(int page) {
        // When using entity manager for creating queries
        // we use JPQL, this means instead of
        // table and row names we use Entity and field names
        TypedQuery<EngineOil> query = entityManager.createQuery(
                "FROM EngineOil",
                EngineOil.class)
                .setFirstResult(calculateOffset(page))
                .setMaxResults(LIMIT);
        TypedQuery<Long> count = entityManager.createQuery(
            "SELECT count(e) FROM EngineOil e",
                Long.class
        );
        return Pair.of(query.getResultList(), count.getSingleResult());
    }

    public Pair<List<EngineOil>, Long> findByFilterAndPage(String jpqlWhere, String[] filter, int page) {
        TypedQuery<EngineOil> query = entityManager.createQuery(
                "FROM EngineOil " + jpqlWhere,
                EngineOil.class)
                .setFirstResult(calculateOffset(page))
                .setMaxResults(LIMIT);
        TypedQuery<Long> count = entityManager.createQuery(
                "SELECT count(e) FROM EngineOil e " + jpqlWhere,
                Long.class
        );
        return Pair.of((List<EngineOil>) prepareQuery(query, filter).getResultList(),
                (Long) prepareQuery(count, filter).getSingleResult());
    }

    private TypedQuery<?> prepareQuery(TypedQuery<?> query, String[] filter) {
        for (int i = 0; i < filter.length; i++) {
            query.setParameter(i+1, filter[i]);
        }
        return query;
    }

    private int calculateOffset(int page) {
        return (page - 1) * LIMIT;
    }

    public EngineOil findByTitle(String title) {
        TypedQuery<EngineOil> query = entityManager.createQuery("FROM EngineOil WHERE thumbnail LIKE :title", EngineOil.class);
        query.setParameter("title", ("%" + title + ".jpg"));
        return query.getResultList().get(0);
    }

}
