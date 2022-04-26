package lyric.rpc.net.dto;

public class RpcMessage {
    /**
     * rpc 消息类型
     */
    private byte messageType;
    /**
     * request id
     */
    private int requestId;
    /**
     * request data
     */
    private Object data;
}