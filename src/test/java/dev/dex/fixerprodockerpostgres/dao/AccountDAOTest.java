package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.oauth2.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class AccountDAOTest {
    @Autowired
    private EntityManager entityManager;
    private AccountDAO underTest;

    @BeforeEach
    void setUp() {
        underTest = new AccountDAO(entityManager);
    }

    @AfterEach
    void tearDown() {
        entityManager.createQuery("DELETE FROM Account").executeUpdate();
    }

    @Test
    void canFindAll() {
        // given
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                "dex@gmail.com", 1, Provider.LOCAL));
        Account dockster = entityManager.merge(new Account("dockster", "test123", true,
                "doc@gmail.com", 1, Provider.LOCAL));

        // when
        List<Account> testAccounts = underTest.findAll();

        // then
        assertThat(testAccounts.get(0)).isEqualTo(dexter);
        assertThat(testAccounts.get(1)).isEqualTo(dockster);
    }

    @Test
    void canSave() {
        // given
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                "dex@gmail.com", 1, Provider.LOCAL));

        // when
        Account dexterDB = underTest.save(dexter);

        // then
        assertThat(dexterDB).isEqualTo(dexter);
    }

    @Test
    void canFindByHashCode() {
        // given
        int hashCode = 123;
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                "dex@gmail.com", hashCode, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        Account dexterDB = underTest.findByHashCode(hashCode);

        // then
        assertThat(dexterDB).isEqualTo(dexter);
    }

    @Test
    void willThrowWhenHashcodeNotExist() {
        // given
        int hashCode = 123;
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                "dex@gmail.com", hashCode, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        // then
        assertThatThrownBy(() -> underTest.findByHashCode(12345))
                .isInstanceOf(NoResultException.class)
                .hasMessageContaining("No result found for query [FROM Account WHERE hashCode=:theHashCode]");
    }

    @Test
    void canFindByUsername() {
        // given
        String username = "dexter";
        Account dexter = entityManager.merge(new Account(username, "test123", true,
                "dex@gmail.com", 123, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        Account dexterDB = underTest.findByUsername(username);

        // then
        assertThat(dexterDB).isEqualTo(dexter);
    }

    @Test
    void willReturnNullWhenUsernameNotExist() {
        // given
        String username = "dexter";
        Account dexter = entityManager.merge(new Account(username, "test123", true,
                "dex@gmail.com", 123, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        Account dexterDB = underTest.findByUsername("mexter");

        // then
        assertThat(dexterDB).isNull();
    }

    @Test
    void canFindByEmail() {
        // given
        String email = "dex@gmail.com";
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                email, 123, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        Account dexterDB = underTest.findByEmail(email);

        // then
        assertThat(dexterDB).isEqualTo(dexter);
    }

    @Test
    void willReturnNullWhenEmailNotExist() {
        // given
        String email = "dex@gmail.com";
        Account dexter = entityManager.merge(new Account("dexter", "test123", true,
                email, 123, Provider.LOCAL));
        entityManager.merge(dexter);

        // when
        Account dexterDB = underTest.findByEmail("bad_email");

        // then
        assertThat(dexterDB).isNull();
    }
}