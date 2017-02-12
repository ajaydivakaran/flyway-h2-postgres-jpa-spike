package my.spike.repository;


import my.spike.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Sql(scripts = {"/clearCustomer.sql", "/createCustomer.sql"})
    public void shouldFetchUsers() {
        final Customer customer = new Customer();
        customer.setFirstName("jane");
        customerRepository.save(customer);
        final List<Customer> customers = customerRepository.findAll();
        assertEquals(3, customers.size());
    }

    @Test
    @Sql(scripts = {"/clearCustomer.sql", "/createCustomer.sql"})
    public void shouldFetchUsersAgain() {
        final List<Customer> customers = customerRepository.findAll();
        assertEquals(2, customers.size());
    }

}