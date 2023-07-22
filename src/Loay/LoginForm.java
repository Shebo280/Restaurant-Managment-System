package Loay;
import com.Shebo.main.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class LoginForm extends JFrame {

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize() {
        
        ImageIcon img = new ImageIcon( getClass().getResource("/Loay/icons/password.png"));
        JLabel lbPic = new JLabel(img, SwingConstants.CENTER);
        GradientPanel picPanel = new GradientPanel();
        picPanel.setLayout(new GridLayout(0, 1, 10, 10));
        picPanel.add(lbPic);

        JLabel lbLoginForm = new JLabel("Login Form", SwingConstants.CENTER);

        lbLoginForm.setVerticalAlignment(JLabel.TOP);
        lbLoginForm.setFont(mainFont);


        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);
        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);


        JGradientbutton btnLogin = new JGradientbutton();
        btnLogin.setText("Login");
        btnLogin.setForeground(Color.white);
        btnLogin.setFont(mainFont);


        JGradientbutton btnSignUp = new JGradientbutton();
        btnSignUp.setText("Sign up");
        btnSignUp.setFont(mainFont);
        btnSignUp.setForeground(Color.white);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                ClientQueries queries= new ClientQueries();
                Client client= queries.AuthenticatedUser(email, password);


                if (client != null) {
                    if(Objects.equals(client.getId(), "1")){
                        MainAdmin admin = new MainAdmin();
                       admin.setVisible(true);
                       admin.setLocationRelativeTo(null);
                        dispose();
                    }
                    else{
                        Mainclient c= new Mainclient(client.getId());
                        c.setVisible(true);
                        c.setLocationRelativeTo(null);
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Email or Password Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.initialize();
                dispose();
                }
        });

        tfEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });

        pfPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnSignUp);


        add(picPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 700);
        setMinimumSize(new Dimension(550, 700));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}
