package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentGroupThrift;
import by.bsuir.aipos.thriftlib.StudentThrift;
import by.bsuir.aipos.thriftlib.StudentThriftService;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainWindow {

    public static Logger logger = Logger.getLogger(StudentClient.class);
    private JFrame frame;
    private String host;
    private int port;
    private StudentClient studentClient;
    private StudentTable studentTable;
    private List<StudentThrift> studentThrifts;
    private List<StudentGroupThrift> studentGroupThrifts;

    public MainWindow() {
        studentThrifts = new ArrayList<>();
        frame = new JFrame("Thrifts Student Client");
        frame.setLayout(new BorderLayout());
        host = getHost();
        port = getPort();
        frame.add(createToolBar(), BorderLayout.NORTH);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ExitAdapter(this));
        frame.setVisible(true);
        studentTable = new StudentTable(this);
        frame.add(studentTable, BorderLayout.CENTER);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(makeButton(new JButton(), "UPDATE.png", actionEvent -> updateTable()));
        toolBar.addSeparator();
        toolBar.add(makeButton(new JButton(), "ADD.png", actionEvent -> addStudent()));
        toolBar.add(makeButton(new JButton(), "EDIT.png", actionEvent -> editStudent()));
        toolBar.add(makeButton(new JButton(), "DELETE.png", actionEvent -> removeStudent()));
        return toolBar;
    }

    private void updateTable(){
        logger.info("Update table");
        studentTable.updatePanel();
    }

    private void addStudent(){
        logger.info("Add new student");
        updateTable();
    }

    private void editStudent(){
        logger.info("Edit student");
        updateTable();
    }

    private void removeStudent(){
        logger.info("Remove student");
        updateTable();
    }

    private JButton makeButton(JButton button, String imgName, ActionListener action){
        button.addActionListener(action);
        String patch = "img/" + imgName;
        ImageIcon img = new ImageIcon(patch);
        button.setIcon(img);
        return button;
    }

    private String getHost() {
        Pattern host = Pattern.compile("((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])");
        do {
            this.host = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter IP Address of the Server:",
                    "Welcome to the Citadels",
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

    private int getPort() {
        int minPort = 1;
        int maxPort = 65535;
        do {
            port = Integer.parseInt((String) JOptionPane.showInputDialog(
                    frame,
                    "Choose a port:",
                    "Port selection",
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

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.runClient();
    }

    private void runClient(){
        studentClient = new StudentClient(host, port);
        studentClient.start();
    }

    public void transportClose(){
        studentClient.transportClose();
    }

    public StudentClient getStudentClient() {
        return studentClient;
    }
}
