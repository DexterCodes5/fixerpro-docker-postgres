package dev.dex.fixerprodockerpostgres.controller;

import dev.dex.fixerprodockerpostgres.exception.*;
import jakarta.servlet.http.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.*;

@ControllerAdvice
public class FixerproExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(FixerproExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EngineOilNotFoundException.class)
    public String handleEngineOilNotFoundException(HttpServletRequest request, Exception ex, Model model) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);

        model.addAttribute("status", 404);
        return "/error/something-went-wrong";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(HttpServletRequest request, Exception ex, Model model) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);

        model.addAttribute("status", 400);
        return "/error/something-went-wrong";
    }

    @ExceptionHandler(EmailExistsException.class)
    public String handleEmailExistsException(HttpServletRequest request, Exception ex) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);
        return "redirect:/fixerpro/my-login?emailExists";
    }

    @ExceptionHandler(UsernameExistsException.class)
    public String handleUsernameExistsException(HttpServletRequest request, Exception ex) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);
        return "redirect:/fixerpro/my-login?usernameExists";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(HttpServletRequest request, Exception ex, Model model) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);

        model.addAttribute("status", 404);
        return "/error/something-went-wrong";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public String handleAccountNotFoundException(HttpServletRequest request, Exception ex, Model model) {
        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);

        model.addAttribute("status", 404);
        return "/error/something-went-wrong";
    }

    // I can't catch Exceptions from reCaptcha Filter, this Controller Advice catches exceptions
    // in Dispatcher Servlet
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(BadCredentialsException.class)
//    public String handleBadCredentialsException(HttpServletRequest request, EngineOilNotFoundException ex, Model model) {
//        LOG.error("Request: " + request.getRequestURI() + " raised " + ex);
//
//        model.addAttribute("status", 401);
//        return "/error/something-went-wrong";
//    }
}
