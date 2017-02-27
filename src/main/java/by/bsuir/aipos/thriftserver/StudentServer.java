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
    /**
     * Logger for server
     */
    private static Logger logger = Logger.getLogger(StudentServer.class);
    /**
     * Student group service
     */
    private static StudentGroupService studentGroupService = new StudentGroupServiceImpl();
    /**
     * Student service
     */
    private static StudentService studentService = new StudentServiceImpl();
    /**
     * Constant for default server's port
     */
    private static final int DEFAULT_PORT = 8080;
    /**
     * Initialize port by default value
     */
    private static int port = DEFAULT_PORT;

    /**
     * Server runner
     * @param args the only sent argument is server's port
     */
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

    /**
     * Run server
     */
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

    /**
     * Return logger
     * @return logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Return student group service
     * @return student group service
     */
    public static StudentGroupService getStudentGroupService() {
        return studentGroupService;
    }

    /**
     * Return student service
     * @return student service
     */
    public static StudentService getStudentService() {
        return studentService;
    }

    /**
     * Write amazing text in a console
     */
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
