package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.exception.*;
import dev.dex.fixerprodockerpostgres.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.util.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EngineOilService {
    private final EngineOilDAO engineOilDAO;

    // Dependency Injection on engineOilDAO
    @Autowired
    public EngineOilService(EngineOilDAO engineOilDAO) {
        this.engineOilDAO = engineOilDAO;
    }

    public List<EngineOil> findAll() {
        List<EngineOil> engineOils = this.engineOilDAO.findAll();
        if (engineOils.isEmpty()) {
            throw new EngineOilNotFoundException("Can't find all engine oils");
        }
        return engineOils;
    }

    public Pair<List<EngineOil>, Long> findByPage(int page) {
        if (page < 1) {
            throw new BadRequestException("In EngineOilService findByPage page is invalid. page = " + page);
        }
        Pair<List<EngineOil>, Long> engineOilsAndCount = this.engineOilDAO.findByPage(page);
        if (engineOilsAndCount.getFirst().isEmpty() || engineOilsAndCount.getSecond() == 0) {
            throw new EngineOilNotFoundException("Can't find engine oils by page");
        }
        return engineOilsAndCount;
    }

    public Pair<List<EngineOil>, Long> findByBrandsAndPage(String[] brands, int page) {
        if (page < 1) {
            throw new BadRequestException("In EngineOilService findByBrandsAndPage page is invalid. page = " + page);
        }
        String jpqlWhere = "WHERE";
        jpqlWhere += EngineOilUtil.makeJpqlBrands(brands);

        Pair<List<EngineOil>, Long> engineOilsAndCount = engineOilDAO.findByFilterAndPage(jpqlWhere, brands, page);
        if (engineOilsAndCount.getFirst().isEmpty() || engineOilsAndCount.getSecond() == 0) {
            throw new EngineOilNotFoundException("Can't find engine oils by brands and page");
        }
        return engineOilsAndCount;
    }

    public Pair<List<EngineOil>, Long> findByLitersAndPage(String[] liters, int page) {
        if (page < 1) {
            throw new BadRequestException("In EngineOilService findByLitersAndPage page is invalid. page = " + page);
        }

        // I have to use JPA Query Params because they protect against sql injection
        String jpqlWhere = "WHERE";
        jpqlWhere += EngineOilUtil.makeJpqlLiters(liters, 1);

        Pair<List<EngineOil>, Long> engineOilsAndCount = this.engineOilDAO.findByFilterAndPage(jpqlWhere, liters, page);
        if (engineOilsAndCount.getFirst().isEmpty() || engineOilsAndCount.getSecond() == 0) {
            throw new EngineOilNotFoundException("Can't find engine oils by liters and page");
        }
        return engineOilsAndCount;
    }

    public Pair<List<EngineOil>, Long> findByBrandsAndLitersAndPage(String[] brands, String[] liters, int page) {
        if (page < 1) {
            throw new BadRequestException("In EngineOilService findByBrandsAndLitersAndPage page is invalid. page = " + page);
        }

        String jpqlWhere = "WHERE (";
        jpqlWhere += EngineOilUtil.makeJpqlBrands(brands) + " ) AND (";
        jpqlWhere += EngineOilUtil.makeJpqlLiters(liters, brands.length+1) + " )";

        Pair<List<EngineOil>, Long> engineOilsAndCount = this.engineOilDAO.findByFilterAndPage(
                jpqlWhere, EngineOilUtil.arrayConcat(brands, liters), page);
        if (engineOilsAndCount.getFirst().isEmpty() || engineOilsAndCount.getSecond() == 0) {
            throw new EngineOilNotFoundException("Can't find engine oils by brands, liters and page");
        }
        return engineOilsAndCount;
    }

    public EngineOil findByTitle(String title) {
        try {
            return engineOilDAO.findByTitle(title);
        }
        catch (IndexOutOfBoundsException ex) {
            throw new EngineOilNotFoundException("Can't find engine oil by title");
        }
    }

}
