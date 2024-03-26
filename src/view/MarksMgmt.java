package view;
import model.userdata.user;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.transferObjects.*;
import model.userdata.*;
import model.repository.*;

public class MarksMgmt extends JFrame{

	// A list model to store all the employees
	DefaultListModel<user> model = new DefaultListModel<user>();

//	userRepository userRepo = new userRepository(new storageRepository());
//	storageParams userParams = userRepo.retrieve();
//
//	for(userTO uTO : userParams.uTOList) {
//	    admin adm = new admin(uTO.email);
//	    model.addElement(adm);
//	}
//
//	marksRepository marksRepo = new MarksRepository(new storageRepository());
//	storageParams marksParams = marksRepo.retrieve();
//
//	for(studentTO sTO : marksParams.sTOList) {
//	    student stu = new student(Integer.toString(sTO.roll), sTO.coll, sTO.cate);
//	    model.addElement(stu);
//	}

	// initialize JList with model

    
    // Stores the currently selected employee from the list
    user selectedEmployee = null;
    
    Color bgColor = new Color(242, 245, 255);
    Color panelColor = new Color(206, 215, 246);
    Color fontColor = new Color(8, 17, 54);
    Color placeholderColor = new Color(195, 195, 195);
   

    MarksMgmt() {
        setTitle("Marks Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        
        JLabel uploadLabel = new JLabel("Upload CSV File: ");
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

        JLabel adminMail = new JLabel("Admin Email ID: ");
        adminMail.setFont(new Font("Arial Black",1,17));
        
        JLabel sidLabel = new JLabel("Student ID: ");
        sidLabel.setFont(new Font("Arial Black",1,17));
        
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial Black",1,17));
        
        JLabel marksLabel = new JLabel("Marks: ");
        marksLabel.setFont(new Font("Arial Black",1,17));

        detailsGroup.add(adminMail);
        detailsGroup.add(sidLabel);
        detailsGroup.add(nameLabel);
        detailsGroup.add(marksLabel);
        detailsTab.add(detailsGroup);
        
        
        // Queries tab to add and remove employees under a leader
        JPanel queriesTab = new JPanel();
        queriesTab.setBackground(panelColor);
        TitledBorder queryBorder = new TitledBorder(new EmptyBorder(30,10,10,10));
        queriesTab.setBorder(queryBorder);
        
        JPanel queriesGroup = new JPanel(new GridLayout(3, 0, 20, 20));
        queriesGroup.setBackground(panelColor);

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
        
        JPanel dataGroup = new JPanel(new GridLayout(0, 2, 50, 15));
        dataGroup.setBackground(panelColor);
        
        JLabel pdfLabel = new JLabel("Generate PDF: ");
        pdfLabel.setFont(new Font("Arial Black",1,17));
        pdfLabel.setForeground(fontColor);
        
        JButton pdfButton = new JButton("PDF");
        pdfButton.setFont(new Font("Arial Black",1,17));
        pdfButton.setForeground(fontColor);
        pdfButton.setBackground(bgColor);
        pdfButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dataGroup.add(pdfLabel);
        dataGroup.add(pdfButton);
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
        JList studentList = new JList(model);
        JScrollPane studentScroll = new JScrollPane(studentList);
        studentScroll.setBackground(bgColor);
        studentScroll.setFont(new Font("Arial Black",1,13));
        studentScroll.setBorder(listBorder);
        gb.setConstraints(studentScroll, gbc);

        // add components to main panel
        mainPanel.add(tabsMenu);
        mainPanel.add(studentScroll);

        add(mainPanel);
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarksMgmt mm = new MarksMgmt();
		mm.setVisible(true);
	}

}
