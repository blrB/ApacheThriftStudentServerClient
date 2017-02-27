package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentThrift;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentTable extends JComponent {
    /**
     * Main window instance
     */
    private MainWindow mainWindow;
    /**
     * Table with students
     */
    private JTable table;
    /**
     * Scroll pane for table with students
     */
    private JScrollPane scrollPane;
    /**
     * List of students
     */
    private List<StudentThrift> listOfStudent;

    /**
     * Create student table for given main window
     *
     * @param mainWindow frame where table is located
     */
    public StudentTable(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
    }

    /**
     * Update scroll pane
     */
    private void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    /**
     * Add content to the table
     */
    public void createPanel() {
        listOfStudent = mainWindow.getStudentClient().getAllStudent();
        StudentTableModel model = new StudentTableModel(listOfStudent);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(evt -> updateScrollPane());
        scrollPane.getVerticalScrollBar().addAdjustmentListener(evt -> updateScrollPane());
        add(scrollPane);
    }

    /**
     * Update content of the table
     */
    public void updatePanel() {
        removeAll();
        createPanel();
        revalidate();
        repaint();
    }

    /**
     * Return selected by user student
     * @return selected by user student
     */
    public StudentThrift getSelectedStudent(){
        if (table.getSelectedRow() < 0){
            return null;
        }
        return listOfStudent.get(table.getSelectedRow());
    }
}
