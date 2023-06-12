package dev.dex.fixerprodockerpostgres.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.boot.web.servlet.error.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            model.addAttribute("status", statusCode);
        }
        else {
            model.addAttribute("status", "unknown");
        }
        return "error/something-went-wrong";
    }
}
