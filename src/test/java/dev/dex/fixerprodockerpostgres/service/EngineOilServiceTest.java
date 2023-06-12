package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.exception.*;
import dev.dex.fixerprodockerpostgres.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.data.util.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class EngineOilServiceTest {
    @Mock
    private EngineOilDAO engineOilDAO;
    private EngineOilService underTest;

    @BeforeEach
    void setUp() {
        underTest = new EngineOilService(engineOilDAO);
    }

    @Test
    void canFindAll() {
        // given
        List<EngineOil> engineOils = List.of(
                        new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                        new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56")
                );
        given(engineOilDAO.findAll()).willReturn(engineOils);

        // when
        underTest.findAll();

        // then
        verify(engineOilDAO).findAll();
    }

    @Test
    void willThrowWhenFindAllListIsEmpty() {
        // given
        List<EngineOil> engineOils = new ArrayList<>();
        given(engineOilDAO.findAll()).willReturn(engineOils);

        // when
        // then
        assertThatThrownBy(() -> underTest.findAll())
                .isInstanceOf(EngineOilNotFoundException.class)
                .hasMessageContaining("Can't find all engine oils");
    }
    @Test
    void canFindByPage() {
        // given
        int testPage = 1;
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(
                List.of(
                        new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                        new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56")
                ),
                2l
        );
        given(engineOilDAO.findByPage(testPage)).willReturn(testEngineOilsAndCount);

        // when
        underTest.findByPage(testPage);

        // then
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(engineOilDAO).findByPage(integerArgumentCaptor.capture());

        int capturedPage = integerArgumentCaptor.getValue();
        assertThat(capturedPage).isEqualTo(testPage);
    }

    @Test
    void willThrowWhenPageInvalidInFindByPage() {
        // given
        int testInvalidPage = 0;

        // when
        // then
        assertThatThrownBy(() -> underTest.findByPage(testInvalidPage))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("In EngineOilService findByPage page is invalid. page = " + testInvalidPage);
        verify(engineOilDAO, never()).findByPage(anyInt());
    }

    @Test
    void willThrowWhenEngineOilsIsEmptyInFindByPage() {
        // given
        int testPage = 1;
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(new ArrayList<>(), 0L);
        given(engineOilDAO.findByPage(testPage)).willReturn(testEngineOilsAndCount);

        // when
        // then
        assertThatThrownBy(() -> underTest.findByPage(testPage))
                .isInstanceOf(EngineOilNotFoundException.class)
                .hasMessageContaining("Can't find engine oils by page");
    }

    @Test
    void canFindByBrandsAndPage() {
        // given
        String testJpqlWhere = "WHERE title LIKE ?1 OR title LIKE ?2";
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        int testPage = 1;

        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(
                List.of(
                        new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                                "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                        new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                                "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56")
                ),
                2l
        );
        given(engineOilDAO.findByFilterAndPage(testJpqlWhere, testBrands, testPage)).willReturn(testEngineOilsAndCount);

        // when
        underTest.findByBrandsAndPage(testBrands, testPage);

        // then
        ArgumentCaptor<String> jpqlWhereArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String[]> brandsArgumentCaptor = ArgumentCaptor.forClass(String[].class);
        ArgumentCaptor<Integer> pageArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(engineOilDAO).findByFilterAndPage(
                jpqlWhereArgumentCaptor.capture(),
                brandsArgumentCaptor.capture(),
                pageArgumentCaptor.capture());

        String capturedJpqlWhere = jpqlWhereArgumentCaptor.getValue();
        String[] capturedBrands = brandsArgumentCaptor.getValue();
        int capturedPage = pageArgumentCaptor.getValue();

        assertThat(capturedJpqlWhere).isEqualTo(testJpqlWhere);
        assertThat(capturedBrands).containsOnly(testBrands);
        assertThat(capturedPage).isEqualTo(testPage);
    }

    @Test
    void willThrowWhenPageIsInvalidInFindByBrandsAndPage() {
        // given
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        int testPage = 0;

        // when
        // then
        assertThatThrownBy(() -> underTest.findByBrandsAndPage(testBrands, testPage))
                .isInstanceOf(BadRequestException.class)
                        .hasMessageContaining(
                                "In EngineOilService findByBrandsAndPage page is invalid. page = " + testPage);
        verify(engineOilDAO, never()).findByFilterAndPage(anyString(), any(String[].class), anyInt());
    }

    @Test
    void willThrowWhenEngineOilsIsEmptyInFindByBrandsAndPage() {
        // given
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        int testPage = 1;
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(new ArrayList<>(), 0L);
        given(engineOilDAO.findByFilterAndPage(anyString(), any(String[].class),eq(testPage)))
                .willReturn(testEngineOilsAndCount);

        // when
        // then
        assertThatThrownBy(() -> underTest.findByBrandsAndPage(testBrands, testPage))
                .isInstanceOf(EngineOilNotFoundException.class)
                .hasMessageContaining("Can't find engine oils by brands and page");
    }

    @Test
    void canFindByLitersAndPage() {
        // given
        String testJpqlWhere = "WHERE liters = ?1";
        int testPage = 1;
        String[] testLiters = new String[] {
                "1"
        };

        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(
                List.of(
                        new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                                "/thumbnails/1l/castrol-edge-turbodiesel-5w40-1l.jpg", "1", "84.90"),
                        new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 1L",
                                "/thumbnails/1l/motul-6100-synergie-10w-40-1l.jpg", "1", "78.56")
                ),
                2l
        );

        given(engineOilDAO.findByFilterAndPage(testJpqlWhere, testLiters, testPage)).willReturn(testEngineOilsAndCount);

        // when
        underTest.findByLitersAndPage(testLiters, testPage);

        // then
        ArgumentCaptor<String> jpqlWhereArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String[]> litersArgumentCaptor = ArgumentCaptor.forClass(String[].class);
        ArgumentCaptor<Integer> pageArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(engineOilDAO).findByFilterAndPage(
                jpqlWhereArgumentCaptor.capture(),
                litersArgumentCaptor.capture(),
                pageArgumentCaptor.capture());

        String capturedJpqlWhere = jpqlWhereArgumentCaptor.getValue();
        String[] capturedLiters = litersArgumentCaptor.getValue();
        int capturedPage = pageArgumentCaptor.getValue();

        assertThat(capturedJpqlWhere).isEqualTo(testJpqlWhere);
        assertThat(capturedLiters).containsOnly(testLiters);
        assertThat(capturedPage).isEqualTo(testPage);
    }

    @Test
    void willThrowWhenPageIsInvalidInFindByLitersAndPage() {
        // given
        String[] testLiters = new String[] {
                "1"
        };
        int testPage = 0;

        // when
        // then
        assertThatThrownBy(() -> underTest.findByLitersAndPage(testLiters, testPage))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "In EngineOilService findByLitersAndPage page is invalid. page = " + testPage);
        verify(engineOilDAO, never()).findByFilterAndPage(anyString(), any(String[].class), anyInt());
    }

    @Test
    void willThrowWhenEngineOilsIsEmptyInFindByLitersAndPage() {
        // given
        String[] testLiters = new String[] {
                "1"
        };
        int testPage = 1;
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(new ArrayList<>(), 0L);
        given(engineOilDAO.findByFilterAndPage(anyString(), any(String[].class),eq(testPage)))
                .willReturn(testEngineOilsAndCount);

        // when
        // then
        assertThatThrownBy(() -> underTest.findByLitersAndPage(testLiters, testPage))
                .isInstanceOf(EngineOilNotFoundException.class)
                .hasMessageContaining("Can't find engine oils by liters and page");
    }

    @Test
    void canFindByBrandsAndLitersAndPage() {
        // given
        String testJpqlWhere = "WHERE ( title LIKE ?1 OR title LIKE ?2 ) AND ( liters = ?3 )";
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        String[] testLiters = new String[] {
                "1"
        };
        int testPage = 1;

        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(
                List.of(
                        new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                                "/thumbnails/1l/castrol-edge-turbodiesel-5w40-1l.jpg", "1", "84.90"),
                        new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 1L",
                                "/thumbnails/1l/motul-6100-synergie-10w-40-1l.jpg", "1", "78.56")
                ),
                2l
        );

        given(engineOilDAO.findByFilterAndPage(eq(testJpqlWhere), any(String[].class),
                eq(testPage))).willReturn(testEngineOilsAndCount);

        // when
        underTest.findByBrandsAndLitersAndPage(testBrands, testLiters, testPage);

        // then
        ArgumentCaptor<String> jpqlWhereArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String[]> brandsAndLitersArgumentCaptor = ArgumentCaptor.forClass(String[].class);
        ArgumentCaptor<Integer> pageArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(engineOilDAO).findByFilterAndPage(
                jpqlWhereArgumentCaptor.capture(),
                brandsAndLitersArgumentCaptor.capture(),
                pageArgumentCaptor.capture());

        String capturedJpqlWhere = jpqlWhereArgumentCaptor.getValue();
        String[] capturedBrandsAndLiters = brandsAndLitersArgumentCaptor.getValue();
        int capturedPage = pageArgumentCaptor.getValue();

        assertThat(capturedJpqlWhere).isEqualTo(testJpqlWhere);

        String[] expectedBrandsAndLiters = EngineOilUtil.arrayConcat(testBrands, testLiters);
        assertThat(capturedBrandsAndLiters).containsOnly(expectedBrandsAndLiters);
        assertThat(capturedPage).isEqualTo(testPage);
    }

    @Test
    void willThrowWhenPageIsInvalidInFindByBrandsAndLitersAndPage() {
        // given
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        String[] testLiters = new String[] {
                "1"
        };
        int testPage = 0;

        // when
        // then
        assertThatThrownBy(() -> underTest.findByBrandsAndLitersAndPage(testBrands, testLiters, testPage))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "In EngineOilService findByBrandsAndLitersAndPage page is invalid. page = " + testPage);
        verify(engineOilDAO, never()).findByFilterAndPage(anyString(), any(String[].class), anyInt());
    }

    @Test
    void willThrowWhenEngineOilsIsEmptyInFindByBrandsAndLitersAndPage() {
        // given
        String[] testBrands = new String[] {
                "CASTROL",
                "MOTUL"
        };
        String[] testLiters = new String[] {
                "1"
        };
        int testPage = 1;
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(new ArrayList<>(), 0L);
        given(engineOilDAO.findByFilterAndPage(anyString(), any(String[].class),eq(testPage)))
                .willReturn(testEngineOilsAndCount);

        // when
        // then
        assertThatThrownBy(() -> underTest.findByBrandsAndLitersAndPage(testBrands, testLiters, testPage))
                .isInstanceOf(EngineOilNotFoundException.class)
                .hasMessageContaining("Can't find engine oils by brands, liters and page");
    }

    @Test
    void canFindByTitle() {
        // given
        String testTitle = "castrol-edge-turbodiesel-5w40-5l";

        // when
        underTest.findByTitle(testTitle);

        // then
        ArgumentCaptor<String> titleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(engineOilDAO).findByTitle(titleArgumentCaptor.capture());

        String capturedTitle = titleArgumentCaptor.getValue();
        assertThat(capturedTitle).isEqualTo(testTitle);
    }
}