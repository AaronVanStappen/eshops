package be.vdab.ui;

import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.User;

import javax.swing.*;
import java.awt.*;

public class LogInUI extends JInternalFrame {
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;
    private JInternalFrame frame = new JInternalFrame();
    private JPanel panel = new JPanel();
    private Customer customer;
    private int customerId;
    private String customerName;
    private String customerFirst;
    private String customerEmail;
    private String customerAddress;



    public LogInUI(JDesktopPane desktop) {
        initComponents();
        layoutComponents();
        initListeners(desktop);
    }

    private void initComponents()  {
        username = new JTextField(20);
        password = new JPasswordField(20);
        usernameLabel = new JLabel("username");
        passwordLabel = new JLabel("password");
        btnLogin = new JButton("login");
    }
    private void layoutComponents() {
        panel.add(usernameLabel, BorderLayout.CENTER);
        panel.add(username, BorderLayout.CENTER);
        panel.add(passwordLabel, BorderLayout.CENTER);
        panel.add(password, BorderLayout.CENTER);
        panel.add(btnLogin, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        setTitle("LogIn");
        setVisible(true);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    private void initListeners(JDesktopPane desktop) {
        btnLogin.addActionListener(e -> {
            String userLog = username.getText();
            char[] passwordC = password.getPassword();
            String passwordS = "";
            for (int i = 0; i < passwordC.length; i++) {
                passwordS += passwordC[i];
            }
            User user = new CustomerDaoImpl().findByLoginAndUsername(userLog, passwordS);
            if (user != null) {
                customerId = new CustomerDaoImpl().findCustomers(userLog).getId();
                customerName = new CustomerDaoImpl().findCustomers(userLog).getName();
                customerFirst = new CustomerDaoImpl().findCustomers(userLog).getFirst_name();
                customerEmail = new CustomerDaoImpl().findCustomers(userLog).getEmail();
                customerAddress = new CustomerDaoImpl().findCustomers(userLog).getDelivAd();

                customer = new Customer(customerId, userLog, passwordS, customerName, customerFirst, customerEmail,
                        customerAddress);

                this.frame.setVisible(false);
                this.frame.dispose();
                this.frame.remove(0);
                desktop.add(new SelectShopUI(desktop, customerId).getFrame());
                this.frame.repaint();
            } else {
                JOptionPane.showMessageDialog( null, "username and/or password incorrect");
                username.setText("");
                password.setText("");
            }
        });
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
