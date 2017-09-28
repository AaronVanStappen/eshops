package be.vdab.ui;

import javax.swing.*;
import java.awt.*;

public class EshopSwingApp extends JFrame {

    public EshopSwingApp() {
        initComponents();
        layoutComponents();
        initListeners();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("e-shop App");
        setSize(1200, 700);
        setLocationRelativeTo(null);
    }

    private void layoutComponents() {
        LogInUI login = new LogInUI();
        if (login.hasVisibility()){
            add(login, BorderLayout.CENTER);
        } else {
            JLabel welkom = new JLabel();
            welkom.setText("Welkom " + login.getCustomer().getFirst_name() + ", you magnificent BEAST!");
            add(welkom);
        }
    }

    private void initListeners() {

    }

    public static void main(String[] args) {
        new EshopSwingApp();
    }

}
