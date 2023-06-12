package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.*;

@Controller
@RequestMapping("/fixerpro")
public class SignController {
    private final AccountService accountService;

    @Autowired
    public SignController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/my-login")
    public String login() {
        return "login";
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody Account account) {
        accountService.signUp(account);
        return "redirect:/fixerpro/my-login?signedUp";
    }

    @GetMapping("/email-verification")
    public String emailVerification(@RequestParam("user") int userHashCode) throws AccountNotFoundException {
        Account account = accountService.findByHashCode(userHashCode);
        return "email-verification";
    }

}
