package com.matas;

import org.apache.tomcat.util.net.AbstractEndpoint;
import org.apache.tomcat.util.net.DefaultServerSocketFactory;
import org.apache.tomcat.util.net.JIoEndpoint;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关于多个Acceptor共用一个ServerSocket。
 * <p>
 * {@link AbstractEndpoint#startAcceptorThreads()} 中getAcceptorThreadCount()如果返回多个，就会创建多个Acceptor线程。
 * {@link JIoEndpoint.Acceptor#run()} 调用 {@link DefaultServerSocketFactory#acceptSocket(java.net.ServerSocket)} 。ServerSocket是同一个。而ServerSocket非线程安全。
 * 所以最好不要设置acceptorThreadCount。默认就是1个
 *
 * @author matas
 * @date 2018/8/25 18:41
 * @email mataszhang@163.com
 */
public class TestAccept {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket ss = new ServerSocket(8888);
        ServerThread serverThread = new ServerThread(ss);
        executorService.submit(serverThread);
        executorService.submit(serverThread);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new ClientThread());
        }
    }

    private static class ClientThread implements Runnable {

        @Override
        public void run() {
            try {
                Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ServerThread implements Runnable {
        private ServerSocket ss;

        private ServerThread(ServerSocket ss) {
            this.ss = ss;
        }


        @Override
        public void run() {
            try {
                System.err.println(Thread.currentThread() + "=>started");
                while (true) {
                    TimeUnit.MILLISECONDS.sleep(50);
                    Socket accept = ss.accept();
                    System.err.println(Thread.currentThread().getName() + "=>处理" + accept);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
