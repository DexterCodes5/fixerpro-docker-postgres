package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.service.*;
import dev.dex.fixerprodockerpostgres.security.SecurityConfigTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

@WebMvcTest(controllers = CustomerOrderController.class)
@Import(SecurityConfigTest.class)
class CustomerOrderControllerTest {
    @MockBean
    private CustomerOrderService customerOrderService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT"})
    void canGoToCart() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/checkout"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("checkout"));
    }

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT"})
    void canTakeOrder() throws Exception {
        String requestBody = """
                {
                    "nameAndSurname" : "Dimitar Tanchev",
                    "telephone" : "08534324",
                    "deliveryMethod" : "Speed",
                    "address" : "Some Address",
                    "comment" : "My Comment",
                    "total" : 100.50,
                    "products" : "A lot of products"
                }""";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/fixerpro/checkout/take-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/fixerpro/checkout/successful-order"));
    }

    @Test
    @WithMockUser(username = "dexter", roles = {"CLIENT"})
    void canSuccessfulOrder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/checkout/successful-order"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("successful-order"));
    }
}