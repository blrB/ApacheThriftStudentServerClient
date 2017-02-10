package by.bsuir.aipos.thriftlib;

import by.bsuir.aipos.thriftserver.Server;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

/**
 * Created by andrey on 09/02/17.
 */
public class ArithmeticServiceImpl implements ArithmeticService.Iface {

    public static Logger logger = Logger.getLogger(ArithmeticServiceImpl.class);

    public long add(int num1, int num2) throws TException {
        logger.info("Operation add + " + num1 + " + " + num2);
        return num1 + num2;
    }

    public long multiply(int num1, int num2) throws TException {
        logger.info("Operation multiply + " + num1 + " + " + num2);
        return num1 * num2;
    }

}