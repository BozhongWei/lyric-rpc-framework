package lyric.rpc.net.transport.socket;

import lombok.extern.slf4j.Slf4j;
import lyric.rpc.exception.RpcException;
import lyric.rpc.net.dto.RpcRequest;
import lyric.rpc.net.transport.RpcRequestTransport;
import lyric.rpc.registry.ServiceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
@Slf4j
@Service
public class SocketRpcClient implements RpcRequestTransport {
    /**
     * 在实现Rpc通信之前，我们要首先进行服务的发现功能，因此需要有服务发现的组件
     */
    @Autowired
    private  ServiceDiscovery serviceDiscovery;

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest){
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getClusterName(),rpcRequest.getRpcServiceName());
        try (Socket socket = new Socket()){
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // Send data to the server through the output stream
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // Read RpcResponse from the input stream
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败",e);
        }
    }
}
