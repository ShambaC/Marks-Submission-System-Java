package control;
import utility.OTPUtil;
import utility.credUtil;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;


public class LoginControl {
    
    private LoginView view;
    private JPanel main;
    
    private credUtil CredsObj = new credUtil();
    // Get the OTP util
    private OTPUtil OTPobj = new OTPUtil();

    public LoginControl(LoginView view) {
        this.view = view;
        this.main = view.getMainPanel();
        initComponents();
    }
    
    private String getSHA256(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // Get the hashing function
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // Hash the string
        byte[] hash = md.digest(str.getBytes("UTF-8"));

        // Convert the hashed bytes to Hex string
        BigInteger num = new BigInteger(1, hash);
        StringBuilder hexStr = new StringBuilder(num.toString(16));

        // Pad hex string
        while(hexStr.length() < 32) {
            hexStr.insert(0, '0');
        }

        // Return the padded hashed string
        return hexStr.toString();
    }

    private void initComponents() {
//    	main = view.getMainPanel();
    	JPanel loginForm = view.getLoginPanel();
    	JPanel regForm = view.getRegPanel();
    	JPanel forgotForm = view.getForgotPanel();
    	JPanel resetPasswordForm = view.getResetPasswordPanel();
    	
        JButton loginButton = view.getLoginButton();
        JButton registerButton = view.getRegisterButton();
        JButton newRegBtn = view.getNewRegisterButton();
        JButton backLoginBtn = view.getBackLoginButton();
        JButton sendOTPBtn = view.getSendOTPButton();
        JButton resendOTPBtn = view.getResendOTPButton();
        JButton verifyOTPBtn = view.getVerifyOTPButton();
        JButton cancelBtn = view.getCancelForgotButton();
        JButton recancelBtn = view.getRecancelButton();
        JButton resetPassBtn = view.getResetPasswordButton();

        JTextField loginMailField = view.getLoginMailField();
        JPasswordField loginPassField = view.getLoginPassField();
        JTextField regEmailField = view.getRegEmailField();
        JPasswordField regPassField = view.getRegPassField();
        JPasswordField regPassConfField = view.getRegPassConfField();
        JTextField forgotEmailField = view.getForgotEmailField();
        JTextField OTPField = view.getOTPField();
        JPasswordField newPassField = view.getNewPasswordField();
        
        JLabel forgot = view.getForgotLabel();
        JLabel newPassLabel = view.getNewPassLabel();

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
                // Reset the login form fields
                loginMailField.setText("");
                loginPassField.setText("");

                // Remove the login form
                main.remove(loginForm);
                // Show the registration form
                main.add(regForm);
                // Update the UI
//                revalidate();
//                repaint();
            }
        });

        backLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the registration form
                main.remove(regForm);
                // Add the login form
                main.add(loginForm);
                // Update the UI
//                revalidate();
//                repaint();
            }
        });
        
        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // show the password reset screen
                main.remove(loginForm);
                main.add(forgotForm);
//                revalidate();
//                repaint();
            }
        });
        
        Timer timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable the resend OTP button every 30 second
                resendOTPBtn.setEnabled(true);
            }
        });
        
     // Do not loop the timer
        // The timer is only required every time the resend button is disabled
        timer.setRepeats(false);

        sendOTPBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the mail from the form field
                String mail = forgotEmailField.getText();
                
                // Check if the mail exists in the DB, only then password can be reset
                if(CredsObj.isMailExists(mail)) {
                    // Disable the email field after it has been verified to be existing
                    // This is done so that when the password reset part comes up, user cannot change the email to change password for a different user
                    forgotEmailField.setEnabled(false);
                    // Use the OTP utility class to send the OTP to the user via their mail
                    OTPobj.sendOTP(mail, false);

                    // Show confirmation dialog
                    JOptionPane.showMessageDialog(main, "Mail sent", "Info", JOptionPane.INFORMATION_MESSAGE);

                    // Enable the OTP input field
                    OTPField.setEnabled(true);
                    // Start the timer to resend OTP
                    timer.start();

                    // Remove the Send OTP and Cancel button
                    forgotForm.remove(sendOTPBtn);
                    forgotForm.remove(cancelBtn);
                    // Add the verify OTP button and a new cancel button specifically meant for later stages
                    forgotForm.add(verifyOTPBtn);
                    forgotForm.add(recancelBtn);
                    // Update the UI
//                    revalidate();
//                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(main, "Email does not exists in our database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the OTP text field and disable it
                OTPField.setText("");
                OTPField.setEnabled(false);
                // Disable the resend OTP button as well
                resendOTPBtn.setEnabled(false);
                // Reset the user email field
                forgotEmailField.setText("");
                // Remove the password reset form
                main.remove(forgotForm);
                // Add the login form window
                main.add(loginForm);
                // Update the UI
//                revalidate();
//                repaint();
            }        
           });
        
        resendOTPBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String mail = forgotEmailField.getText();
                
                // Check if the mail exists in the DB, only then password can be reset
                if(CredsObj.isMailExists(mail)) {
                    // Disable the email field after it has been verified to be existing
                    // This is done so that when the password reset part comes up, user cannot change the email to change password for a different user
                    forgotEmailField.setEnabled(false);
                    // Use the OTP utility class to send the OTP to the user via their mail
                    OTPobj.sendOTP(mail, false);

                    // Show confirmation dialog
                    JOptionPane.showMessageDialog(main, "Mail sent", "Info", JOptionPane.INFORMATION_MESSAGE);

                    // Enable the OTP input field
                    OTPField.setEnabled(true);
                    // Start the timer to resend OTP
                    timer.start();

                    // Remove the Send OTP and Cancel button
                    forgotForm.remove(sendOTPBtn);
                    forgotForm.remove(cancelBtn);
                    // Add the verify OTP button and a new cancel button specifically meant for later stages
                    forgotForm.add(verifyOTPBtn);
                    forgotForm.add(recancelBtn);
                    // Update the UI
//                    revalidate();
//                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(main, "Email does not exists in our database", "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        
        verifyOTPBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user given OTP from the form field
                String OTPfromField = OTPField.getText();
                // Get the generated OTP from the utility
                String OTPfromObj = OTPobj.getOTP();

                // Check for the input and generated OTPs' equality
                if(OTPfromField.equals(OTPfromObj)) {
                    // Stop the timer as it is not required anymore
                    timer.stop();

                    // Reset the OTP field and disable the field
                    OTPField.setText("");
                    OTPField.setEnabled(false);
                    resendOTPBtn.setEnabled(false);

                    // Remove verify buttons
                    forgotForm.remove(verifyOTPBtn);
                    forgotForm.remove(recancelBtn);

                    // Add the password reset fields
                    forgotForm.add(newPassLabel);
                    forgotForm.add(newPassField);
                    forgotForm.add(resetPassBtn);
                    forgotForm.add(recancelBtn);

                    // Update the UI
//                    revalidate();
//                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(main, "Incorrect OTP, try again!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        newRegBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the registration form data
                String email = regEmailField.getText();
                String pass = String.valueOf(regPassField.getPassword());
                String passCnf = String.valueOf(regPassConfField.getPassword());

                // Check if all the inputs were provided and valid
                if(!email.isBlank() && !pass.isBlank() && !passCnf.isBlank()) {
                    // Check if email already exists
                    if(CredsObj.isMailExists(email)) {
                        // Then show, new registration not possible
                        JOptionPane.showMessageDialog(main, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        // Check if both the password fields are consistent
                        if(pass.equals(passCnf)) {
                            try {
                                // Hash the password with SHA256
                                String hashedPass = getSHA256(passCnf);
                                // Add the user to the DB
                                CredsObj.addEntry(email, hashedPass);

                                // Show confirmation dialog
                                JOptionPane.showMessageDialog(main, "Account added!", "Info", JOptionPane.INFORMATION_MESSAGE);

                                // Reset the registration form
                                regEmailField.setText("");
                                regPassField.setText("");
                                regPassConfField.setText("");
                            }
                            catch(NoSuchAlgorithmException | UnsupportedEncodingException err) {
                                err.printStackTrace();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(main, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(main, "Please fill up all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        resetPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the mail and the new password from the form
                String mail = forgotEmailField.getText();
                String pass = String.valueOf(newPassField.getPassword());

                // Check only for validity of password as the mail field is locked
                if(!pass.isBlank()) {
                    try {
                        // hash the password with SHA256
                        String hashedPass = getSHA256(pass);
                        // Reset the password using the utility
                        CredsObj.resetPassWord(mail, hashedPass);

                        JOptionPane.showMessageDialog(main, "Password reset successfully", "Info", JOptionPane.INFORMATION_MESSAGE);

                        // Force to the login form window by clicking on the cancel button programatically
                        recancelBtn.doClick();
                    }
                    catch(NoSuchAlgorithmException | UnsupportedEncodingException err) {
                        err.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(main, "Password field cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        recancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset fields
                forgotEmailField.setEnabled(true);
                OTPField.setText("");
                OTPField.setEnabled(false);
                resendOTPBtn.setEnabled(false);
                timer.stop();
                forgotEmailField.setText("");
                newPassField.setText("");

                // Remove the components
                forgotForm.remove(newPassLabel);
                forgotForm.remove(newPassField);
                forgotForm.remove(verifyOTPBtn);
                forgotForm.remove(resendOTPBtn);
                forgotForm.remove(resetPassBtn);
                forgotForm.remove(recancelBtn);

                // Add back the previously removed buttons
                forgotForm.add(sendOTPBtn);
                forgotForm.add(cancelBtn);

                // Remove and the add the required components
                main.remove(forgotForm);
                main.add(loginForm);
                // Update UI
//                revalidate();
//                repaint();
            }
        });
        
    }
}
