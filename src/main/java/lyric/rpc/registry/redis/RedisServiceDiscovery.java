package lyric.rpc.registry.redis;

import lyric.rpc.registry.ServiceDiscovery;
import lyric.rpc.registry.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
@Service
public class RedisServiceDiscovery implements ServiceDiscovery {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public InetSocketAddress lookupService(String groupName, String rpcServiceName) {
        System.out.println("==================================");
        System.out.println(groupName);
        System.out.println(rpcServiceName);
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(groupName);

        return (InetSocketAddress) boundHashOperations.get(rpcServiceName);
    }
}
