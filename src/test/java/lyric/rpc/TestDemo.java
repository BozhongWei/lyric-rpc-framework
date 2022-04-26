package lyric.rpc;

import lyric.rpc.config.RpcServiceConfig;
import lyric.rpc.hello.Hello;
import lyric.rpc.hello.HelloService;
import lyric.rpc.hello.impl.HelloServiceImpl;
import lyric.rpc.net.dto.RpcRequest;
import lyric.rpc.net.transport.RpcRequestTransport;
import lyric.rpc.net.transport.socket.SocketRpcClient;
import lyric.rpc.net.transport.socket.SocketRpcServer;
import lyric.rpc.proxy.RpcClientProxy;
import lyric.rpc.registry.redis.RedisServiceDiscovery;
import lyric.rpc.registry.redis.RedisServiceRegistry;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisServiceRegistry redisServiceRegistry;
    @Autowired
    RedisServiceDiscovery redisServiceDiscovery;
    @Autowired
    SocketRpcServer socketRpcServer;
    @Autowired
    RpcRequestTransport rpcRequestTransport;
    @Test
    public void redisServiceRegistryTest(){
        redisServiceRegistry.registerService("TestDemo1","TestService",new InetSocketAddress("10.23.15.121",8848));
    }
    @Test
    public void redisServiceDiscoveryTest(){
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);
        InetSocketAddress inetSocketAddress = redisServiceDiscovery.lookupService(rpcServiceConfig.getClusterName(), "lyric.rpc.hello.HelloServiceA-Apr-A");
        System.out.println(inetSocketAddress);
    }

    @Test
    public void socketClientMain(){
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String hello = helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
    }
    @Test
    public void socketServerMain(){
        HelloService helloService = new HelloServiceImpl();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(helloService);
        socketRpcServer.registerService(rpcServiceConfig);
        socketRpcServer.start(10000);
    }
}
