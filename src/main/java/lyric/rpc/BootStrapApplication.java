package lyric.rpc;

import lyric.rpc.config.RpcServiceConfig;
import lyric.rpc.hello.HelloService;
import lyric.rpc.hello.impl.HelloServiceImpl;
import lyric.rpc.net.transport.socket.SocketRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootStrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class,args);
        HelloService helloService = new HelloServiceImpl();
    }
}
