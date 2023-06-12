package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilterServiceTest {
    @Mock
    private FilterDAO filterDAO;
    private FilterService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FilterService(filterDAO);
    }

    @Test
    void canFindAll() {
        // when
        underTest.findAll();

        // then
        verify(filterDAO).findAll();
    }

    @Test
    void canFindFilters() {
        // given
        String testCarBrand = "mercedes-benz";
        String testCarModel = "c-class-w203";
        String testType = "air-filter";

        // when
        underTest.findFilters(testCarBrand, testCarModel, testType);

        // then
        ArgumentCaptor<String> jpqlArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> typeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> compatibleCarArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(filterDAO).findFilters(jpqlArgumentCaptor.capture(), typeArgumentCaptor.capture(),
                compatibleCarArgumentCaptor.capture());

        String testJpql = "FROM Filter WHERE type = ?1 AND compatibleCars LIKE ?2";
        String testCompatibleCar = "%" + testCarBrand + "-" + testCarModel + "%";
        assertThat(jpqlArgumentCaptor.getValue()).isEqualTo(testJpql);
        assertThat(typeArgumentCaptor.getValue()).isEqualTo(testType);
        assertThat(compatibleCarArgumentCaptor.getValue()).isEqualTo(testCompatibleCar);
    }

    @Test
    void canFindFilterByNumber() {
        // when
        String testNumber = "1457437003";
        underTest.findFilterByNumber(testNumber);

        // then
        ArgumentCaptor<String> numberArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(filterDAO).findFilterByNumber(numberArgumentCaptor.capture());

        assertThat(numberArgumentCaptor.getValue()).isEqualTo(testNumber);
    }
}