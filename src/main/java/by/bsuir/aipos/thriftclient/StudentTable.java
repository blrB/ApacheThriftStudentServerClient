package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentThrift;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentTable extends JComponent {

    private MainWindow mainWindow;
    private JTable table;
    private JScrollPane scrollPane;
    private List<StudentThrift> listOfStudent;

    public StudentTable(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
    }

    private void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void createPanel() {
        listOfStudent = mainWindow.getStudentClient().getAllStudent();
        StudentTableModel model = new StudentTableModel(listOfStudent, mainWindow);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(evt -> updateScrollPane());
        scrollPane.getVerticalScrollBar().addAdjustmentListener(evt -> updateScrollPane());
        add(scrollPane);
    }

    public void updatePanel() {
        removeAll();
        createPanel();
        revalidate();
        repaint();
    }

    public StudentThrift getSelectedStudent(){
        if (table.getSelectedRow() < 0){
            return null;
        }
        return listOfStudent.get(table.getSelectedRow());
    }
}
