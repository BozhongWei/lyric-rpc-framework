package lyric.rpc.net.transport.socket;

import lombok.extern.slf4j.Slf4j;
import lyric.rpc.factory.SingletonFactory;
import lyric.rpc.handler.RpcRequestHandler;
import lyric.rpc.net.dto.RpcRequest;
import lyric.rpc.net.dto.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author shuang.kou
 * @createTime 2020年05月10日 09:18:00
 */
@Slf4j
public class SocketRpcRequestHandlerRunnable implements Runnable {
    private final Socket socket;
    private final RpcRequestHandler rpcRequestHandler;


    public SocketRpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void run() {
        log.info("server handle message from client by thread: [{}]", Thread.currentThread().getName());
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            /**
             * 先通过InputStream拿到请求，然后将请求处理之后通过socket返回
             */
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            /**
             * 通过rpcRequestHandler处理
             */
            Object result = rpcRequestHandler.handle(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            log.error("occur exception:", e);
        }
    }
}
