package control;

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

import utility.OTPUtil;
import utility.credUtil;
import view.MarksMgmt;

public class LoginControl {
    /**
     * Method to hash a string using the SHA-256 algorithm
     * @param str String to hash
     * @return Hashed string in Hex format
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getSHA256(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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

    public loginButtonAL loginButtonALFactory(JTextField loginMailField, JPasswordField loginPassField, JPanel main) {
        return new loginButtonAL(loginMailField, loginPassField, main);
    }
    public registerButtonAL registerButtonALFactory(JTextField loginMailField, JPasswordField loginPassField, JPanel regForm, JPanel loginForm, JPanel main) {
        return new registerButtonAL(loginMailField, loginPassField, regForm, loginForm, main);
    }
    public backLoginBtnAL backLoginBtnALFactory(JPanel regForm, JPanel loginForm, JPanel main) {
        return new backLoginBtnAL(regForm, loginForm, main);
    }
    public forgotML forgotMLFactory(JPanel loginForm, JPanel forgotForm, JPanel main) {
        return new forgotML(loginForm, forgotForm, main);
    }
    public sendOTPBtnAL sendOTPBtnALFactory(OTPUtil OTPobj, JTextField forgotEmailField, JTextField OTPField, JPanel main, JPanel forgotForm, JButton sendOTPBtn, JButton cancelBtn, JButton verifyOTPBtn, JButton recancelBtn, Timer timer) {
        return new sendOTPBtnAL(OTPobj, forgotEmailField, OTPField, main, forgotForm, sendOTPBtn, cancelBtn, verifyOTPBtn, recancelBtn, timer);
    }
    public cancelBtnAL cancelBtnALFactory(JPanel main, JTextField OTPField, JButton resendOTPBtn, JTextField forgotEmailField, JPanel forgotForm, JPanel loginForm) {
        return new cancelBtnAL(main, OTPField, resendOTPBtn, forgotEmailField, forgotForm, loginForm);
    }
    public resendOTPBtnAL resendOTPBtnALFactory(OTPUtil OTPobj, JPanel main, JTextField forgotEmailField, JTextField OTPField, JPanel forgotForm, JButton sendOTPBtn, JButton cancelBtn, JButton verifyOTPBtn, JButton recancelBtn, Timer timer) {
        return new resendOTPBtnAL(OTPobj, main, forgotEmailField, OTPField, forgotForm, sendOTPBtn, cancelBtn, verifyOTPBtn, recancelBtn, timer);
    }
    public verifyOTPBtnAL verifyOTPBtnALFactory(OTPUtil OTPobj, JTextField OTPField, JButton resendOTPBtn, JButton verifyOTPBtn, JButton recancelBtn, JPanel forgotForm, JLabel newPassLabel, JPasswordField newPassField, JButton resetPassBtn, JPanel main, Timer timer) {
        return new verifyOTPBtnAL(OTPobj, OTPField, resendOTPBtn, verifyOTPBtn, recancelBtn, forgotForm, newPassLabel, newPassField, resetPassBtn, main, timer);
    }
    public newRegBtnAL newRegBtnALFactory(JPanel main, JTextField regEmailField, JPasswordField regPassField, JPasswordField regPassConfField) {
        return new newRegBtnAL(main, regEmailField, regPassField, regPassConfField);
    }
    public resetPassBtnAL resetPassBtnALFactory(JTextField forgotEmailField, JPasswordField newPassField, JPanel main, JButton recancelBtn) {
        return new resetPassBtnAL(forgotEmailField, newPassField, main, recancelBtn);
    }
    public recancelBtnAL recancelBtnALFactory(JTextField forgotEmailField, JTextField OTPField, JButton resendOTPBtn, JPasswordField newPassField, JPanel main, JPanel forgotForm, JLabel newPassLabel, JButton verifyOTPBtn, JButton resetPassBtn, JButton recancelBtn, JButton sendOTPBtn, JButton cancelBtn, Timer timer, JPanel loginForm) {
        return new recancelBtnAL(forgotEmailField, OTPField, resendOTPBtn, newPassField, main, forgotForm, newPassLabel, verifyOTPBtn, resetPassBtn, recancelBtn, sendOTPBtn, cancelBtn, timer, loginForm);
    }
    
}

class loginButtonAL implements ActionListener {
    JTextField loginMailField;
    JPasswordField loginPassField;
    JPanel main;

    public loginButtonAL(JTextField loginMailField, JPasswordField loginPassField, JPanel main) {
        this.loginMailField = loginMailField;
        this.loginPassField = loginPassField;
        this.main = main;
    }

    private credUtil CredsObj = new credUtil();

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the mail and password from the form fields
        String mail = loginMailField.getText();
	    String pass = String.valueOf(loginPassField.getPassword());

        // If the inputs are valid
        if(!mail.isBlank() && !pass.isBlank()) {
            // Check the database for the mail to see if it exists
            if(CredsObj.isMailExists(mail)) {
                try {
                    // hash the password with SHA256
                    String hashedPassword = LoginControl.getSHA256(pass);
                    // Check if the email provided matches the corresponding password from the database
                    if(CredsObj.isMailPassMatch(mail, hashedPassword)) {
                        // If it matches, launch the Employee Management UI
                        MarksMgmt mm = new MarksMgmt();
                        mm.setVisible(true);
//	                            mm.setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(main, "Mail Password do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(NoSuchAlgorithmException | UnsupportedEncodingException err) {
                    err.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(main, "Email does not exists in our database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(main, "Please fill up all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class registerButtonAL implements ActionListener {
    JTextField loginMailField;
    JPasswordField loginPassField;
    JPanel regForm;
    JPanel loginForm;
    JPanel main;

    public registerButtonAL(JTextField loginMailField, JPasswordField loginPassField, JPanel regForm, JPanel loginForm, JPanel main) {
        this.loginMailField = loginMailField;
        this.loginPassField = loginPassField;
        this.regForm = regForm;
        this.loginForm = loginForm;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset the login form fields
        loginMailField.setText("");
        loginPassField.setText("");

        // Remove the login form
        main.remove(loginForm);
        // Show the registration form
        main.add(regForm);
        main.revalidate();
        main.repaint();
    }
}

class backLoginBtnAL implements ActionListener {
    JPanel regForm;
    JPanel loginForm;
    JPanel main;

    public backLoginBtnAL(JPanel regForm, JPanel loginForm, JPanel main) {
        this.regForm = regForm;
        this.loginForm = loginForm;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove the registration form
        main.remove(regForm);
        // Add the login form
        main.add(loginForm);
        // Update the UI
        main.revalidate();
        main.repaint();
    }
}

class forgotML extends MouseAdapter {
    JPanel loginForm;
    JPanel forgotForm;
    JPanel main;

    public forgotML(JPanel loginForm, JPanel forgotForm, JPanel main) {
        this.loginForm = loginForm;
        this.forgotForm = forgotForm;
        this.main = main;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // show the password reset screen
        main.remove(loginForm);
        main.add(forgotForm);
        main.revalidate();
        main.repaint();
    }
}

class sendOTPBtnAL implements ActionListener {
    private credUtil CredsObj = new credUtil();

    OTPUtil OTPobj;
    
    JTextField forgotEmailField;
    JTextField OTPField;
    JPanel main;
    JPanel forgotForm;
    JButton sendOTPBtn;
    JButton cancelBtn;
    JButton verifyOTPBtn;
    JButton recancelBtn;

    Timer timer;

    public sendOTPBtnAL(OTPUtil OTPobj, JTextField forgotEmailField, JTextField OTPField, JPanel main, JPanel forgotForm, JButton sendOTPBtn, JButton cancelBtn, JButton verifyOTPBtn, JButton recancelBtn, Timer timer) {
        this.OTPobj = OTPobj;
        this.forgotEmailField = forgotEmailField;
        this.OTPField = OTPField;
        this.main = main;
        this.forgotForm = forgotForm;
        this.sendOTPBtn = sendOTPBtn;
        this.cancelBtn = cancelBtn;
        this.verifyOTPBtn = verifyOTPBtn;
        this.recancelBtn = recancelBtn;
        this.timer = timer;
    }

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
            main.revalidate();
            main.repaint();
        }
        else {
            JOptionPane.showMessageDialog(main, "Email does not exists in our database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class cancelBtnAL implements ActionListener {
    JPanel main;
    JTextField OTPField;
    JButton resendOTPBtn;
    JTextField forgotEmailField;
    JPanel forgotForm;
    JPanel loginForm;

    public cancelBtnAL(JPanel main, JTextField OTPField, JButton resendOTPBtn, JTextField forgotEmailField, JPanel forgotForm, JPanel loginForm) {
        this.main = main;
        this.OTPField = OTPField;
        this.resendOTPBtn = resendOTPBtn;
        this.forgotEmailField = forgotEmailField;
        this.forgotForm = forgotForm;
        this.loginForm = loginForm;
    }

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
        main.revalidate();
        main.repaint();
    }
}

class resendOTPBtnAL implements ActionListener {
    private credUtil CredsObj = new credUtil();

    OTPUtil OTPobj;

    JPanel main;
    JTextField forgotEmailField;
    JTextField OTPField;
    JPanel forgotForm;
    JButton sendOTPBtn;
    JButton cancelBtn;
    JButton verifyOTPBtn;
    JButton recancelBtn;

    Timer timer;

    public resendOTPBtnAL(OTPUtil OTPobj, JPanel main, JTextField forgotEmailField, JTextField OTPField, JPanel forgotForm, JButton sendOTPBtn, JButton cancelBtn, JButton verifyOTPBtn, JButton recancelBtn, Timer timer) {
        this.OTPobj = OTPobj;
        this.main = main;
        this.forgotEmailField = forgotEmailField;
        this.OTPField = OTPField;
        this.forgotForm = forgotForm;
        this.sendOTPBtn = sendOTPBtn;
        this.cancelBtn = cancelBtn;
        this.verifyOTPBtn = verifyOTPBtn;
        this.recancelBtn = recancelBtn;

        this.timer = timer;
    }

    @Override
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
            main.revalidate();
            main.repaint();
        }
        else {
            JOptionPane.showMessageDialog(main, "Email does not exists in our database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class verifyOTPBtnAL implements ActionListener {
    OTPUtil OTPobj;
    JTextField OTPField;
    JButton resendOTPBtn;
    JButton verifyOTPBtn;
    JButton recancelBtn;
    JPanel forgotForm;
    JLabel newPassLabel;
    JPasswordField newPassField;
    JButton resetPassBtn;
    JPanel main;
    Timer timer;

    public verifyOTPBtnAL(OTPUtil OTPobj, JTextField OTPField, JButton resendOTPBtn, JButton verifyOTPBtn, JButton recancelBtn, JPanel forgotForm, JLabel newPassLabel, JPasswordField newPassField, JButton resetPassBtn, JPanel main, Timer timer) {
        this.OTPobj = OTPobj;
        this.OTPField = OTPField;
        this.resendOTPBtn = resendOTPBtn;
        this.verifyOTPBtn = verifyOTPBtn;
        this.recancelBtn = recancelBtn;
        this.forgotForm = forgotForm;
        this.newPassLabel = newPassLabel;
        this.newPassField = newPassField;
        this.resetPassBtn = resetPassBtn;
        this.main = main;
        this.timer = timer;
    }

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
            main.revalidate();
            main.repaint();
        }
        else {
            JOptionPane.showMessageDialog(main, "Incorrect OTP, try again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class newRegBtnAL implements ActionListener {
    private credUtil CredsObj = new credUtil();

    JPanel main;
    JTextField regEmailField;
    JPasswordField regPassField;
    JPasswordField regPassConfField;

    public newRegBtnAL(JPanel main, JTextField regEmailField, JPasswordField regPassField, JPasswordField regPassConfField) {
        this.main = main;
        this.regEmailField = regEmailField;
        this.regPassField = regPassField;
        this.regPassConfField = regPassConfField;
    }

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
                        String hashedPass = LoginControl.getSHA256(passCnf);
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
}

class resetPassBtnAL implements ActionListener {
    private credUtil CredsObj = new credUtil();

    JTextField forgotEmailField;
    JPasswordField newPassField;
    JPanel main;
    JButton recancelBtn;

    public resetPassBtnAL(JTextField forgotEmailField, JPasswordField newPassField, JPanel main, JButton recancelBtn) {
        this.forgotEmailField = forgotEmailField;
        this.newPassField = newPassField;
        this.main = main;
        this.recancelBtn = recancelBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the mail and the new password from the form
        String mail = forgotEmailField.getText();
        String pass = String.valueOf(newPassField.getPassword());

        // Check only for validity of password as the mail field is locked
        if(!pass.isBlank()) {
            try {
                // hash the password with SHA256
                String hashedPass = LoginControl.getSHA256(pass);
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
}

class recancelBtnAL implements ActionListener {
    JTextField forgotEmailField;
    JTextField OTPField;
    JButton resendOTPBtn;
    JPasswordField newPassField;
    JPanel main;
    JPanel forgotForm;
    JLabel newPassLabel;
    JButton verifyOTPBtn;
    JButton resetPassBtn;
    JButton recancelBtn;
    JButton sendOTPBtn;
    JButton cancelBtn;
    JPanel loginForm;

    Timer timer;

    public recancelBtnAL(JTextField forgotEmailField, JTextField OTPField, JButton resendOTPBtn, JPasswordField newPassField, JPanel main, JPanel forgotForm, JLabel newPassLabel, JButton verifyOTPBtn, JButton resetPassBtn, JButton recancelBtn, JButton sendOTPBtn, JButton cancelBtn, Timer timer, JPanel loginForm) {
        this.forgotEmailField = forgotEmailField;
        this.OTPField = OTPField;
        this.resendOTPBtn = resendOTPBtn;
        this.newPassField = newPassField;
        this.main = main;
        this.forgotForm = forgotForm;
        this.newPassLabel = newPassLabel;
        this.verifyOTPBtn = verifyOTPBtn;
        this.resetPassBtn = resetPassBtn;
        this.recancelBtn = recancelBtn;
        this.sendOTPBtn = sendOTPBtn;
        this.cancelBtn = cancelBtn;
        this.loginForm = loginForm;

        this.timer = timer;
    }

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
        main.revalidate();
        main.repaint();
    }
}