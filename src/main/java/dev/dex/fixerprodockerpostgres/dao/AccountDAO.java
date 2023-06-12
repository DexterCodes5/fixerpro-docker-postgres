package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class AccountDAO {
    private final EntityManager entityManager;

    @Autowired
    public AccountDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("FROM Account", Account.class);
        return  query.getResultList();
    }

    public Account save(Account account) {
        return entityManager.merge(account);
    }

    public Account findByHashCode(int hashCode) {
        TypedQuery<Account> query = entityManager.createQuery("FROM Account WHERE hashCode=:theHashCode", Account.class);
        query.setParameter("theHashCode", hashCode);
        return query.getSingleResult();
    }

    public Account findByUsername(String username) {
        TypedQuery<Account> query = entityManager.createQuery("FROM Account WHERE username=:theUsername", Account.class);
        query.setParameter("theUsername", username);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getResultList().get(0);
    }

    public Account findByEmail(String email) {
        TypedQuery<Account> query = entityManager.createQuery("FROM Account WHERE email=:theEmail", Account.class);
        query.setParameter("theEmail", email);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getResultList().get(0);
    }

}
