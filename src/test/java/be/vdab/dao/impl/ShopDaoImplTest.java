package be.vdab.dao.impl;

import be.vdab.dao.ShopDao;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ShopDaoImplTest {
    private ShopDao eshop = new ShopDaoImpl();

    @Test
    public void listAllShops() {
        assertThat(eshop.listAllShops().get(0).getId()).isEqualTo(1);
        assertThat(eshop.listAllShops()).hasSize(1);
    }
}