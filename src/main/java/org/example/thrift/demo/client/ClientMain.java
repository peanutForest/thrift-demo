package org.example.thrift.demo.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.thrift.demo.client.thriftdatatype.ResultInt;
import org.example.thrift.demo.client.thriftdatatype.ResultStr;

public class ClientMain {
    private static TTransport m_transport;
    private static final String THRIFT_HOST = "localhost";
    public static final int THRIFT_PORT = 12356;

    public static void main(String[] args) {

        m_transport = new TFramedTransport(new TSocket(THRIFT_HOST, THRIFT_PORT, 2000));
        TProtocol protocol = new TBinaryProtocol(m_transport);
        TestThriftService.Client testClient = new TestThriftService.Client(protocol);

        try {
            m_transport.open();

            ResultStr res = testClient.getStr("hello ", "world!");
            System.out.printf("Result = %s and res = %s%n", res.result, res.getValue());
            ResultInt resp = testClient.getInt(1);
            System.out.printf("Result = %s and resp = %d%n", resp.getResult(), resp.value);
            m_transport.close();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
