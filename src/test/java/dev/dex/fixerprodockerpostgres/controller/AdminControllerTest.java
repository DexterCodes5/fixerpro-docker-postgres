package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.oauth2.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.mockito.BDDMockito.*;

@WebMvcTest(controllers = AdminController.class)
@Import(SecurityConfigTest.class)
class AdminControllerTest {
    @MockBean
    private EngineOilService engineOilService;
    @MockBean
    private FilterService filterService;
    @MockBean
    private CustomerOrderService customerOrderService;
    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT", "ADMIN"})
    void canGetAccounts() throws Exception {
        List<Account> testAccounts = List.of(
                new Account("dexter", "test123", true,
                        "dex@gmail.com", 123, Provider.LOCAL),
                new Account("dexter", "test123", true,
                        "dex@gmail.com", 1234, Provider.LOCAL));

        given(accountService.findAll()).willReturn(testAccounts);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/admin/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/admin-accounts"))
                .andExpect(MockMvcResultMatchers.model().attribute("table", "Accounts"))
                .andExpect(MockMvcResultMatchers.model().attribute("accounts", testAccounts));
    }

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT", "ADMIN"})
    void canGetCustomerOrders() throws Exception {
        List<CustomerOrder> testCustomerOrders = List.of(
                new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                        "his_address", "his_comment", 100, "products"),
                new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                        "his_address", "his_comment", 100, "products")
        );

        given(customerOrderService.findAll()).willReturn(testCustomerOrders);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/admin/customer-orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/admin-customer-orders"))
                .andExpect(MockMvcResultMatchers.model().attribute("table", "Customer Orders"))
                .andExpect(MockMvcResultMatchers.model().attribute("customerOrders", testCustomerOrders));
    }

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT", "ADMIN"})
    void canGetEngineOil() throws Exception {
        List<EngineOil> testEngineOils = List.of(
                new EngineOil("CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L",
                        "/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg", "5", "84.90"),
                new EngineOil("MOTUL 6100 SYNERGIE+ 10W-40 5L",
                        "/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg", "5", "78.56")
        );

        given(engineOilService.findAll()).willReturn(testEngineOils);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/admin/engine-oil"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/admin-engine-oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("table", "Engine Oil"))
                .andExpect(MockMvcResultMatchers.model().attribute("engineOils", testEngineOils));
    }

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT", "ADMIN"})
    void canGetFilters() throws Exception {
        List<Filter> testFilters = List.of(
                new Filter("oil-filter", "BOSCH", "1 457 437 003",
                        "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-bosch1.jpg", "16.21"),
                new Filter("oil-filter", "MANN-FILTER", "HU 925/4 X",
                        "[\"bmw-3-e46\", \"bmw-5-e39\"]", "/thumbnails/filters/filter-mann1.jpg", "23.92")
        );

        given(filterService.findAll()).willReturn(testFilters);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/admin/filters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/admin-filters"))
                .andExpect(MockMvcResultMatchers.model().attribute("table", "Filters"))
                .andExpect(MockMvcResultMatchers.model().attribute("filters", testFilters));
    }
}