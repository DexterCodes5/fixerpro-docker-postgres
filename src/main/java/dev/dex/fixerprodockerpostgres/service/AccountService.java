package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.exception.*;
import dev.dex.fixerprodockerpostgres.oauth2.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.security.auth.login.*;
import java.util.*;

@Service
public class AccountService {
    private final AccountDAO accountDAO;
    private final MailService mailService;
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountDAO accountDAO, MailService mailService, JdbcTemplate jdbcTemplate,
                          PasswordEncoder passwordEncoder) {
        this.accountDAO = accountDAO;
        this.mailService = mailService;
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Transactional
    public Account signUp(Account account) throws RuntimeException {
        if (this.accountDAO.findByEmail(account.getEmail()) != null) {
            throw new EmailExistsException("There is an account with that email address: " + account.getEmail());
        }
        if (this.accountDAO.findByUsername(account.getUsername()) != null) {
            throw new UsernameExistsException("There is an account with that username: " + account.getUsername());
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        int hashCode = account.getUsername().hashCode();
        account.setHashCode(hashCode);
        account.setProvider(Provider.LOCAL);
        mailService.sendSimpleMessage(
                account.getEmail(),
                "Email Verification",
                """
                        Email Verification Link: http://localhost:8080/fixerpro/email-verification?user=""" + hashCode);
        return accountDAO.save(account);
    }

    @Transactional
    public Account findByHashCode(int hashCode) throws AccountNotFoundException {
        // if account exists enable it
        Account account = null;

        try {
            account = accountDAO.findByHashCode(hashCode);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new AccountNotFoundException("Cannot find account by hashcode. Hashcode: " + hashCode);
        }

        return enableAccount(account);
    }

    private Account enableAccount(Account account) {
        account.setEnabled(true);
        jdbcTemplate.update("INSERT INTO authorities VALUES (?,?)", account.getUsername(), "ROLE_CLIENT");
        return accountDAO.save(account);
    }

    @Transactional
    public void processOAuthPostLogin(String username, String email) {
        Account account = accountDAO.findByUsername(username);
        if (account != null) {
            return;
        }

        jdbcTemplate.update("INSERT INTO accounts (username, enabled, email, provider) VALUES (?,?,?,?)",
                username, true, email, "GOOGLE");
        jdbcTemplate.update("INSERT INTO authorities VALUES (?,?)", username, "ROLE_CLIENT");
    }

    public List<GrantedAuthority> findAuthoritiesByUsername(String username) {
//        List<String> authorityStrings = jdbcTemplate.queryForList("SELECT authority FROM authorities WHERE username=?", String.class, username);
//        List<GrantedAuthority> authorities = authorityStrings.stream()
//                .map(authString -> new SimpleGrantedAuthority(authString))
//                .collect(Collectors.toList());
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }
}
