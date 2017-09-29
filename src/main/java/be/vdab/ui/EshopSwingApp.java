package be.vdab.ui;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;

public class EshopSwingApp extends JFrame {
    private JPanel panel = new JPanel();
    private JInternalFrame f = new JInternalFrame();
    private JDesktopPane desktop = new JDesktopPane();

    public EshopSwingApp() {
        initComponents();
        layoutComponents();
        initListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        setTitle("e-shop App");
        setSize(1200, 700);
        setLocationRelativeTo(null);
    }

    public void layoutComponents() {
        LogInUI login = new LogInUI();
        JLabel welkom = new JLabel();
        welkom.setText("Welcome " + ", you magnificent BEAST!");
        panel.add(welkom);
        desktop.setOpaque(false);
        f.add(panel, BorderLayout.CENTER);
        f.setSize(300, 200);
        f.setVisible(true);
        desktop.add(f);
        setContentPane(desktop);
    }

    private void initListeners() {

    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public static void main(String[] args) {
        new EshopSwingApp();
    }

}