package be.vdab.ui;

import be.vdab.dao.BasketDao;
import be.vdab.dao.ProductDao;
import be.vdab.dao.impl.BasketDaoImpl;
import be.vdab.dao.impl.ProductDaoImpl;
import be.vdab.entiteiten.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Types;

public class ProductListUI extends JInternalFrame {
    private JInternalFrame frame = new JInternalFrame();
    private DefaultListModel<Product> listModel;
    private JSplitPane pane;
    private JScrollPane productsScroll, basketScroll;
    private ProductDao products;
    private BasketDao basketDao;
    private JPanel southPanel;
    private JList<Product> productJList, basket;
    private JButton btnAdd, btnRemove;

    public ProductListUI(JDesktopPane desktop) {
        initComponents();
        layoutComponents();
        initListeners(desktop);
        setVisible(true);
    }

    private void initComponents() {
        listModel = new DefaultListModel<>();
        listModel.addElement(new Product(0, null, 0.00, 0, 0));
        basket = new JList<>(listModel);
        productJList = new JList<>();
        products = new ProductDaoImpl();
        basketDao = new BasketDaoImpl();
        productJList.setListData(products.getProducts().toArray(new Product[0]));
        productsScroll = new JScrollPane(productJList);
        basketScroll = new JScrollPane(basket);
        productsScroll.setVisible(true);
        basketScroll.setVisible(true);
        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, productsScroll, basketScroll);
        pane.setSize(1200, 600);
        btnAdd = new JButton("add to basket");
        btnRemove = new JButton("remove from basket");
        southPanel = new JPanel();
    }

    private void layoutComponents() {
        setVisible(true);
        pane.setVisible(true);
        southPanel.setVisible(true);
        southPanel.add(btnAdd, BorderLayout.EAST);
        southPanel.add(btnRemove, BorderLayout.WEST);
        frame.add(pane, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    private void initListeners(JDesktopPane desktop) {
        btnAdd.addActionListener(e -> {
            Product product = productJList.getSelectedValue();
            if (listModel.contains(product)) {
                int i = listModel.indexOf(product);
                int amount = listModel.getElementAt(i).getAmount();
                amount++;
                if (amount <= product.getStock()) {
                    listModel.remove(i);
                    listModel.insertElementAt(new Product(product.getId(), product.getName(), product.getPrice(),
                            product.getStock(), amount), i);
                } else if (amount > product.getAmount()){
                 JOptionPane.showMessageDialog(null, "your order amount exceeds the stock of this product");
                }
            } else {
                listModel.addElement(new Product(product.getId(), product.getName(), product.getPrice(),
                        product.getStock(), 1));

            }
        });
        btnRemove.addActionListener(e -> {
            Product product = basket.getSelectedValue();
            if (listModel.contains(product)) {
                int i = listModel.indexOf(product);
                int amount = listModel.getElementAt(i).getAmount();
                if (amount > 1) {
                    amount--;
                    listModel.remove(i);
                    listModel.insertElementAt(new Product(product.getId(), product.getName(), product.getPrice(),
                            product.getStock(), amount), i);
                } else {
                    listModel.remove(i);
                }
            }
        });
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
