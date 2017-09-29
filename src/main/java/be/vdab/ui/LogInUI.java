package be.vdab.ui;

import be.vdab.dao.CustomerDao;
import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.User;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class LogInUI extends JInternalFrame {
    private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;
    private JInternalFrame internal = new JInternalFrame();
    private EshopSwingApp shop = new EshopSwingApp();
    private boolean loginSuccess = true;
    private Customer customer;

    public LogInUI() {
        initComponents();
        layoutComponents();
        initListeners();
    }

    private void initComponents()  {
        username = new JTextField(20);
        password = new JPasswordField(20);
        usernameLabel = new JLabel("username");
        passwordLabel = new JLabel("password");
        btnLogin = new JButton("login");
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.add(usernameLabel, BorderLayout.CENTER);
        panel.add(username, BorderLayout.CENTER);
        panel.add(passwordLabel, BorderLayout.CENTER);
        panel.add(password, BorderLayout.CENTER);
        panel.add(btnLogin, BorderLayout.CENTER);
        internal.add(panel, BorderLayout.CENTER);
        //setBounds(50, 50, 100, 100);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setTitle("LogIn");
        setVisible(true);
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
                shop.getDesktop().removeAll();
                setLoginSuccess(true);
            } else {
               JOptionPane.showMessageDialog( null, "username and/or password incorrect");
               username.setText("");
               password.setText("");
               new LogInUI();
            }
        });
    }

    protected Customer getCustomer() {
        return customer;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }
}

/*public class Main {
    public static void main(String args[]) {
        JFrame f = new JFrame("Sample");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane desktop = new JDesktopPane();
        desktop.setOpaque(false);
        desktop.add(new SelfInternalFrame("1"), JLayeredPane.POPUP_LAYER);
        f.add(desktop, BorderLayout.CENTER);
        f.setSize(300, 200);
        f.setVisible(true);
    }

}

class SelfInternalFrame extends JInternalFrame {
    public SelfInternalFrame(String s) {
        getContentPane().add(new JLabel(s), BorderLayout.CENTER);
        setBounds(50, 50, 100, 100);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setTitle(s);
        setVisible(true);
    }
}*/
