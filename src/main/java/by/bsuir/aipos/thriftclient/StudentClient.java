package by.bsuir.aipos.thriftclient;

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

import java.util.List;

public class StudentClient {

    public static Logger logger = Logger.getLogger(StudentClient.class);
    private static int port = 8080;
    private static String host = "localhost";

    public static void main(String[] args) {
        StudentClient studentClient = new StudentClient();
        studentClient.invoke();
    }

    private void invoke() {
        logger.info("Start client");
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(host, port));
            TProtocol protocol = new TBinaryProtocol(transport);
            StudentThriftService.Client client = new StudentThriftService.Client(protocol);
            transport.open();

            List<StudentThrift> studentThrift = client.getAllStudent();
            System.out.print(studentThrift);

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
