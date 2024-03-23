import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class View extends JFrame {
	
	Color bgColor = new Color(242, 245, 255);
    Color panelColor = new Color(206, 215, 246);
    Color fontColor = new Color(8, 17, 54);
    Color placeholderColor = new Color(195, 195, 195);
    
    public View() {
    	setTitle("Admin Login");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();
    }

    private void init(){
    	GridBagLayout gb = new GridBagLayout();
    	GridBagConstraints gbc = new GridBagConstraints();
    	JPanel main = new JPanel(gb);
    	
    	//Login Form
    	JPanel loginForm = new JPanel(new GridLayout(4,2,25,25));
    	loginForm.setPreferredSize(new Dimension(550,350));
    	loginForm.setBackground(panelColor);
    	
    	
//    	ImageIcon image = null;
//    	BufferedImage bufferedImage = null;
//    	try {
//    	    URL url = new URL("https://crushlogo.com/public/uploads/thumbnail/university-of-calcutta-logo-11684849442zuxwzxmtaj.png");
//    	    bufferedImage = ImageIO.read(url);
//    	} catch (IOException e1) {
//    	    e1.printStackTrace();
//    	}
//    	 try {
//    		 URL imageUrl = new URL("https://crushlogo.com/public/uploads/thumbnail/university-of-calcutta-logo-11684849442zuxwzxmtaj.png");
//    		 BufferedImage originalImage = ImageIO.read(imageUrl);
//             int newWidth = 200; // New width for the image
//             int newHeight = (int) ((double) originalImage.getHeight() * newWidth / originalImage.getWidth());
//             Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//	         JLabel imageLabel = new JLabel("resizedImage");
//			// Add the image at the top center with column span 2
//	         gbc.gridx = 0; // Start at the first column
//	         gbc.gridy = 0; // First row
//	         gbc.gridwidth = 2; // Column span of 2
//	         gbc.anchor = GridBagConstraints.PAGE_START; // Align to the top
//	         loginForm.add(imageLabel, gbc);
//    	 }
//	     catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    	 
    	
    	
        TitledBorder border = new TitledBorder(new EmptyBorder(60,25,25,25), "ADMIN LOG-IN");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleFont(new Font("Arial Black",1,20));
        border.setTitleColor(fontColor);
        
        JLabel userLabel = new JLabel("Email: ");
        userLabel.setFont(new Font("Arial Black",1,17));
        userLabel.setForeground(fontColor);
        JTextField loginMailField = new JTextField();
        loginMailField.setFont(new Font("Arial",1,15));
        loginMailField.setForeground(fontColor);
        loginMailField.setBackground(bgColor);
        
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(new Font("Arial Black",1,17));
        passLabel.setForeground(fontColor);
        JPasswordField loginPassField = new JPasswordField();
        loginPassField.setFont(new Font("Arial",1,15));
        loginPassField.setForeground(fontColor);
        loginPassField.setBackground(bgColor);
        
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial Black",1,17));
        loginButton.setForeground(fontColor);
        loginButton.setBackground(bgColor);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial Black",1,17));
        registerButton.setForeground(fontColor);
        registerButton.setBackground(bgColor);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
//        JPanel forgotPanel = new JPanel(new GridLayout(1,1,15,15));
//        JPanel forgotContainer = new JPanel(new GridLayout());
        JLabel forgot = new JLabel("<html><u>Forgot Password<u><html>", SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
//        forgot.setBorder(new EmptyBorder(0,0,0,0));
        forgot.setForeground(Color.BLUE);
        forgot.setFont(new Font("Arial",1,15));
        forgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        forgot.setVerticalAlignment(SwingConstants.BOTTOM);
//        forgotPanel.add(forgot);
        
//        loginForm.add(imgPanel);
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
        JPanel regForm = new JPanel(new GridLayout(4,2,25,25));
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
        JTextField regEmailField = new JTextField();
        regEmailField.setFont(new Font("Arial",1,15));
        regEmailField.setForeground(fontColor);
        regEmailField.setBackground(bgColor);
        
        JLabel regPassLabel = new JLabel("Password: ");
        regPassLabel.setFont(new Font("Arial Black",1,17));
        regPassLabel.setForeground(fontColor);
        JPasswordField regPassField = new JPasswordField();
        regPassField.setFont(new Font("Arial",1,15));
        regPassField.setForeground(fontColor);
        regPassField.setBackground(bgColor);
        
        JLabel regPassConfLabel = new JLabel("Confirm Password: ");
        regPassConfLabel.setFont(new Font("Arial Black",1,17));
        regPassConfLabel.setForeground(fontColor);
        JPasswordField regPassConfField = new JPasswordField();
        regPassConfField.setFont(new Font("Arial",1,15));
        regPassConfField.setForeground(fontColor);
        regPassConfField.setBackground(bgColor);
        
        JButton newRegBtn = new JButton("Register");
        newRegBtn.setFont(new Font("Arial Black",1,17));
        newRegBtn.setForeground(fontColor);
        newRegBtn.setBackground(bgColor);
        newRegBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton backLoginBtn = new JButton("Login");
        backLoginBtn.setFont(new Font("Arial Black",1,17));
        backLoginBtn.setForeground(fontColor);
        backLoginBtn.setBackground(bgColor);
        backLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       

        regForm.setBorder(regBorder);
        regForm.add(regUserLabel);
        regForm.add(regEmailField);
        regForm.add(regPassLabel);
        regForm.add(regPassField);
        regForm.add(regPassConfLabel);
        regForm.add(regPassConfField);
        regForm.add(newRegBtn);
        regForm.add(backLoginBtn);

        //Forgot password page
        JPanel forgotForm = new JPanel(new GridLayout(3, 2, 30, 15));
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
        
        JTextField forgotEmailField = new JTextField();
        forgotEmailField.setFont(new Font("Arial",1,15));
        forgotEmailField.setForeground(fontColor);
        forgotEmailField.setBackground(bgColor);
        
        JLabel OTPLabel = new JLabel("OTP: ");
        OTPLabel.setFont(new Font("Arial Black",1,17));
        OTPLabel.setForeground(fontColor);
        
        JTextField OTPField = new JTextField();
        OTPField.setFont(new Font("Arial",1,15));
        OTPField.setForeground(fontColor);
        OTPField.setBackground(bgColor);
        OTPField.setEnabled(false);
        
        JButton resendOtpBtn = new JButton("Resend OTP");
        resendOtpBtn.setFont(new Font("Arial Black",1,12));
        resendOtpBtn.setForeground(fontColor);
        resendOtpBtn.setBackground(bgColor);
        resendOtpBtn.setEnabled(false);
        resendOtpBtn.setToolTipText("You can resend OTP once every 30 seconds");
        resendOtpBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JPanel OTPFieldGroup = new JPanel(new GridLayout(1, 2, 10, 0));
        
        JButton sendOTPBtn = new JButton("Send OTP");
        sendOTPBtn.setFont(new Font("Arial Black",1,17));
        sendOTPBtn.setForeground(fontColor);
        sendOTPBtn.setBackground(bgColor);
        sendOTPBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Arial Black",1,17));
        cancelBtn.setForeground(fontColor);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // After OTP sent components
        JButton verifyOTPBtn = new JButton("Verify");
        verifyOTPBtn.setFont(new Font("Arial Black",1,17));
        verifyOTPBtn.setForeground(fontColor);
        verifyOTPBtn.setBackground(bgColor);
        verifyOTPBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
//        JButton recancelBtn = new JButton("Cancel");
//        recancelBtn.setFont(new Font("Arial Black",1,17));
//        recancelBtn.setForeground(fontColor);
//        recancelBtn.setBackground(bgColor);
//        recancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // After OTP verification components
        JLabel newPassLabel = new JLabel("Set New Password: ");
        newPassLabel.setFont(new Font("Arial Black",1,17));
        newPassLabel.setForeground(fontColor);
        
        JPasswordField newPassField = new JPasswordField();
        newPassField.setFont(new Font("Arial",1,15));
        newPassField.setForeground(fontColor);
        newPassField.setBackground(bgColor);
        
        JButton resetPassBtn = new JButton("Reset Password");
        resetPassBtn.setFont(new Font("Arial Black",1,17));
        resetPassBtn.setForeground(fontColor);
        resetPassBtn.setBackground(bgColor);
        resetPassBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        OTPFieldGroup.add(OTPField);
        OTPFieldGroup.add(resendOtpBtn);

        forgotForm.setBorder(forgotBorder);
        forgotForm.add(forgotEmailLabel);
        forgotForm.add(forgotEmailField);
        forgotForm.add(OTPLabel);
        forgotForm.add(OTPFieldGroup);
        forgotForm.add(sendOTPBtn);
        forgotForm.add(cancelBtn);
    }
    
    
    public static void main(String[] args) {
    	View view = new View();
        view.setVisible(true);
    }
}

