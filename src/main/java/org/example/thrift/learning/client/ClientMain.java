package org.example.thrift.learning.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.thrift.learning.client.thriftdatatype.ResultInt;
import org.example.thrift.learning.client.thriftdatatype.ResultStr;

public class ClientMain {
    private static TTransport m_transport;
    private static final String THRIFT_HOST = "localhost";
    public static final int THRIFT_PORT = 12356;

    public static void main(String[] args) {
        // TTransport传输类都是对I/O层的封装，更直观理解为封装了一个Socket
        // TFramedTransport内部封装了一个读写buffer，写入时先写到buffer，之后调用flush方法将buffer封装成帧后发送(4字节帧长+帧内容)
        m_transport = new TFramedTransport(new TSocket(THRIFT_HOST, THRIFT_PORT, 2000));

        // TProtocol提供了一组操作函数，用以规定采用哪种协议进行数据的读写，其内部包含一个传输类TTransport，从TTransport的输入输出流中读写数据
        // TBinaryProtocol以二进制方式进行数据读写。写时会为消息内容封装一个首部，首部信息来自TMessage类(消息名称、消息序列号、消息类型)
        // 消息内容则是在返回值封装类中(如getStr_result)
        TProtocol protocol = new TBinaryProtocol(m_transport);

        // 创建一个client需要socket连接、trasport传输、protocol协议
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
