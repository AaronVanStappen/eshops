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

    public OrderOverviewUI(JDesktopPane desktop, DefaultListModel<OrderView> orderListModel, double orderTotal){
        initComponents(orderListModel);
        layoutComponents(orderTotal);
        initListeners();
    }

    private void initComponents(DefaultListModel<OrderView> orderListModel) {
        orders = new JList(orderListModel);
        lblPriceTag = new JLabel("total price");
        lblTotalPrice = new JLabel();
        centralPanel = new JPanel();
        southPanel = new JPanel();
        btnClose = new JButton("close");

    }

    private void layoutComponents(Double orderTotal) {
        southPanel.add(btnClose, BorderLayout.EAST);
        southPanel.add(lblPriceTag, BorderLayout.EAST);
        lblTotalPrice.setText(String.valueOf(orderTotal));
        southPanel.add(lblTotalPrice, BorderLayout.EAST);
        centralPanel.add(orders, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    private void initListeners() {
        btnClose.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /*private double getTotal(DefaultListModel<OrderView> orderList) {
        double price = 0.00;
        for (int i = 0; i < orderList.size(); i++) {
            price = price + orderList.getElementAt(i).getTotalPrice();
        }
        System.out.println("prijs= " + price);
        return price;
    }*/

    public JInternalFrame getFrame() {
        return frame;
    }
}
