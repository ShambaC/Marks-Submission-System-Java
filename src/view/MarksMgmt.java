package view;

import model.repository.marksRepository;
import model.repository.storageParams;
import model.repository.storageRepository;
import model.repository.userRepository;
import model.transferObjects.studentTO;
import model.transferObjects.userTO;
import model.userdata.admin;
import model.userdata.student;
import model.userdata.user;
import utility.DButil;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import control.MarksMgmtControl;


/**
 * Class that stores the main UI of the main window
 */
public class MarksMgmt extends JFrame {

	// initialize JList with model
	// A list model to store all the employees
	DefaultListModel<user> model = new DefaultListModel<user>();

	
    
    Color bgColor = new Color(242, 245, 255);
    Color panelColor = new Color(206, 215, 246);
    Color fontColor = new Color(8, 17, 54);
    Color placeholderColor = new Color(195, 195, 195);
   

    public MarksMgmt() { 
        setTitle("Marks Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       userRepository userRepo = new userRepository(new storageRepository());
       storageParams userParams = userRepo.retrieve();

       for(userTO uTO : userParams.uTOList) {
           model.addElement(new admin(uTO.email));
       }

       marksRepository marksRepo = new marksRepository(new storageRepository());
       storageParams marksParams = marksRepo.retrieve();

       for(studentTO sTO : marksParams.sTOList) {
           model.addElement(new student(Integer.toString(sTO.roll), sTO.coll, sTO.cate));
       }

        init();
    }

    private void init() {

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        // Main panel to hold everything
        JPanel mainPanel = new JPanel(gb);
        mainPanel.setBackground(bgColor);
        
        gbc.weightx = 7;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(6, 0, 0, 0);
        // Tabs for operations
        JTabbedPane tabsMenu = new JTabbedPane(JTabbedPane.LEFT);
        tabsMenu.setBackground(bgColor);

        //To upload a file
        JPanel uploadTab = new JPanel();
        
        uploadTab.setBackground(panelColor);
        TitledBorder uploadBorder = new TitledBorder(new EmptyBorder(30,10,10,10));
        uploadTab.setBorder(uploadBorder);
        
        JPanel uploadGroup = new JPanel(new GridLayout(0,2,50,15));
        uploadGroup.setBackground(panelColor);
        
        JLabel uploadLabel = new JLabel("Upload Excel File: ");
        uploadLabel.setFont(new Font("Arial Black",1,17));
        uploadLabel.setForeground(fontColor);
        
        JButton uploadBtn = new JButton("Upload File");
        uploadBtn.setFont(new Font("Arial Black",1,17));
        uploadBtn.setForeground(fontColor);
        uploadBtn.setBackground(bgColor);
        uploadBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        uploadGroup.add(uploadLabel);
        uploadGroup.add(uploadBtn);
        uploadTab.add(uploadGroup);
        

        // The details tab to show information for the selected employee
        JPanel detailsTab = new JPanel();
        detailsTab.setBackground(panelColor);
        TitledBorder detailsBorder = new TitledBorder(new EmptyBorder(30,10,10,10));
        detailsTab.setBorder(detailsBorder);
        
        JPanel detailsGroup = new JPanel(new GridLayout(0, 1));
        detailsGroup.setBackground(panelColor);

        JLabel warningLabel = new JLabel("Select an user to view details");
        warningLabel.setFont(new Font("Arial Black",1,17));

        JLabel adminMail = new JLabel("Admin Email ID: ");
        adminMail.setFont(new Font("Arial Black",1,17));
        adminMail.setVisible(false);
        
        JLabel sidLabel = new JLabel("Student ID: ");
        sidLabel.setFont(new Font("Arial Black",1,17));
        sidLabel.setVisible(false);
        
        JLabel marksLabel = new JLabel("Marks: ");
        marksLabel.setFont(new Font("Arial Black",1,17));
        marksLabel.setVisible(false);

        JTable marksTable = new JTable();
        JTableHeader marksHeader = marksTable.getTableHeader();
        marksHeader.setVisible(false);
        marksTable.setVisible(false);

        detailsGroup.add(warningLabel);
        detailsGroup.add(adminMail);
        detailsGroup.add(sidLabel);
        detailsGroup.add(marksLabel);
        detailsGroup.add(marksHeader);
        detailsGroup.add(marksTable);
        detailsTab.add(detailsGroup);
        
        
        // Queries tab to add and remove employees under a leader
        JPanel queriesTab = new JPanel();
        queriesTab.setBackground(panelColor);
        TitledBorder queryBorder = new TitledBorder(new EmptyBorder(30,10,10,10));
        queriesTab.setBorder(queryBorder);
        
        JPanel queriesGroup = new JPanel(new GridLayout(5, 1, 20, 20));
        queriesGroup.setBackground(panelColor);

        DefaultListModel<String> subListModel = new DefaultListModel<String>();
        DButil dbUtil = DButil.getInstance();
        try{
            ResultSet res = dbUtil.executeQueryStatement("select distinct paperCode, paperType from marks;");
            while(res.next()) {
                subListModel.addElement(res.getString("paperCode") + ":" + res.getString("paperType"));
            }
        }
        catch(SQLException err) {
            System.err.println(err.getErrorCode() + " " + err.getSQLState() + " " + err.getMessage());
        }
        JList<String> querySubList = new JList<>(subListModel);
        querySubList.setSelectedIndex(0);

        // Query Button
        JButton maxBtn = new JButton("Highest marks");
        maxBtn.setFont(new Font("Arial Black",1,17));
        maxBtn.setForeground(fontColor);
        maxBtn.setBackground(bgColor);
        maxBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton minBtn = new JButton("Lowest marks");
        minBtn.setFont(new Font("Arial Black",1,17));
        minBtn.setForeground(fontColor);
        minBtn.setBackground(bgColor);
        minBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton passBtn = new JButton("Percentage of Passed Students");
        passBtn.setFont(new Font("Arial Black",1,17));
        passBtn.setForeground(fontColor);
        passBtn.setBackground(bgColor);
        passBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton avgBtn = new JButton("Average marks");
        avgBtn.setFont(new Font("Arial Black",1,17));
        avgBtn.setForeground(fontColor);
        avgBtn.setBackground(bgColor);
        avgBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        queriesGroup.add(querySubList);
        queriesGroup.add(maxBtn);
        queriesGroup.add(minBtn);
        queriesGroup.add(avgBtn);
        queriesGroup.add(passBtn);

        queriesTab.add(queriesGroup);


        // The data tab to store and load data from files
        JPanel pdfTab = new JPanel();
        pdfTab.setBackground(panelColor);
        TitledBorder dataBorder = new TitledBorder(new EmptyBorder(30,10,10,10));
        pdfTab.setBorder(dataBorder);
        
        JPanel dataGroup = new JPanel(new GridLayout(0, 1, 50, 15));
        dataGroup.setBackground(panelColor);
        
        JList<String> pdfSubList = new JList<>(subListModel);
        pdfSubList.setSelectedIndex(0);

        JLabel pdfLabel = new JLabel("Generate PDF: ");
        pdfLabel.setFont(new Font("Arial Black",1,17));
        pdfLabel.setForeground(fontColor);
        
        JButton pdfButton = new JButton("Select Location and Generate");
        pdfButton.setFont(new Font("Arial Black",1,17));
        pdfButton.setForeground(fontColor);
        pdfButton.setBackground(bgColor);
        pdfButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel pdfEmailLabel = new JLabel("Enter email address: ");
        pdfEmailLabel.setFont(new Font("Arial Black",1,17));
        pdfEmailLabel.setForeground(fontColor);

        JTextField pdfEmailField = new JTextField();
        pdfEmailField.setFont(new Font("Arial Black",1,17));
        pdfEmailField.setForeground(fontColor);

        JButton sendPdfButton = new JButton("Send PDF");
        sendPdfButton.setFont(new Font("Arial Black",1,17));
        sendPdfButton.setForeground(fontColor);
        sendPdfButton.setBackground(bgColor);
        sendPdfButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dataGroup.add(pdfSubList);
        dataGroup.add(pdfLabel);
        dataGroup.add(pdfButton);
        dataGroup.add(pdfEmailLabel);
        dataGroup.add(pdfEmailField);
        dataGroup.add(sendPdfButton);
        pdfTab.add(dataGroup);
        
        TitledBorder tabsBorder = new TitledBorder(new EmptyBorder(10,10,10,10));
        tabsBorder.setTitleJustification(TitledBorder.CENTER);
        tabsBorder.setTitlePosition(TitledBorder.TOP);
        tabsBorder.setTitleFont(new Font("Arial Black",1,15));
        tabsBorder.setTitleColor(fontColor);
        
        tabsMenu.setBorder(tabsBorder);
        tabsMenu.setFont(new Font("Arial Black",1,17));
        tabsMenu.addTab("Upload", uploadTab);
        tabsMenu.addTab("Details", detailsTab);
        tabsMenu.addTab("Queries", queriesTab);
        tabsMenu.addTab("PDF", pdfTab);
//        tabsMenu.setEnabledAt(1, false);
        gb.setConstraints(tabsMenu, gbc);

        // The list of the employees
        TitledBorder listBorder = new TitledBorder(new EmptyBorder(35,10,15,10), "Students List");
        listBorder.setTitleJustification(TitledBorder.CENTER);
        listBorder.setTitlePosition(TitledBorder.TOP);
        listBorder.setTitleFont(new Font("Arial Black",1,19));
        listBorder.setTitleColor(fontColor);
        
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        JList<user> studentList = new JList<user>(model);
        JScrollPane studentScroll = new JScrollPane(studentList);
        studentScroll.setBackground(bgColor);
        studentScroll.setFont(new Font("Arial Black",1,13));
        studentScroll.setBorder(listBorder);
        gb.setConstraints(studentScroll, gbc);

        // add components to main panel
        mainPanel.add(tabsMenu);
        mainPanel.add(studentScroll);

        add(mainPanel);

        MarksMgmtControl mmCon = new MarksMgmtControl();

        uploadBtn.addActionListener(mmCon.uploadBtnALFactory(mainPanel, model, subListModel));
        studentList.addListSelectionListener(mmCon.studentListLSLFactory(studentList, detailsGroup, warningLabel, adminMail, sidLabel, marksLabel, marksTable, marksHeader));
        maxBtn.addActionListener(mmCon.maxBtnALFactory(mainPanel, querySubList));
        minBtn.addActionListener(mmCon.minBtnALFactory(mainPanel, querySubList));
        avgBtn.addActionListener(mmCon.avgBtnALFactory(mainPanel, querySubList));
        passBtn.addActionListener(mmCon.passBtnALFactory(mainPanel, querySubList));
        pdfButton.addActionListener(mmCon.pdfButtonALFactory(mainPanel, pdfSubList));
        sendPdfButton.addActionListener(mmCon.sendPdfButtonALFactory(mainPanel, pdfSubList, pdfEmailField));
    }

	
	public static void main(String[] args) {
		MarksMgmt mm = new MarksMgmt();
		mm.setVisible(true);
	}

}
