package be.vdab.ui;

import be.vdab.dao.impl.ShopDaoImpl;
import be.vdab.dao.ShopDao;
import be.vdab.entiteiten.Eshop;

import javax.swing.*;
import java.awt.*;

public class SelectShopUI extends JInternalFrame {
    private JInternalFrame frame = new JInternalFrame();
    private JPanel centralPanel, northPanel;
    private JLabel lblShopkeuze, lblGreeting;
    private JComboBox cbShops;
    private JButton btnChoose;
    private ShopDao eshopdao;
    private Eshop eshop;

    public SelectShopUI(JDesktopPane desktop) {
        initComponents();
        layoutComponents();
        initListeners(desktop);
    }

    private void initComponents() {
        centralPanel = new JPanel();
        northPanel = new JPanel();
        lblGreeting = new JLabel();
        lblShopkeuze = new JLabel();
        cbShops = new JComboBox();
        btnChoose = new JButton();
        eshopdao = new ShopDaoImpl();
        for (Eshop shop : eshopdao.listAllShops()) {
            cbShops.addItem(shop);
        }
    }

    private void layoutComponents() {
        lblShopkeuze.setText("choose a shop:");
        lblGreeting.setText("Welcome, we hope you're having a nice day.");
        btnChoose.setText("go");
        northPanel.add(lblGreeting);
        centralPanel.add(lblShopkeuze);
        centralPanel.add(cbShops);
        centralPanel.add(btnChoose);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    protected void initListeners(JDesktopPane desktop) {
        btnChoose.addActionListener(e -> {
            if (cbShops.getSelectedItem() != null) {
                eshop = (Eshop) cbShops.getSelectedItem();
                this.frame.setVisible(false);
                this.frame.dispose();
                this.frame.remove(0);
                desktop.add(new ProductListUI(desktop).getFrame());
                this.frame.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "select a shop, please");
            }
        });
    }

    public JInternalFrame getFrame() {
        return frame;
    }

    public Eshop getEshop() {
        return this.eshop;
    }
}