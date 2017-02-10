package by.bsuir.aipos.thriftclient;

import by.bsuir.aipos.thriftlib.ArithmeticService;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by andrey on 09/02/17.
 */
public class Client {

    public static Logger logger = Logger.getLogger(Client.class);

    private void invoke() {
        logger.info("Start client");
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket("localhost", 7911));
            TProtocol protocol = new TBinaryProtocol(transport);

            ArithmeticService.Client client = new ArithmeticService.Client(protocol);
            transport.open();

            long addResult = client.add(100, 200);
            logger.info("Add result: " + addResult);
            long multiplyResult = client.multiply(20, 40);
            logger.info("Multiply result: " + multiplyResult);

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.invoke();
    }
}