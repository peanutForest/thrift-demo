package org.example.thrift.demo.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.example.thrift.demo.server.TestThriftService;
import org.example.thrift.demo.server.TestThriftServiceImpl;

public class ServerMain {

    private static int m_thriftPort = 12356;
    // 创建自己的服务对象
    private static TestThriftServiceImpl m_myService = new TestThriftServiceImpl();
    private static TServer m_server = null;

    private static void createNonblockingServer() throws TTransportException {
        TProcessor tProcessor = new TestThriftService.Processor<TestThriftService.Iface>(m_myService);
        TNonblockingServerSocket nioSocket = new TNonblockingServerSocket(m_thriftPort);

        // 创建一个server需要socket连接、transport传输、protocol协议、processor处理
        TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(nioSocket);
        tnbArgs.transportFactory(new TFramedTransport.Factory());
        tnbArgs.protocolFactory(new TBinaryProtocol.Factory());
        tnbArgs.processor(tProcessor);
        m_server = new TNonblockingServer(tnbArgs);
    }

    public static boolean start() {
        try {
            createNonblockingServer();
        } catch (TTransportException e) {
            System.out.println("start server error!" + e);
            return false;
        }
        System.out.println("service at port: " + m_thriftPort);
        m_server.serve();
        return true;
    }

    public static void main(String[] args) {
        if (!start()) {
            System.exit(0);
        }
    }
}
