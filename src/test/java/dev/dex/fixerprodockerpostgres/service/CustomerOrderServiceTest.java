package dev.dex.fixerprodockerpostgres.service;

import dev.dex.fixerprodockerpostgres.dao.*;
import dev.dex.fixerprodockerpostgres.entity.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceTest {
    @Mock
    private CustomerOrderDAO customerOrderDAO;
    @Mock
    private MailService mailService;
    private CustomerOrderService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerOrderService(customerOrderDAO, mailService);
    }

    @Test
    void processOrderCanSave() {
        // given
        CustomerOrder customerOrder = new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                "his_address", "his_comment", 100, "products");

        // when
        underTest.processOrder(customerOrder);

        // then
        ArgumentCaptor<CustomerOrder> customerOrderArgumentCaptor = ArgumentCaptor.forClass(CustomerOrder.class);
        verify(customerOrderDAO).save(customerOrderArgumentCaptor.capture());

        CustomerOrder capturedCustomerOrder = customerOrderArgumentCaptor.getValue();
        assertThat(capturedCustomerOrder).isEqualTo(customerOrder);
    }

    @Test
    void processOrderCanSendEmail() {
        // given
        CustomerOrder customerOrder = new CustomerOrder("Dexter McPherson", "+359678", "speedy",
                "his_address", "his_comment", 100, "products");

        // when
        underTest.processOrder(customerOrder);

        // then
        ArgumentCaptor<String> toArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> textArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mailService).sendSimpleMessage(toArgumentCaptor.capture(), subjectArgumentCaptor.capture(),
                textArgumentCaptor.capture());

        String capturedTo = toArgumentCaptor.getValue();
        String capturedSubject = subjectArgumentCaptor.getValue();
        String capturedText = textArgumentCaptor.getValue();

        assertThat(capturedSubject).isEqualTo("Order N.: " + customerOrder.getId());
        assertThat(capturedText).isEqualTo(customerOrder.toString());
    }

    @Test
    void canFindAll() {
        // when
        underTest.findAll();

        // then
        verify(customerOrderDAO).findAll();
    }
}