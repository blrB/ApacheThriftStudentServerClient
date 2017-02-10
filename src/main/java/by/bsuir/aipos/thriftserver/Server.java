package by.bsuir.aipos.thriftserver;

import by.bsuir.aipos.thriftlib.ArithmeticService;
import by.bsuir.aipos.thriftlib.ArithmeticServiceImpl;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by andrey on 09/02/17.
 */
public class Server {

    public static Logger logger = Logger.getLogger(Server.class);

    private void start() {
        logger.info("Start server");
        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(7911);
            ArithmeticService.Processor processor = new ArithmeticService.Processor(new ArithmeticServiceImpl());

            TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).
                    processor(processor));
            logger.info("Starting server on port 7911 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.start();
    }

}
