package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import dev.dex.fixerprodockerpostgres.exception.*;
import dev.dex.fixerprodockerpostgres.oauth2.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.crypto.password.*;

import javax.security.auth.login.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountDAO accountDAO;
    @Mock
    private MailService mailService;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AccountService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AccountService(accountDAO, mailService, jdbcTemplate, passwordEncoder);
    }

    @Test
    void canFindAll() {
        // when
        underTest.findAll();

        // then
        verify(accountDAO).findAll();
    }

    @Test
    void canSignUp() {
        // given
        Account testAccount = new Account("dexter", "test123", true,
                        "dex@gmail.com", 0, Provider.LOCAL);
        int testHashCode = testAccount.getUsername().hashCode();

        // when
        underTest.signUp(testAccount);

        // then
        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(accountDAO).findByEmail(emailArgumentCaptor.capture());
        assertThat(emailArgumentCaptor.getValue()).isEqualTo(testAccount.getEmail());

        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(accountDAO).findByUsername(usernameArgumentCaptor.capture());
        assertThat(usernameArgumentCaptor.getValue()).isEqualTo(testAccount.getUsername());

        ArgumentCaptor<String> passwordArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(passwordEncoder).encode(passwordArgumentCaptor.capture());
        assertThat(passwordArgumentCaptor.getValue()).isEqualTo("test123");

        ArgumentCaptor<String> emailArgumentCaptor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> testArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mailService).sendSimpleMessage(emailArgumentCaptor2.capture(), subjectArgumentCaptor.capture(),
                testArgumentCaptor.capture());
        assertThat(emailArgumentCaptor2.getValue()).isEqualTo(testAccount.getEmail());
        assertThat(subjectArgumentCaptor.getValue()).isEqualTo("Email Verification");
        assertThat(testArgumentCaptor.getValue()).isEqualTo("""
                Email Verification Link: http://localhost:8080/fixerpro/email-verification?user=""" + testHashCode);

        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountDAO).save(accountArgumentCaptor.capture());
        assertThat(accountArgumentCaptor.getValue()).isEqualTo(testAccount);
    }

    @Test
    void willThrowWhenEmailDoesNotExistInSignUp() {
        // given
        Account testAccount = new Account("dexter", "test123", true,
                "dex@gmail.com", 0, Provider.LOCAL);

        given(accountDAO.findByEmail(testAccount.getEmail())).willReturn(testAccount);

        // when
        // then
        assertThatThrownBy(() -> underTest.signUp(testAccount))
                .isInstanceOf(EmailExistsException.class)
                .hasMessageContaining("There is an account with that email address: " + testAccount.getEmail());
    }

    @Test
    void willThrowWhenUsernameDoesNotExistInSignUp() {
        // given
        Account testAccount = new Account("dexter", "test123", true,
                "dex@gmail.com", 0, Provider.LOCAL);

        given(accountDAO.findByUsername(testAccount.getUsername())).willReturn(testAccount);

        // when
        // then
        assertThatThrownBy(() -> underTest.signUp(testAccount))
                .isInstanceOf(UsernameExistsException.class)
                .hasMessageContaining("There is an account with that username: " + testAccount.getUsername());
    }
    @Test
    void canFindByHashCode() throws AccountNotFoundException {
        // given
        Account testAccount = new Account("dexter", "test123", true,
                "dex@gmail.com", 0, Provider.LOCAL);
        int testHashCode = testAccount.getUsername().hashCode();

        given(accountDAO.findByHashCode(testHashCode)).willReturn(testAccount);

        // when
        underTest.findByHashCode(testHashCode);

        // then
        ArgumentCaptor<Integer> hashCodeArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(accountDAO).findByHashCode(hashCodeArgumentCaptor.capture());
        assertThat(hashCodeArgumentCaptor.getValue()).isEqualTo(testHashCode);

        ArgumentCaptor<String> sqlArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> roleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(jdbcTemplate).update(sqlArgumentCaptor.capture(), usernameArgumentCaptor.capture(),
                roleArgumentCaptor.capture());
        assertThat(sqlArgumentCaptor.getValue()).isEqualTo("INSERT INTO authorities VALUES (?,?)");
        assertThat(usernameArgumentCaptor.getValue()).isEqualTo(testAccount.getUsername());
        assertThat(roleArgumentCaptor.getValue()).isEqualTo("ROLE_CLIENT");

        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountDAO).save(accountArgumentCaptor.capture());
        assertThat(accountArgumentCaptor.getValue()).isEqualTo(testAccount);
    }

    @Test
    void willThrowWhenHashCodeDoesNotExistInFindByHashCode() {
        // given
        Account testAccount = new Account("dexter", "test123", true,
                "dex@gmail.com", 0, Provider.LOCAL);
        int testHashCode = testAccount.getUsername().hashCode();

        given(accountDAO.findByHashCode(testHashCode)).willThrow(
                new EmptyResultDataAccessException(20));

        // when
        // then
        assertThatThrownBy(() -> underTest.findByHashCode(testHashCode))
                .isInstanceOf(AccountNotFoundException.class)
                .hasMessageContaining("Cannot find account by hashcode. Hashcode: " + testHashCode);
    }


    @Test
    void canProcessOAuthPostLogin() {
        // given
        String testUsername = "dexter";
        String testEmail = "dex@gmail.com";
        Account testAccount = new Account(testUsername, "test123", true,
                testEmail, 0, Provider.LOCAL);

        // when
        underTest.processOAuthPostLogin(testUsername, testEmail);

        // then
        ArgumentCaptor<String> sqlArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> enabledArgumentCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<String> emaiArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> providerArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(jdbcTemplate).update(sqlArgumentCaptor.capture(), usernameArgumentCaptor.capture(),
                enabledArgumentCaptor.capture(), emaiArgumentCaptor.capture(), providerArgumentCaptor.capture());
        assertThat(sqlArgumentCaptor.getValue()).isEqualTo(
                "INSERT INTO accounts (username, enabled, email, provider) VALUES (?,?,?,?)");
        assertThat(usernameArgumentCaptor.getValue()).isEqualTo(testUsername);
        assertThat(enabledArgumentCaptor.getValue()).isEqualTo(true);
        assertThat(emaiArgumentCaptor.getValue()).isEqualTo(testEmail);
        assertThat(providerArgumentCaptor.getValue()).isEqualTo("GOOGLE");

        ArgumentCaptor<String> roleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(jdbcTemplate).update(sqlArgumentCaptor.capture(), usernameArgumentCaptor.capture(),
                roleArgumentCaptor.capture());
        assertThat(sqlArgumentCaptor.getValue()).isEqualTo("INSERT INTO authorities VALUES (?,?)");
        assertThat(usernameArgumentCaptor.getValue()).isEqualTo(testUsername);
        assertThat(roleArgumentCaptor.getValue()).isEqualTo("ROLE_CLIENT");
    }
}