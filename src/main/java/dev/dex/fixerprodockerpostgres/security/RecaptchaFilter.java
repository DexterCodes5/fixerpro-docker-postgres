package dev.dex.fixerprodockerpostgres.security;

import dev.dex.fixerprodockerpostgres.model.*;
import dev.dex.fixerprodockerpostgres.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.web.filter.*;

import java.io.*;


public class RecaptchaFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(RecaptchaFilter.class);
    private final RecaptchaService recaptchaService;

    @Autowired
    public RecaptchaFilter(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {
            String recaptcha = request.getHeader("recaptcha");
            RecaptchaResponse recaptchaResponse = recaptchaService.validateToken(recaptcha);
            // add recaptcha response has a score 0.0 -> 1.0
            if (!recaptchaResponse.success() || recaptchaResponse.score() < 0.5) {
                LOG.info("Invalid reCAPTCHA token");
                throw new BadCredentialsException("Invalid reCAPTCHA token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
