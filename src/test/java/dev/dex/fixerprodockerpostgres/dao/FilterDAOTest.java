package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class FilterDAOTest {
    @Autowired
    private EntityManager entityManager;
    private FilterDAO underTest;

    @BeforeEach
    void setUp() {
        underTest = new FilterDAO(entityManager);
    }

    @Test
    void canFindAll() {
        // given
        Filter filter = entityManager.merge(new Filter("oil-filter", "BOSCH", "1 457 437 003",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21"));
        Filter filter2 = entityManager.merge(new Filter("oil-filter", "MANN-FILTER", "HU 925/4 X",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-mann1.jpg", "23.92"));

        // when
        List<Filter> testFilters = underTest.findAll();

        // then
        assertThat(testFilters.get(0)).isEqualTo(filter);
        assertThat(testFilters.get(1)).isEqualTo(filter2);
    }

    @Test
    void canFindFilters() {
        // given
        Filter filter = entityManager.merge(new Filter("oil-filter", "BOSCH", "1 457 437 003",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21"));
        Filter filter2 = entityManager.merge(new Filter("air-filter", "MANN-FILTER", "HU 925/4 X",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-mann1.jpg", "23.92"));
        String jpql = "FROM Filter WHERE type = ?1 AND compatibleCars LIKE ?2";
        String filterType = "oil-filter";
        String compatibleCar = "%bmw-3-e46%";

        // when
        List<Filter> filtersDb = underTest.findFilters(jpql, filterType, compatibleCar);

        // then
        assertThat(filtersDb.size()).isEqualTo(1);
        assertThat(filtersDb.get(0)).isEqualTo(filter);
    }

    @Test
    void canFindFilterByNumber() {
        // given
        Filter filter = entityManager.merge(new Filter("oil-filter", "BOSCH", "1 457 437 003",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21"));
        Filter filter2 = entityManager.merge(new Filter("air-filter", "MANN-FILTER", "HU 925/4 X",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-mann1.jpg", "23.92"));
        String testNumber = "1457437003";

        // when
        Filter filterDb = underTest.findFilterByNumber(testNumber);

        // then
        assertThat(filterDb).isEqualTo(filter);
    }
}