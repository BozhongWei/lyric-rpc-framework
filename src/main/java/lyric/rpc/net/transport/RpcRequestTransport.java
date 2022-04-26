package lyric.rpc.net.transport;

import lyric.rpc.net.dto.RpcRequest;

import java.io.IOException;

public interface RpcRequestTransport {
    /**
     * 在网络传输层面，我们仅需要关注如何将Rpc请求发送出去，结果会通过传输方式发送回来。
     * 本项目中我们只考虑Socket通信，其他不考虑。
     */
    Object sendRpcRequest(RpcRequest rpcRequest) throws IOException, ClassNotFoundException;
}
