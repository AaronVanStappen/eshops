package be.vdab.dao.impl;

import be.vdab.dao.CustomerDao;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CustomerDaoImplTest {
    private CustomerDao customer = new CustomerDaoImpl();

    @Test
    public void findCustomers() {
        assertThat(customer.findCustomers("Menten", "Julie", "JulieM").getEmail())
                .isEqualTo("julie@menten.be");
        assertThat(customer.findCustomers("Menten", "Julie", "JulieM").getEmail())
                .isNotNull();
        assertThat(customer.findCustomers("Menten", "Julie", "JulieM").getId())
                .isEqualTo(2);
    }

    @Test
    public void findByLoginAndUsername() {
    }

}