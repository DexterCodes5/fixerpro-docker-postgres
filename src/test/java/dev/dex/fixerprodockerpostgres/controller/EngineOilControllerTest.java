package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import dev.dex.fixerprodockerpostgres.util.*;
import dev.dex.fixerprodockerpostgres.security.SecurityConfigTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.data.util.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@WebMvcTest(EngineOilController.class)
@Import(SecurityConfigTest.class)
class EngineOilControllerTest {
    @MockBean
    private EngineOilService engineOilService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void canGetEngineOils() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                        "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                        "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 2l);

        given(engineOilService.findByPage(1)).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsWithRequestParams() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                        "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                        "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 2l);

        given(engineOilService.findByPage(2)).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/?page=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByBrand() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                        "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 1l);

        given(engineOilService.findByBrandsAndPage(any(String[].class), eq(1))).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/brand-castrol"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByBrandWithParams() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                        "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 1l);

        given(engineOilService.findByBrandsAndPage(any(String[].class), eq(2))).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/brand-castrol/?page=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByLiters() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                        "/thumbnails/1l/castrol-edge-turbodiesel-5w40-5l.jpg", "1", "84.90"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 1l);

        given(engineOilService.findByLitersAndPage(any(String[].class), eq(1))).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/liters-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByLitersWithParams() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                        "/thumbnails/1l/castrol-edge-turbodiesel-5w40-5l.jpg", "1", "84.90"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 1l);

        given(engineOilService.findByLitersAndPage(any(String[].class), eq(2))).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/liters-1/?page=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByBrandAndLiters() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                        "/thumbnails/1l/castrol-edge-turbodiesel-5w40-5l.jpg", "1", "84.90"),
                new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 1L",
                        "/thumbnails/1l/motul-6100-synergie-10w-40-5l.jpg", "1", "78.56"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 2l);

        given(engineOilService.findByBrandsAndLitersAndPage(any(String[].class), any(String[].class), eq(1))).willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/brand-castrol-motul/liters-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetEngineOilsByBrandAndLitersWithParams() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                        "/thumbnails/1l/castrol-edge-turbodiesel-5w40-5l.jpg", "1", "84.90"),
                new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 1L",
                        "/thumbnails/1l/motul-6100-synergie-10w-40-5l.jpg", "1", "78.56"));
        Pair<List<EngineOil>, Long> testEngineOilsAndCount = Pair.of(testEngineOils, 2l);

        given(engineOilService.findByBrandsAndLitersAndPage(any(String[].class), any(String[].class), eq(2)))
                .willReturn(testEngineOilsAndCount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/fixerpro/engine-oil/brand-castrol-motul/liters-1/?page=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOilsAndCount.getFirst()))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOilsLength", testEngineOilsAndCount.getSecond()));
    }

    @Test
    void canGetProduct() throws Exception {
        String testTitle = "castrol-edge-turbodiesel-5w40-5l";
        EngineOil testEngineOil = new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 1L",
                "/thumbnails/1l/castrol-edge-turbodiesel-5w40-5l.jpg", "1", "84.90");
        given(engineOilService.findByTitle(anyString())).willReturn(testEngineOil);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/fixerpro/engine-oil/castrol-edge-turbodiesel-5w40-5l"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product-page"))
                .andExpect(MockMvcResultMatchers.model().attribute("product", testEngineOil))
                .andExpect(MockMvcResultMatchers.model().attribute("name", testEngineOil.getTitle()))
                .andExpect(MockMvcResultMatchers.model().attribute("brand", Utilities.getEngineOilBrand(testEngineOil)))
                .andExpect(MockMvcResultMatchers.model().attribute("description", "Amazing oil, it's the best oil, really cool oil..."));
    }
}