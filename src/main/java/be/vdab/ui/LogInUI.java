package be.vdab.ui;

import be.vdab.dao.CustomerDao;
import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LogInUI extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;
    private boolean visible = true;
    private Customer customer;

    public LogInUI() {
        initComponents();
        layoutComponents();
        initListeners();
    }

    private void initComponents() {
        username = new JTextField(10);
        password = new JPasswordField(10);
        usernameLabel = new JLabel("username");
        passwordLabel = new JLabel("password");
        btnLogin = new JButton("login");
        Dimension dim = new Dimension();
        dim.setSize(25, 5);
    }

    private void layoutComponents() {
        GridLayout layout = new GridLayout();
        layout.setColumns(5);
        layout.setRows(15);
        add(usernameLabel);
        add(username);
        add(passwordLabel);
        add(password);
        add(btnLogin);
    }

    private void initListeners() {
        btnLogin.addActionListener(e -> {
            String userLog = username.getText();
            char[] passwordC = password.getPassword();
            String passwordS = "";
            for (int i = 0; i < passwordC.length; i++) {
                passwordS += passwordC[i];
            }
            User user = new CustomerDaoImpl().findByLoginAndUsername(userLog, passwordS);
            if (user != null) {
                customer = new CustomerDaoImpl().findCustomers(userLog);
                giveVisibility(false);
                setVisible(false);
                new EshopSwingApp();
            } else {
               JOptionPane.showMessageDialog( null, "username and/or password incorrect");
               username.setText("");
               password.setText("");
            }
        });
    }

    private void giveVisibility(boolean b) {
        this.visible = b;
    }

    public boolean hasVisibility() {
        return visible;
    }

    public Customer getCustomer() {
        return customer;
    }
}
