package Loay;
import com.Shebo.main.Mainclient;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SignUpForm extends JFrame {

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize() {
       
        ImageIcon img = new ImageIcon( getClass().getResource("/Loay/icons/register.png"));
        JLabel lbPic = new JLabel(img, SwingConstants.CENTER);

        GradientPanel picPanel = new GradientPanel();
        picPanel.setLayout(new GridLayout(0, 1, 10, 10));
        picPanel.add(lbPic);

        JLabel lbLoginForm = new JLabel("Sign Up Form", SwingConstants.CENTER);

        lbLoginForm.setVerticalAlignment(JLabel.TOP);
        lbLoginForm.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbFirstName = new JLabel("First Name");
        lbFirstName.setFont(mainFont);

        JTextField tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);

        JTextField tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        JLabel lbPhone = new JLabel("Phone Number");
        lbPhone.setFont(mainFont);

        JLabel lbError = new JLabel("");
        lbError.setFont(mainFont);
        lbError.setForeground(Color.red);

        JTextField tfPhone = new JTextField();
        tfPhone.setFont(mainFont);


        tfLastName.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);


        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPhone);
        formPanel.add(tfPhone);
        formPanel.add(lbError);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        JGradientbutton btnSignUp = new JGradientbutton();
        btnSignUp.setText("Sign Up");
        btnSignUp.setForeground(Color.white);
        btnSignUp.setFont(mainFont);


        JGradientbutton btnLogin = new JGradientbutton();
        btnLogin.setText("Login");
        btnLogin.setForeground(Color.white);
        btnLogin.setFont(mainFont);


        tfPhone.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    tfPhone.setEditable(true);
                    lbError.setText("");
                }
                else {
                    tfPhone.setEditable(false);
                    lbError.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        pfPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSignUp.doClick();
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fName = tfFirstName.getText();
                String lName = tfLastName.getText();
                String email = tfEmail.getText();
                String pNum = tfPhone.getText();
                String password = String.valueOf(pfPassword.getPassword());

                ClientQueries queries= new ClientQueries();
                Client client= queries.insertNewClient(fName, lName, email, pNum, password);

                if (client != null) {
                    LoginForm login = new LoginForm();
                    login.initialize();
                    dispose();
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                loginForm.initialize();
                dispose();
                dispose();
            }

        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnSignUp);
        buttonsPanel.add(btnLogin);


        add(picPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Signup Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 700);
        setMinimumSize(new Dimension(550, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
