package org.example.thrift.demo.server;

import org.apache.thrift.TException;
import org.example.thrift.demo.client.thriftdatatype.ResultInt;
import org.example.thrift.demo.client.thriftdatatype.ResultStr;
import org.example.thrift.demo.client.thriftdatatype.ThriftResult;

/**
 * 服务端要实现Iface接口
 */
public class TestThriftServiceImpl implements TestThriftService.Iface {

    public ResultStr getStr(String srcStr1, String srcStr2) throws TException {

        long startTime = System.currentTimeMillis();
        ResultStr rs = new ResultStr();
        rs.setResult(ThriftResult.SUCCESS);
        String res = srcStr1 + srcStr2;
        rs.setValue(res);
        long stopTime = System.currentTimeMillis();

        System.out.println("[getStr]time inverval: " + (stopTime - startTime));
        return rs;
    }

    public ResultInt getInt(int val) throws TException {

        long startTime = System.currentTimeMillis();
        ResultInt ri = new ResultInt();
        ri.setResult(ThriftResult.INDEX_ERROR);
        int res = val * 10;
        ri.setValue(res);
        long stopTime = System.currentTimeMillis();

        System.out.println("[gitInt]time interval: " + (stopTime - startTime) * 1000);
        return ri;
    }
}
