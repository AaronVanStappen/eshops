package be.vdab.ui;

import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.User;
import com.sun.org.apache.bcel.internal.generic.Select;

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

    protected void initListeners(JDesktopPane desktop) {
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
                this.frame.setVisible(false);
                this.frame.dispose();
                this.frame.remove(0);
                desktop.add(new SelectShopUI(desktop).getFrame());
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

    protected Customer getCustomer() {
        return customer;
    }
}
