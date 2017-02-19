package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.StudentGroupThrift;
import by.bsuir.aipos.thriftlib.StudentThrift;
import by.bsuir.aipos.thriftlib.StudentThriftService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;

public class StudentClient extends Thread implements StudentThriftService.Iface{

    private final MainWindow mainWindow;
    private TTransport transport;
    private StudentThriftService.Client client;
    private int port;
    private String host;

    public StudentClient(String host, int port, MainWindow mainWindow) {
        this.host = host;
        this.port = port;
        this.mainWindow = mainWindow;
    }

    public void run() {
        MainWindow.logger.info("Start client");
        try {
            transport = new TFramedTransport(new TSocket(host, port));
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new StudentThriftService.Client(protocol);
            transport.open();
            mainWindow.updateTable();
        } catch (TException e) {
            MainWindow.logger.error("Error in StudentClient run() ");
            MainWindow.logger.trace(e);
            System.exit(-1);
        }
    }

    public void transportClose(){
        try {
            transport.close();
        } catch (Exception e){
            MainWindow.logger.error("Error in transportClose");
            MainWindow.logger.trace(e);
        }
        System.exit(0);
    }

    @Override
    public StudentThrift saveStudent(StudentThrift studentThrift) {
        try {
            studentThrift = client.saveStudent(studentThrift);
        } catch (TException e) {
            MainWindow.logger.error("Error in saveStudent");
            MainWindow.logger.trace(e);
        }
        return studentThrift;
    }

    @Override
    public StudentThrift getStudent(long id) {
        StudentThrift studentThrift = new StudentThrift();
        try {
            studentThrift = client.getStudent(id);
        } catch (TException e) {
            MainWindow.logger.error("Error in getStudent");
            MainWindow.logger.trace(e);
        }
        return studentThrift;
    }

    @Override
    public void deleteStudent(long id) {
        try {
            client.deleteStudent(id);
        } catch (TException e) {
            MainWindow.logger.error("Error in deleteStudent");
            MainWindow.logger.trace(e);
        }
    }

    @Override
    public List<StudentThrift> getAllStudent(){
        List<StudentThrift> studentThrifts = new ArrayList<>();
        try {
            studentThrifts = client.getAllStudent();
        } catch (TException e) {
            MainWindow.logger.error("Error in getAllStudent");
            MainWindow.logger.trace(e);
        }
        return studentThrifts;
    }

    @Override
    public StudentGroupThrift saveStudentGroup(StudentGroupThrift studentGroupThrift) {
        try {
            studentGroupThrift = client.saveStudentGroup(studentGroupThrift);
        } catch (TException e) {
            MainWindow.logger.error("Error in saveStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroupThrift;
    }

    @Override
    public StudentGroupThrift getStudentGroup(long id) {
        StudentGroupThrift studentGroupThrift = new StudentGroupThrift();
        try {
            studentGroupThrift = client.getStudentGroup(id);
        } catch (TException e) {
            MainWindow.logger.error("Error in getStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroupThrift;
    }

    @Override
    public StudentGroupThrift getStudentGroupByName(String name) {
        StudentGroupThrift studentGroupThrift = new StudentGroupThrift();
        try {
            studentGroupThrift = client.getStudentGroupByName(name);
        } catch (TException e) {
            MainWindow.logger.error("Error in getStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroupThrift;
    }

    @Override
    public void deleteStudentGroup(long id) {
        try {
            client.deleteStudentGroup(id);
        } catch (TException e) {
            MainWindow.logger.error("Error in deleteStudentGroup");
            MainWindow.logger.trace(e);
        }
    }

    @Override
    public List<StudentGroupThrift> getAllStudentGroup() {
        List<StudentGroupThrift> studentGroupThrifts = new ArrayList<>();
        try {
            studentGroupThrifts = client.getAllStudentGroup();
        } catch (TException e) {
            MainWindow.logger.error("Error in getAllStudentGroup");
            MainWindow.logger.trace(e);
        }
        return studentGroupThrifts;
    }
}
