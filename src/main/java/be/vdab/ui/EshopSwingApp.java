package be.vdab.ui;

import javax.swing.*;
import java.awt.*;

public class EshopSwingApp extends JFrame {
    private JTable tblProducts = new JTable();
    private JScrollPane spProducts = new JScrollPane(tblProducts);

    private EshopSwingApp() {
        initComponents();
        layoutComponents();
        initListeners();
    }

    private void initComponents() {
        setTitle("E-shop App");
        setSize(1200, 700);
        setLocation(200, 200);
        setVisible(true);
    }

    private void layoutComponents() {
        JPanel middlePanel = new JPanel();
        middlePanel.add(spProducts);
        add(middlePanel, BorderLayout.CENTER);
    }

    private void initListeners() {

    }

    public static void main(String[] args) {
        new EshopSwingApp();
    }

}
