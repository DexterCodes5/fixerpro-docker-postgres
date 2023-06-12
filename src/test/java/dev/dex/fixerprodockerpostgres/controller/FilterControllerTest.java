package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import dev.dex.fixerprodockerpostgres.security.SecurityConfigTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@WebMvcTest(controllers = FilterController.class)
@Import(SecurityConfigTest.class)
class FilterControllerTest {
    @MockBean
    private FilterService filterService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void canGetFiltersBasePage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/filters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("filters"));
    }

    @Test
    void chooseFilterType() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/filters/bmw/3-e46/320i-163"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("filters"))
                .andExpect(MockMvcResultMatchers.model().attribute("displayFilterTypes", "true"));
    }

    @Test
    void canGetFilters() throws Exception {
        List<Filter> testFilters = List.of(
                new Filter("oil-filter", "BOSCH", "1 457 437 003",
                        "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21"),
                new Filter("oil-filter", "MANN-FILTER", "HU 925/4 X",
                        "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-mann1.jpg", "23.92")
        );
        given(filterService.findFilters("bmw", "3-e46", "oil-filter")).willReturn(testFilters);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/filters/bmw/3-e46/320i-163/oil-filter"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("filters"))
                .andExpect(MockMvcResultMatchers.model().attribute("displayFilterTypes", "true"))
                .andExpect(MockMvcResultMatchers.model().attribute("filters", testFilters));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/filters/bmw/3-e46/320i-163/oil-fil"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("filters"))
                .andExpect(MockMvcResultMatchers.model().attribute("displayFilterTypes", "true"))
                .andExpect(MockMvcResultMatchers.model().attribute("outOfStock", true));
    }

    @Test
    void canOpenFilterPage() throws Exception {
        String testFilterNumber = "1457437003";
        Filter testFilter = new Filter("oil-filter", "BOSCH", "1 457 437 003",
                "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21");
        given(filterService.findFilterByNumber(testFilterNumber)).willReturn(testFilter);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/filters/" + testFilterNumber))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product-page"))
                .andExpect(MockMvcResultMatchers.model().attribute("product", testFilter))
                .andExpect(MockMvcResultMatchers.model().attribute("name", testFilter.getBrand()
                        + " " + testFilter.getNumber()))
                .andExpect(MockMvcResultMatchers.model().attribute("brand", testFilter.getBrand()))
                .andExpect(MockMvcResultMatchers.model().attribute("description",
                        "Amazing filter, it's the best filter, really cool filter..."));
    }
}