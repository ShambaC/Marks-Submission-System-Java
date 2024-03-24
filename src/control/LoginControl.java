package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.LoginView;

public class LoginControl {
    
    private LoginView view;

    public LoginControl(LoginView view) {
        this.view = view;
        initComponents();
    }

    private void initComponents() {
        JButton loginButton = view.getLoginButton();
        JButton registerButton = view.getRegisterButton();
        JButton backLoginBtn = view.getBackLoginButton();
        JButton sendOTPBtn = view.getSendOTPButton();
        JButton cancelBtn = view.getCancelForgotButton();
        JButton resetPassBtn = view.getResetPasswordButton();

        JTextField loginMailField = view.getLoginMailField();
        JPasswordField loginPassField = view.getLoginPassField();
        JTextField regEmailField = view.getRegEmailField();
        JPasswordField regPassField = view.getRegPassField();
        JPasswordField regPassConfField = view.getRegPassConfField();
        JTextField forgotEmailField = view.getForgotEmailField();
        JTextField OTPField = view.getOTPField();
        JTextField newPassField = view.getNewPasswordField();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginMailField.getText();
                String password = new String(loginPassField.getPassword());
                // Perform login logic here
                // For example, you can check credentials, validate, etc.
                JOptionPane.showMessageDialog(view, "Perform login logic for: \nEmail: " + email + "\nPassword: " + password);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get registration form data
                String email = regEmailField.getText();
                String pass = new String(regPassField.getPassword());
                String passCnf = new String(regPassConfField.getPassword());
                
                // Perform registration logic here
                // For example, validate inputs, check for existing accounts, etc.
                JOptionPane.showMessageDialog(view, "Perform registration logic for: \nEmail: " + email + "\nPassword: " + pass + "\nConfirm Password: " + passCnf);
            }
        });

        backLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back to login form action
                JOptionPane.showMessageDialog(view, "Back to Login Form");
            }
        });

        sendOTPBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = forgotEmailField.getText();
                // Perform action to send OTP to the provided email
                JOptionPane.showMessageDialog(view, "Sending OTP to: " + email);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancel action for forgot password
                JOptionPane.showMessageDialog(view, "Cancel Forgot Password");
            }
        });

        resetPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String otp = OTPField.getText();
                String newPassword = new String(newPassField.getPassword());
                // Perform action to reset password with OTP and new password
                JOptionPane.showMessageDialog(view, "Reset Password with OTP: " + otp + "\nNew Password: " + newPassword);
            }
        });
    }
}
