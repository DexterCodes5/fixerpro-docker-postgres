package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class CustomerOrderDAOTest {
    @Autowired
    private EntityManager entityManager;
    private CustomerOrderDAO underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerOrderDAO(entityManager);
    }

    @AfterEach
    void tearDown() {
        entityManager.createQuery("DELETE FROM CustomerOrder").executeUpdate();
    }

    @Test
    void canSave() {
        // given
        CustomerOrder customerOrder = entityManager.merge(new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                "his_address", "his_comment", 100, "products"));

        // when
        CustomerOrder customerOrderDB = underTest.save(customerOrder);

        // then
        assertThat(customerOrderDB).isEqualTo(customerOrder);
    }

    @Test
    void canFindAll() {
        // given
        CustomerOrder customerOrder = entityManager.merge(new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                "his_address", "his_comment", 100, "products"));
        CustomerOrder customerOrder2 = entityManager.merge(new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                "his_address", "his_comment", 100, "products"));

        // when
        List<CustomerOrder> testCustomerOrders = underTest.findAll();

        // then
        assertThat(testCustomerOrders.get(0)).isEqualTo(customerOrder);
        assertThat(testCustomerOrders.get(1)).isEqualTo(customerOrder2);
    }
}