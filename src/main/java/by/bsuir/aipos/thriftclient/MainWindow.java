package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentGroupThrift;
import by.bsuir.aipos.thriftlib.StudentThrift;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainWindow {
    /**
     * Logger
     */
    public static Logger logger = Logger.getLogger(StudentClient.class);
    /**
     * Main frame
     */
    private JFrame frame;
    /**
     * Host address of sever
     */
    private String host;
    /**
     * Host port of server
     */
    private int port;
    /**
     * Student client
     */
    private StudentClient studentClient;
    /**
     * Table with students
     */
    private StudentTable studentTable;

    /**
     * Create main window and add content to it
     */
    public MainWindow() {
        frame = new JFrame("Thrifts Student Client");
        host = getHost();
        port = getPort();
        frame.setLayout(new BorderLayout());
        frame.add(createToolBar(), BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ExitAdapter(this));
        studentTable = new StudentTable(this);
        frame.add(studentTable, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Toolbar creator
     *
     * @return created toolbar
     */
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(AddComponent.makeButton(new JButton(), "UPDATE.png", actionEvent -> updateTable()));
        toolBar.addSeparator();
        toolBar.add(AddComponent.makeButton(new JButton(), "ADD.png", actionEvent -> addStudent()));
        toolBar.add(AddComponent.makeButton(new JButton(), "EDIT.png", actionEvent -> editStudent()));
        toolBar.add(AddComponent.makeButton(new JButton(), "DELETE.png", actionEvent -> removeStudent()));
        return toolBar;
    }

    /**
     * Update table with students by getting data from server
     */
    public void updateTable(){
        logger.info("Update table");
        studentTable.updatePanel();
    }

    /**
     * Create new student and add it to a table
     */
    private void addStudent(){
        logger.info("Add new student");
        StudentDialog dialog = new StudentDialog(this, "Add new Student");
        dialog.show();
        updateTable();
    }

    /**
     * Edit selected student from a table
     */
    private void editStudent(){
        StudentThrift studentThrift = studentTable.getSelectedStudent();
        if (studentThrift != null) {
            logger.info("Edit student");
            StudentDialog dialog = new StudentDialog(this, "Edit Student");
            dialog.setField(studentThrift);
            dialog.show();
            updateTable();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select student in table!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove selected student from table
     */
    private void removeStudent(){
        StudentThrift studentThrift = studentTable.getSelectedStudent();
        if (studentThrift != null) {
            logger.info("Remove student");
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to delete student " + studentThrift.getLastName() + "?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                getStudentClient().deleteStudent(studentThrift.getId());
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Select student in table!",
                    "Not valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Get server's host address that was entered by user
     *
     * @return server's host address
     */
    private String getHost() {
        Pattern host = Pattern.compile("((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])");
        do {
            this.host = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter IP Address of the Server:",
                    "Welcome to StudentThriftClient",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    "127.0.0.1");
            if (this.host == null) {
                System.exit(0);
            }
        }
        while (!host.matcher(this.host).matches());
        return this.host;
    }

    /**
     * Get port entered by user
     *
     * @return server port
     */
    private int getPort() {
        int minPort = 1;
        int maxPort = 65535;
        do {
            port = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Choose a port:",
                    "Port chooser",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "8080"));
            if (port == 0) {
                System.exit(0);
            }
        }
        while (port < minPort && port > maxPort);
        return port;
    }

    /**
     * Client app runner
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.runClient();
    }

    /**
     * Init client
     */
    private void runClient(){
        studentClient = new StudentClient(host, port, this);
        studentClient.start();
    }

    /**
     * Close client connection
     */
    public void transportClose(){
        studentClient.transportClose();
    }

    /**
     * Get student client
     *
     * @return student client instance
     */
    public StudentClient getStudentClient() {
        return studentClient;
    }

    /**
     * Get main window instance
     *
     * @return main window instance
     */
    public JFrame getFrame() {
        return frame;
    }
}
