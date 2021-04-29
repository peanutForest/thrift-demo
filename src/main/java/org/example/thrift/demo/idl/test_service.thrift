namespace java com.test.service

include "thrift_datatype.thrift"

service TestThriftService {
    /*
        添加注释
    */
    thrift_datatype.ResultStr getStr(1:string srcStr1, 2:string srcStr2),

    thrift_datatype.ResultInt getInt(1:i32 val)
}