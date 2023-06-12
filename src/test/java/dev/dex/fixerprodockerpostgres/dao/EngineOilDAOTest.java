package dev.dex.fixerprodockerpostgres.dao;

import dev.dex.fixerprodockerpostgres.entity.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.data.util.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class EngineOilDAOTest {
    @Autowired
    private EntityManager entityManager;
    private EngineOilDAO underTest;

    @BeforeEach
    void setUp() {
        underTest = new EngineOilDAO(entityManager);
    }

    @AfterEach
    void tearDown() {
        entityManager.createQuery("DELETE FROM EngineOil").executeUpdate();
    }

    @Test
    void canFindByPage() {
        // given
        EngineOil castrol = entityManager.merge(new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"));
        EngineOil motul = entityManager.merge(new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56"));

        // when
        Pair<List<EngineOil>, Long> engineOilsAndCount = underTest.findByPage(1);

        // then
        assertThat(engineOilsAndCount.getFirst().get(0)).isEqualTo(castrol);
        assertThat(engineOilsAndCount.getFirst().get(1)).isEqualTo(motul);
        assertThat(engineOilsAndCount.getSecond()).isEqualTo(2);
    }

    @Test
    void canFindByFilterAndPage() {
        // given
        EngineOil castrol = entityManager.merge(new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"));
        EngineOil motul = entityManager.merge(new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56"));

        // when
        String testJpqlWhere = "WHERE title LIKE ?1";
        String[] filter = new String[] {
                "CASTROL%"
        };
        Pair<List<EngineOil>, Long> engineOilsAndCount = underTest.findByFilterAndPage(testJpqlWhere, filter, 1);

        // then
        assertThat(engineOilsAndCount.getFirst().get(0)).isEqualTo(castrol);
        assertThat(engineOilsAndCount.getSecond()).isEqualTo(1);
    }

    @Test
    void canFindByTitle() {
        // given
        EngineOil castrol = entityManager.merge(new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"));
        EngineOil motul = entityManager.merge(new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56"));

        // when
        String testTitle = "castrol-edge-turbodiesel-5w40-5l";
        EngineOil testEngineOil = underTest.findByTitle(testTitle);

        // then
        assertThat(testEngineOil).isEqualTo(castrol);
    }
}