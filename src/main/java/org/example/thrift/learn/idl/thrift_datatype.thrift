namespace java com.test.service.thriftdatatype
const string VERSION = "1.0.1"

enum ThriftResult {
    SUCCESS, /* 成功 */
    SERVER_UNWORKING, /* 服务器处于非working状态*/
    NO_CONTENT, /* 请求结果不存在 */
    PARAMETER_ERROR, /* 参数错误 */
    EXCEPTION, /* 内部出现异常 */
    INDEX_ERROR /* 错误的索引或者下标 */
    DATA_NOT_COMPLETE /* 数据不完全 */
    INNER_ERROR /* 内部错误 */
}

struct ResultStr {
    1: ThriftResult result,
    2: string value,
}

struct ResultInt {
    1: ThriftResult result,
    2: i32 value,
}