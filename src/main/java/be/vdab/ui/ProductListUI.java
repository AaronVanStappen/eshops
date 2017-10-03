package be.vdab.ui;

import be.vdab.dao.BasketDao;
import be.vdab.dao.ProductDao;
import be.vdab.dao.impl.BasketDaoImpl;
import be.vdab.dao.impl.ProductDaoImpl;
import be.vdab.entiteiten.Order;
import be.vdab.entiteiten.OrderView;
import be.vdab.entiteiten.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class ProductListUI extends JInternalFrame {
    private JInternalFrame frame = new JInternalFrame();
    private DefaultListModel<Product> listModel;
    private DefaultListModel<OrderView>orderListModel;
    private JSplitPane pane;
    private ProductDao products;
    private BasketDao basketDao;
    private JPanel southPanel, southWestPanel, northPanel, northWestPanel, southEastPanel;
    private JList<Product> productJList, basket;
    private JList<OrderView> orderList;
    private JButton btnAdd, btnRemove, btnFindProduct, btnSort, btnSortPriceAsc, btnSortPriceDesc, btnOrder;

    public ProductListUI(JDesktopPane desktop, int customerId, int eshopId) {
        initComponents();
        layoutComponents();
        initListeners(desktop, customerId, eshopId);
        setVisible(true);
    }

    private void initComponents() {
        listModel = new DefaultListModel<>();
        orderListModel = new DefaultListModel<>();
        basket = new JList<>(listModel);
        productJList = new JList<>();
        orderList = new JList<>(orderListModel);
        products = new ProductDaoImpl();
        basketDao = new BasketDaoImpl();
        productJList.setListData(products.getProducts().toArray(new Product[0]));
        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, productJList, basket);
        pane.setSize(1200, 600);
        btnAdd = new JButton("add to basket");
        btnRemove = new JButton("remove from basket");
        btnSort = new JButton("sort by name");
        btnSortPriceAsc = new JButton("sort by price ascending");
        btnSortPriceDesc = new JButton("sort by price descending");
        btnFindProduct = new JButton("search product");
        btnOrder = new JButton("order");
        southPanel = new JPanel();
        southEastPanel = new JPanel();
        southWestPanel = new JPanel();
        northPanel = new JPanel();
        northWestPanel = new JPanel();
    }

    private void layoutComponents() {
        setVisible(true);
        pane.setVisible(true);
        southPanel.setVisible(true);
        southWestPanel.setVisible(true);
        southEastPanel.setVisible(true);
        northPanel.setVisible(true);
        northWestPanel.setVisible(true);
        southWestPanel.add(btnAdd, BorderLayout.EAST);
        southWestPanel.add(btnRemove, BorderLayout.WEST);
        southEastPanel.add(btnOrder, BorderLayout.EAST);
        southPanel.add(southWestPanel, BorderLayout.WEST);
        southPanel.add(southEastPanel, BorderLayout.SOUTH);
        northWestPanel.add(btnFindProduct, BorderLayout.WEST);
        northWestPanel.add(btnSort, BorderLayout.WEST);
        northWestPanel.add(btnSortPriceAsc, BorderLayout.WEST);
        northWestPanel.add(btnSortPriceDesc, BorderLayout.WEST);
        northPanel.add(northWestPanel, BorderLayout.NORTH);
        frame.add(pane, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    private void initListeners(JDesktopPane desktop, int customerId, int eshopId) {
        btnAdd.addActionListener(e -> {
            Product product = productJList.getSelectedValue();
            if (listModel.contains(productJList.getSelectedValue())) {
                int i = listModel.indexOf(productJList.getSelectedValue());
                int amount = listModel.getElementAt(i).getAmount();
                int stock = listModel.getElementAt(i).getStock();
                System.out.println("calling stock: " + stock);
                amount++;
                    if (stock >= amount) {
                        listModel.remove(i);
                        listModel.insertElementAt(new Product(product.getId(), product.getName(), product.getPrice(),
                                product.getStock(), amount), i);
                    } else if (amount > product.getAmount()) {
                        JOptionPane.showMessageDialog(null, "We're sorry! It seems your order amount" +
                                " exceeds our stock of this product.");
                    }
            } else {
                if (product.getStock() > 0) {
                    listModel.addElement(new Product(product.getId(), product.getName(), product.getPrice(),
                            product.getStock(), 1));
                } else {
                    JOptionPane.showMessageDialog(null, "We're sorry! It seems your order amount" +
                            " exceeds our stock of this product.");
                }
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
            } else {
                JOptionPane.showMessageDialog(null, "no product selected in basket.");
            }
        });
        btnSort.addActionListener(e -> {
            productJList.setListData(products.sortProducts().toArray(new Product[0]));
        });
        btnSortPriceAsc.addActionListener(e -> {
            productJList.setListData(products.sortProductsByPriceAsc().toArray(new Product[0]));
        });
        btnSortPriceDesc.addActionListener(e -> {
            productJList.setListData(products.sortProductsByPriceDesc().toArray(new Product[0]));
        });
        btnFindProduct.addActionListener(e -> {
            String product = JOptionPane.showInputDialog("enter the name of a product");
            if (products.findProducts(product) != null) {
                productJList.setListData(products.findProducts(product).toArray(new Product[0]));
                pane.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "product not found");
            }
        });
        btnOrder.addActionListener(e -> {
            String pay = "visa";
            Date date = Date.valueOf(LocalDate.now());
            for (int i = 0; i < listModel.size(); i++) {
                double orderTotal = listModel.getElementAt(i).getAmount() * listModel.getElementAt(i).getPrice();
                int productId = listModel.getElementAt(i).getId();
                int amount = listModel.getElementAt(i).getAmount();
                System.out.println("amount: " + amount + " prod: " + productId + " shopId: " + eshopId + " customerId: " + customerId +
                " order total: " + orderTotal);
                try {
                    basketDao.addToDB(new Order(pay, orderTotal, date, customerId, eshopId), productId, amount);
                    orderListModel.addElement(new OrderView(productId, listModel.getElementAt(i).getName(), date, listModel.getElementAt(i).getPrice(),
                            amount, orderTotal));
                    System.out.println("orderList: " + orderListModel.getElementAt(i));
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    desktop.add(new OrderOverviewUI(desktop, orderListModel).getFrame());
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "could not add basket to database");
                }
            }
        });
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
