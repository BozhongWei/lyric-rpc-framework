package lyric.rpc.net.transport.socket;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import lyric.rpc.config.CustomShutdownHook
import lyric.rpc.config.RpcServiceConfig;
import lyric.rpc.net.dto.Message;
import lyric.rpc.provider.ServiceProvider;
import lyric.rpc.provider.impl.RedisServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
@Setter
public class SocketRpcServer {
    private final ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor(10, 50, 60,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100));

    private ServiceProvider serviceProvider;

    @Autowired
    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void registerService(RpcServiceConfig rpcServiceConfig){serviceProvider.publishService(rpcServiceConfig);}

    public void start(int port){
        try(ServerSocket serverSocket = new ServerSocket()){
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host,port));
            //启动服务应该将之前注册的地址删掉重新设置TO-DO
//            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket;
            while((socket = serverSocket.accept())!=null){
                log.info("client connected[{}]",socket.getInetAddress());
//                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                /**
                    拿到socket，创建线程处理请求
                 */
                threadPoolExecutor.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            log.error("发现IO错误",e);
        }
    }

}


