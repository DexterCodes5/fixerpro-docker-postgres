package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class FilterService {
    private final FilterDAO filterDAO;

    // Dependency Injection on filterDAO via Constructor Injection
    @Autowired
    public FilterService(FilterDAO filterDAO) {
        this.filterDAO = filterDAO;
    }

    public List<Filter> findAll() {
        return filterDAO.findAll();
    }

    public List<Filter> findFilters(String carBrand, String carModel, String type) {
        String jpql = "FROM Filter WHERE type = ?1 AND compatibleCars LIKE ?2";
        String compatibleCar = "%" + carBrand + "-" + carModel + "%";
        List<Filter> filters = this.filterDAO.findFilters(jpql, type, compatibleCar);

        return filters;
    }

    public Filter findFilterByNumber(String number) {
        Filter filter = null;
        try {
            filter = this.filterDAO.findFilterByNumber(number.replaceAll("--", "/"));
        }
        catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFoundException("Filter number doesn't exist. number: " + number);
        }

        return filter;
    }
}
