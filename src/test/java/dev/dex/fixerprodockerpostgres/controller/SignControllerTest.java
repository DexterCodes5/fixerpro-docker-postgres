package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.service.*;
import dev.dex.fixerprodockerpostgres.security.SecurityConfigTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

@WebMvcTest(SignController.class)
@Import(SecurityConfigTest.class)
class SignControllerTest {
    @MockBean
    private AccountService accountService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void canLogin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/my-login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    void canSignUp() throws Exception {
        String testRequestBody = """
                {
                    "username" : "dexter",
                    "password" : "my_password",
                    "enabled" : false,
                    "email" : "dexterMail@gmail.com",
                    "hashCode" : 553,
                    "provider" : "LOCAL"
                }""";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/fixerpro/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testRequestBody))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/fixerpro/my-login?signedUp"));
    }

    @Test
    void canEmailVerification() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fixerpro/email-verification?user=553"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("email-verification"));
    }
}