package be.vdab.ui;

import javax.swing.*;
import java.awt.*;

public class EshopSwingApp extends JFrame {

    private EshopSwingApp() {
        initComponents();
        layoutComponents();
        initListeners();
    }

    private void initComponents() {
        setTitle("E-shop App");
        setSize(1200, 700);
        setLocation(10,10);
       // setLocationRelativeTo(null);
        setVisible(true);
    }

    private void layoutComponents() {
        add(new JLabel("hello"), BorderLayout.NORTH);
        JPanel p = new LogInUI();
        float alignmentX = p.getAlignmentX();
        System.out.println(alignmentX);
        p.setBackground(Color.BLUE);
        add(p, BorderLayout.CENTER);
    }

    private void initListeners() {

    }

    public static void main(String[] args) {
        new EshopSwingApp();
    }

}
