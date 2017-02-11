package by.bsuir.aipos.thriftserver;

import by.bsuir.aipos.service.StudentGroupService;
import by.bsuir.aipos.service.StudentGroupServiceImpl;
import by.bsuir.aipos.service.StudentService;
import by.bsuir.aipos.service.StudentServiceImpl;
import by.bsuir.aipos.thriftlib.StudentThriftService;
import by.bsuir.aipos.thriftlib.StudentThriftServiceImpl;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

public class StudentServer {

    private static Logger logger = Logger.getLogger(StudentServer.class);
    private static StudentGroupService studentGroupService = new StudentGroupServiceImpl();
    private static StudentService studentService = new StudentServiceImpl();
    private static final int PORT = 8080;

    public static void main(String[] args) {
        StudentServer studentServer = new StudentServer();
        studentServer.start();
    }

    private void start() {
        startServer();
        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(PORT);
            StudentThriftService.Processor processor =
                    new StudentThriftService.Processor(new StudentThriftServiceImpl());

            TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).
                    processor(processor));
            logger.info("Starting server on port " + PORT );
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static StudentGroupService getStudentGroupService() {
        return studentGroupService;
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    private void startServer(){
        logger.info("Start server\n" +
                "                            _            _______ _          _  __ _   \n" +
                "     /\\                    | |          |__   __| |        (_)/ _| |  \n" +
                "    /  \\   _ __   __ _  ___| |__   ___     | |  | |__  _ __ _| |_| |_ \n" +
                "   / /\\ \\ | '_ \\ / _` |/ __| '_ \\ / _ \\    | |  | '_ \\| '__| |  _| __|\n" +
                "  / ____ \\| |_) | (_| | (__| | | |  __/    | |  | | | | |  | | | | |_ \n" +
                " /_/    \\_\\ .__/ \\__,_|\\___|_| |_|\\___|    |_|  |_| |_|_|  |_|_|  \\__|\n" +
                "          | |                                                         \n" +
                "          |_|                                                         \n\n");
    }
}
