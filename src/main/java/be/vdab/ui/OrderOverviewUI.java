package be.vdab.ui;

import be.vdab.entiteiten.OrderView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OrderOverviewUI extends JInternalFrame{
    private JInternalFrame frame = new JInternalFrame();
    private JList orders;
    private JPanel centralPanel, southPanel;
    private JButton btnClose;
    private JLabel lblPriceTag, lblTotalPrice;
    private static double totalPrice;

    public OrderOverviewUI(JDesktopPane desktop, DefaultListModel<OrderView> orderListModel){
        initComponents(orderListModel);
        layoutComponents(orderListModel);
        initListeners(desktop);
    }

    private void initComponents(DefaultListModel<OrderView> orderListModel) {
        orders = new JList(orderListModel);
        lblPriceTag = new JLabel("total price");
        lblTotalPrice = new JLabel();
        centralPanel = new JPanel();
        southPanel = new JPanel();
        btnClose = new JButton("close");

    }

    private void layoutComponents(DefaultListModel<OrderView> orderListModel) {
        southPanel.add(btnClose, BorderLayout.EAST);
        southPanel.add(lblPriceTag, BorderLayout.EAST);
        totalPrice = this.getTotalPrice(orderListModel);
        lblTotalPrice.setText(String.valueOf(totalPrice));
        southPanel.add(lblTotalPrice, BorderLayout.EAST);
        centralPanel.add(orders, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    private void initListeners(JDesktopPane desktop) {
        btnClose.addActionListener(e -> {
            desktop.removeAll();
        });
    }

    private double getTotalPrice(DefaultListModel<OrderView> orderList) {
        double price = 0.00;
        for (int i = 0; i < orderList.size(); i++) {
            price = price + orderList.getElementAt(i).getTotalPrice();
        }
        System.out.println(price);
        return price;
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
