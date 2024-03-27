package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.EncryptedDocumentException;

import model.repository.marksRepository;
import model.repository.storageParams;
import model.repository.storageRepository;

import model.transferObjects.marksTO;
import model.transferObjects.studentTO;

import model.userdata.student;
import model.userdata.user;

import utility.ExcelRow;
import utility.ExcelUtil;

public class MarksMgmtControl {
    public uploadBtnAL uploadBtnALFactory(JPanel mainPanel, DefaultListModel<user> model) {
        return new uploadBtnAL(mainPanel, model);
    }
    public studentListLSL studentListLSLFactory(JList<user> studentList, JPanel detailsGroup, JLabel warningLabel, JLabel adminMail, JLabel sidLabel, JLabel marksLabel, JTable marksTable, JTableHeader marksHeader) {
        return new studentListLSL(studentList, detailsGroup, warningLabel, adminMail, sidLabel, marksLabel, marksTable, marksHeader);
    }
}

class uploadBtnAL implements ActionListener {
    JPanel mainPanel;
    DefaultListModel<user> model;

    public uploadBtnAL(JPanel mainPanel, DefaultListModel<user> model) {
        this.mainPanel = mainPanel;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(mainPanel);

        if(res == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fc.getSelectedFile().getAbsolutePath();
                List<ExcelRow> rows = new ExcelUtil().readExcel(filePath);

                for(ExcelRow row : rows) {
                    marksRepository marksRepo = new marksRepository(new storageRepository());
                    storageParams params = new storageParams("marks");

                    int roll = row.number;
                    String coll = row.coll;
                    String cate = row.cate;
                    String paperName = row.course_title;
                    String paperCode = row.course_code;
                    String paperType = row.TH_PR_AI;
                    String half = row.half;
                    int FullMarks = row.full_marks;
                    int ObtMarks = row.marks_obtained;

                    if(roll == 0)   continue;

                    marksTO mTO = new marksTO(roll, coll, cate, paperName, paperCode, paperType, half, FullMarks, ObtMarks);
                    studentTO sTO = new studentTO(roll, coll, cate);
                    params.mTO = mTO;
                    params.sTO = sTO;

                    marksRepo.store(params);
                }

                marksRepository marksRepo = new marksRepository(new storageRepository());
                storageParams marksParams = marksRepo.retrieve();

                for(studentTO sTO : marksParams.sTOList) {
                    model.addElement(new student(Integer.toString(sTO.roll), sTO.coll, sTO.cate));
                }

                JOptionPane.showMessageDialog(mainPanel, "File parsed and added to DB", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException | EncryptedDocumentException err) {
                JOptionPane.showMessageDialog(mainPanel, "Some error occured", "Error", JOptionPane.ERROR_MESSAGE);
                err.printStackTrace();
            }
        }
    }
}

class studentListLSL implements ListSelectionListener {
    JList<user> studentList;
    JPanel detailsGroup;
    JLabel warningLabel;
    JLabel adminMail;
    JLabel sidLabel;
    JLabel marksLabel;
    JTable marksTable;
    JTableHeader marksHeader;

    public studentListLSL(JList<user> studentList, JPanel detailsGroup, JLabel warningLabel, JLabel adminMail, JLabel sidLabel, JLabel marksLabel, JTable marksTable, JTableHeader marksHeader) {
        this.studentList = studentList;
        this.detailsGroup = detailsGroup;
        this.warningLabel = warningLabel;
        this.adminMail = adminMail;
        this.sidLabel = sidLabel;
        this.marksLabel = marksLabel;
        this.marksTable = marksTable;
        this.marksHeader = marksHeader;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        user selectedUser = (user) studentList.getSelectedValue();
        warningLabel.setVisible(false);

        if(selectedUser.isAdmin()) {
            sidLabel.setVisible(false);
            marksLabel.setVisible(false);
            marksTable.setVisible(false);

            adminMail.setText("Admin Email ID: " + selectedUser);
            adminMail.setVisible(true);
        }
        else {
            adminMail.setVisible(false);


            sidLabel.setText("Student ID: " + selectedUser);
            sidLabel.setVisible(true);
            marksLabel.setVisible(true);

            String[] columnNames = {"Roll", "Coll", "Cate", "Paper Name", "Paper Code", "Paper Type", "Half", "FullMarks", "Obtained Marks"};

            marksRepository marksRepo = new marksRepository(new storageRepository());
            storageParams params = marksRepo.retrieveOne(Integer.toString(selectedUser.getRoll()));

            Object[][] rowData = new Object[params.mTOList.size()][columnNames.length];

            int i = 0;
            for(marksTO mTO : params.mTOList) {
                rowData[i][0] = mTO.roll;
                rowData[i][1] = mTO.coll;
                rowData[i][2] = mTO.cate;
                rowData[i][3] = mTO.paperName;
                rowData[i][4] = mTO.paperCode;
                rowData[i][5] = mTO.paperType;
                rowData[i][6] = mTO.half;
                rowData[i][7] = mTO.FullMarks;
                rowData[i][8] = mTO.ObtMarks;
                i++;
            }

            class marksTableModel implements TableModel {
                public String getColumnName(int col) {
                    return columnNames[col].toString();
                }
                public int getRowCount() {
                    return rowData.length;
                }
                public int getColumnCount() {
                    return columnNames.length;
                }
                public Object getValueAt(int row, int col) {
                    return rowData[row][col];
                }
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
                public Class getColumnClass(int c) {
                    return getValueAt(0, c).getClass();
                }
                public void setValueAt(Object value, int row, int col) {
                    rowData[row][col] = value;
                }
                public void addTableModelListener(TableModelListener l) {}
                public void removeTableModelListener(TableModelListener l) {}
            }

            marksTable.setModel(new marksTableModel());
            marksHeader.setColumnModel(marksTable.getColumnModel());

            marksHeader.setVisible(true);
            marksTable.setVisible(true);

            detailsGroup.revalidate();
            detailsGroup.repaint();
        }
    }
}