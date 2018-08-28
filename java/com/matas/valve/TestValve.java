package com.matas.valve;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *
 *@author matas
 *@date  2018/8/28 13:50
 *@email   mataszhang@163.com
 */
public class TestValve extends ValveBase {
    private static final Log log = LogFactory.getLog(TestValve.class);

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        log.info("------------------------" + request.getRequestURI());
        this.getNext().invoke(request,response);
    }
}
