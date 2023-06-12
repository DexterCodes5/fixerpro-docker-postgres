package dev.dex.fixerprodockerpostgres.controller;

import jakarta.servlet.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

@RestController
public class SessionController {
    private final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";

    @GetMapping("/home")
    public String home(Principal principal, HttpSession session) {
        incrementCount(session, HOME_VIEW_COUNT);
        return "Hello, " + principal.getName();
    }

    @GetMapping("/count")
    public String count(HttpSession session) {
        return "HOME_VIEW_COUNT: " + session.getAttribute(HOME_VIEW_COUNT);
    }

    private void incrementCount(HttpSession session, String attr) {
        var homeViewCount = session.getAttribute(attr) == null ? 0 : (int) session.getAttribute(attr);
        session.setAttribute(attr, homeViewCount + 1);
    }

}
