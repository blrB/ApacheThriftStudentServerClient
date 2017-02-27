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
    private static StudentGroupService studentGroupService;
    private static StudentService studentService;
    private static final int DEFAULT_PORT = 8080;
    private static int port = DEFAULT_PORT;

    public StudentServer() {
        studentGroupService = new StudentGroupServiceImpl();
        studentService = new StudentServiceImpl();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.error("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        StudentServer studentServer = new StudentServer();
        studentServer.start();
    }

    private void start() {
        startServer();
        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            StudentThriftService.Processor processor =
                    new StudentThriftService.Processor(new StudentThriftServiceImpl());

            TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).
                    processor(processor));
            logger.info("Starting server on port " + port);
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
