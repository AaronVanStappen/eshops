package be.vdab.ui;

import javax.swing.*;

public class EshopApp extends JFrame {
    private JPanel centerPanel = new JPanel();
    private JDesktopPane desktop = new JDesktopPane();

    public EshopApp() {
        setDefaultCloseOperation(3);
        setVisible(true);
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        setTitle("e-shop App");
        setSize(1220, 750);
        setLocationRelativeTo(null);
    }

    public void layoutComponents() {
        LogInUI login = new LogInUI(desktop);
        desktop.add(login.getFrame());
        setContentPane(desktop);
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public static void main(String[] args) {
        new EshopApp();
    }
}
