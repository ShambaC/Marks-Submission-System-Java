package view;

import utility.OTPUtil;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import control.LoginControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * This class is the UI for the login form seen upon application startup
 */
public class LoginView extends JFrame {
	
    Color bgColor = new Color(242, 245, 255);
    Color panelColor = new Color(206, 215, 246);
    Color fontColor = new Color(8, 17, 54);
    Color placeholderColor = new Color(195, 195, 195);
    
    private JPanel main;
    private JPanel loginForm;
    private JPanel regForm;
    private JPanel forgotForm;
    private JLabel forgot;
    private JLabel newPassLabel;
    private JPanel resetPasswordForm;
    
    private JButton loginButton;
    private JButton registerButton;
    private JButton newRegBtn;
    private JButton backLoginBtn;
    private JButton sendOTPBtn;
    private JButton resendOTPBtn;
    private JButton cancelBtn;
    private JButton verifyOTPBtn;
    private JButton resetPassBtn;
    private JButton recancelBtn;
    
    private JTextField loginMailField;
    private JTextField regMailField;
    private JTextField forgotMailField;
    private JTextField OTPField;
    
    private JPasswordField loginPassField;
    private JPasswordField regPassField;
    private JPasswordField regPassConfField;
    private JPasswordField newPassField;
    
    /**
     * Initializes the window for the application
     */
    public LoginView() {
    	setTitle("Admin Login");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();
    }

    /**
     * Initializes the UI components within the window
     */
    private void init(){
    	GridBagLayout gb = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	main = new JPanel(gb);
    	
    	//Login Form
    	loginForm = new JPanel(new GridLayout(4,2,25,25));
    	loginForm.setPreferredSize(new Dimension(550,350));
    	loginForm.setBackground(panelColor);
    	
    	
        TitledBorder border = new TitledBorder(new EmptyBorder(60,25,25,25), "ADMIN LOG-IN");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleFont(new Font("Arial Black",1,20));
        border.setTitleColor(fontColor);
        
        JLabel userLabel = new JLabel("Email: ");
        userLabel.setFont(new Font("Arial Black",1,17));
        userLabel.setForeground(fontColor);
        loginMailField = new JTextField();
        loginMailField.setFont(new Font("Arial",1,15));
        loginMailField.setForeground(fontColor);
        loginMailField.setBackground(bgColor);
        
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(new Font("Arial Black",1,17));
        passLabel.setForeground(fontColor);
        loginPassField = new JPasswordField();
        loginPassField.setFont(new Font("Arial",1,15));
        loginPassField.setForeground(fontColor);
        loginPassField.setBackground(bgColor);
        
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial Black",1,17));
        loginButton.setForeground(fontColor);
        loginButton.setBackground(bgColor);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial Black",1,17));
        registerButton.setForeground(fontColor);
        registerButton.setBackground(bgColor);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        forgot = new JLabel("<html><u>Forgot Password<u><html>", SwingConstants.CENTER);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.PAGE_END;

        forgot.setForeground(Color.BLUE);
        forgot.setFont(new Font("Arial",1,15));
        forgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        loginForm.setBorder(border);
        loginForm.add(userLabel);
        loginForm.add(loginMailField);
        loginForm.add(passLabel);
        loginForm.add(loginPassField);
        loginForm.add(loginButton);
        loginForm.add(registerButton);
        loginForm.add(forgot);
        
        main.add(loginForm);
        main.setBackground(bgColor);
        add(main);
        
        
        //Registration Form
        regForm = new JPanel(new GridLayout(4,2,25,25));
        regForm.setPreferredSize(new Dimension(500,300));
        regForm.setBackground(panelColor);
    	
        TitledBorder regBorder = new TitledBorder(new EmptyBorder(60,25,25,25), "ADMIN REGISTRATION");
        regBorder.setTitleJustification(TitledBorder.CENTER);
        regBorder.setTitlePosition(TitledBorder.TOP);
        regBorder.setTitleFont(new Font("Arial Black",1,20));
        regBorder.setTitleColor(fontColor);

        JLabel regUserLabel = new JLabel("Email: ");
        regUserLabel.setFont(new Font("Arial Black",1,17));
        regUserLabel.setForeground(fontColor);
        regMailField = new JTextField();
        regMailField.setFont(new Font("Arial",1,15));
        regMailField.setForeground(fontColor);
        regMailField.setBackground(bgColor);
        
        JLabel regPassLabel = new JLabel("Password: ");
        regPassLabel.setFont(new Font("Arial Black",1,17));
        regPassLabel.setForeground(fontColor);
        regPassField = new JPasswordField();
        regPassField.setFont(new Font("Arial",1,15));
        regPassField.setForeground(fontColor);
        regPassField.setBackground(bgColor);
        
        JLabel regPassConfLabel = new JLabel("Confirm Password: ");
        regPassConfLabel.setFont(new Font("Arial Black",1,17));
        regPassConfLabel.setForeground(fontColor);
        regPassConfField = new JPasswordField();
        regPassConfField.setFont(new Font("Arial",1,15));
        regPassConfField.setForeground(fontColor);
        regPassConfField.setBackground(bgColor);
        
        newRegBtn = new JButton("Register");
        newRegBtn.setFont(new Font("Arial Black",1,17));
        newRegBtn.setForeground(fontColor);
        newRegBtn.setBackground(bgColor);
        newRegBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backLoginBtn = new JButton("Login");
        backLoginBtn.setFont(new Font("Arial Black",1,17));
        backLoginBtn.setForeground(fontColor);
        backLoginBtn.setBackground(bgColor);
        backLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        regForm.setBorder(regBorder);
        regForm.add(regUserLabel);
        regForm.add(regMailField);
        regForm.add(regPassLabel);
        regForm.add(regPassField);
        regForm.add(regPassConfLabel);
        regForm.add(regPassConfField);
        regForm.add(newRegBtn);
        regForm.add(backLoginBtn);

        //Forgot password page
        forgotForm = new JPanel(new GridLayout(4, 2, 30, 15));
        forgotForm.setPreferredSize(new Dimension(600, 250));
        forgotForm.setBackground(panelColor);

        TitledBorder forgotBorder = new TitledBorder(new EmptyBorder(60,25,25,25), "Forgot Password");
        forgotBorder.setTitleJustification(TitledBorder.CENTER);
        forgotBorder.setTitlePosition(TitledBorder.TOP);
        forgotBorder.setTitleFont(new Font("Arial Black",1,20));
        forgotBorder.setTitleColor(fontColor);

        JLabel forgotEmailLabel = new JLabel("Email: ");
        forgotEmailLabel.setFont(new Font("Arial Black",1,17));
        forgotEmailLabel.setForeground(fontColor);
        
        forgotMailField = new JTextField();
        forgotMailField.setFont(new Font("Arial",1,15));
        forgotMailField.setForeground(fontColor);
        forgotMailField.setBackground(bgColor);
        
        JLabel OTPLabel = new JLabel("OTP: ");
        OTPLabel.setFont(new Font("Arial Black",1,17));
        OTPLabel.setForeground(fontColor);
        
        OTPField = new JTextField();
        OTPField.setFont(new Font("Arial",1,15));
        OTPField.setForeground(fontColor);
        OTPField.setBackground(bgColor);
        OTPField.setEnabled(false);
        
        resendOTPBtn = new JButton("Resend OTP");
        resendOTPBtn.setFont(new Font("Arial Black",1,12));
        resendOTPBtn.setForeground(fontColor);
        resendOTPBtn.setBackground(bgColor);
        resendOTPBtn.setEnabled(false);
        resendOTPBtn.setToolTipText("You can resend OTP once every 30 seconds");
        resendOTPBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JPanel OTPFieldGroup = new JPanel(new GridLayout(1, 2, 10, 0));
        
        sendOTPBtn = new JButton("Send OTP");
        sendOTPBtn.setFont(new Font("Arial Black",1,17));
        sendOTPBtn.setForeground(fontColor);
        sendOTPBtn.setBackground(bgColor);
        sendOTPBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Arial Black",1,17));
        cancelBtn.setForeground(fontColor);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // After OTP sent components
        verifyOTPBtn = new JButton("Verify");
        verifyOTPBtn.setFont(new Font("Arial Black",1,17));
        verifyOTPBtn.setForeground(fontColor);
        verifyOTPBtn.setBackground(bgColor);
        verifyOTPBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        OTPFieldGroup.add(OTPField);
        OTPFieldGroup.add(resendOTPBtn);

        forgotForm.setBorder(forgotBorder);
        forgotForm.add(forgotEmailLabel);
        forgotForm.add(forgotMailField);
        forgotForm.add(OTPLabel);
        forgotForm.add(OTPFieldGroup);
//        forgotForm.add(resendOtpBtn);
        forgotForm.add(sendOTPBtn);
//        forgotForm.add(verifyOTPBtn);
        forgotForm.add(cancelBtn);
        
        // After OTP sent components
        resetPasswordForm = new JPanel(new GridLayout(2,2,30,15));
        resetPasswordForm.setPreferredSize(new Dimension(600, 250));
        resetPasswordForm.setBackground(panelColor);

        TitledBorder resetBorder = new TitledBorder(new EmptyBorder(60,25,25,25), "Reset Password");
        resetBorder.setTitleJustification(TitledBorder.CENTER);
        resetBorder.setTitlePosition(TitledBorder.TOP);
        resetBorder.setTitleFont(new Font("Arial Black",1,20));
        resetBorder.setTitleColor(fontColor);
        
        recancelBtn = new JButton("Cancel");
        recancelBtn.setFont(new Font("Arial Black",1,17));
        recancelBtn.setForeground(fontColor);
        recancelBtn.setBackground(bgColor);
        recancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // After OTP verification components
        newPassLabel = new JLabel("Set New Password: ");
        newPassLabel.setFont(new Font("Arial Black",1,17));
        newPassLabel.setForeground(fontColor);
        
        newPassField = new JPasswordField();
        newPassField.setFont(new Font("Arial",1,15));
        newPassField.setForeground(fontColor);
        newPassField.setBackground(bgColor);
        
        resetPassBtn = new JButton("Reset Password");
        resetPassBtn.setFont(new Font("Arial Black",1,17));
        resetPassBtn.setForeground(fontColor);
        resetPassBtn.setBackground(bgColor);
        resetPassBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        resetPasswordForm.add(newPassLabel);
        resetPasswordForm.add(newPassField);
        resetPasswordForm.add(resetPassBtn);
        resetPasswordForm.add(recancelBtn);

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

        OTPUtil OTPobj = new OTPUtil();

        LoginControl lCon = new LoginControl();

        // Add action listeners from control
        loginButton.addActionListener(lCon.loginButtonALFactory(loginMailField, loginPassField, main, this));
        registerButton.addActionListener(lCon.registerButtonALFactory(loginMailField, loginPassField, regForm, loginForm, main));
        backLoginBtn.addActionListener(lCon.backLoginBtnALFactory(regForm, loginForm, main));
        forgot.addMouseListener(lCon.forgotMLFactory(loginForm, forgotForm, main));
        sendOTPBtn.addActionListener(lCon.sendOTPBtnALFactory(OTPobj, forgotMailField, OTPField, main, forgotForm, sendOTPBtn, cancelBtn, verifyOTPBtn, recancelBtn, timer));
        cancelBtn.addActionListener(lCon.cancelBtnALFactory(main, OTPField, resendOTPBtn, forgotMailField, forgotForm, loginForm));
        resendOTPBtn.addActionListener(lCon.resendOTPBtnALFactory(OTPobj, main, forgotMailField, OTPField, forgotForm, sendOTPBtn, cancelBtn, verifyOTPBtn, recancelBtn, resendOTPBtn, timer));
        verifyOTPBtn.addActionListener(lCon.verifyOTPBtnALFactory(OTPobj, OTPField, resendOTPBtn, verifyOTPBtn, recancelBtn, forgotForm, newPassLabel, newPassField, resetPassBtn, main, timer));
        newRegBtn.addActionListener(lCon.newRegBtnALFactory(main, regMailField, regPassField, regPassConfField));
        resetPassBtn.addActionListener(lCon.resetPassBtnALFactory(forgotMailField, newPassField, main, recancelBtn));
        recancelBtn.addActionListener(lCon.recancelBtnALFactory(forgotMailField, OTPField, resendOTPBtn, newPassField, main, forgotForm, newPassLabel, verifyOTPBtn, resetPassBtn, recancelBtn, sendOTPBtn, cancelBtn, timer, loginForm));
        
    }
    
    public JPanel getMainPanel() {
    	return main;
    }
    
    public JPanel getLoginPanel() {
    	return loginForm;
    }
    
    public JPanel getRegPanel() {
    	return regForm;
    }
    
    public JPanel getForgotPanel() {
    	return forgotForm;
    }
    
    public JPanel getResetPasswordPanel() {
    	return resetPasswordForm;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
    
    public JLabel getForgotLabel() {
        return forgot;
    }
    
    public JLabel getNewPassLabel() {
        return newPassLabel;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
    
    public JButton getNewRegisterButton() {
        return newRegBtn;
    }

    public JButton getBackLoginButton() {
        return backLoginBtn;
    }

    public JButton getSendOTPButton() {
        return sendOTPBtn;
    }
    
    public JButton getResendOTPButton() {
        return resendOTPBtn;
    }
    
    public JButton getVerifyOTPButton() {
        return verifyOTPBtn;
    }

    public JButton getCancelForgotButton() {
        return cancelBtn;
    }

    public JButton getResetPasswordButton() {
        return resetPassBtn;
    }
    
    public JButton getRecancelButton() {
        return recancelBtn;
    }

    public JTextField getLoginMailField() {
        return loginMailField;
    }

    public JPasswordField getLoginPassField() {
        return loginPassField;
    }

    public JTextField getRegEmailField() {
        return regMailField;
    }
    
    public JPasswordField getRegPassField() {
        return regPassField;
    }

    public JPasswordField getRegPassConfField() {
        return regPassConfField;
    }

    public JTextField getForgotEmailField() {
        return forgotMailField;
    }

    public JTextField getOTPField() {
        return OTPField;
    }

    public JPasswordField getNewPasswordField() {
        return newPassField;
    }
    
    public static void main(String[] args) {
    	LoginView view = new LoginView();
        view.setVisible(true);
//        new LoginControl(view);
    }
}

