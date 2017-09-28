package be.vdab.ui;

import javax.swing.*;
import java.awt.*;

public class LogInUI extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;

    public LogInUI() {
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        setSize(500, 500);
        username = new JTextField("username");
        password = new JPasswordField("password");
        usernameLabel = new JLabel("username");
        passwordLabel = new JLabel("password");
        btnLogin = new JButton("login");
        setVisible(true);
    }

    private void layoutComponents() {
        setLayout(new FlowLayout());
        add(usernameLabel);
        add(username);
        add(passwordLabel);
        add(password);
        add(btnLogin);
    }
}
