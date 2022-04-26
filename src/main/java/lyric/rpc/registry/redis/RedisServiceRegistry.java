
package lyric.rpc.registry.redis;

import lyric.rpc.registry.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
@Service
public class RedisServiceRegistry implements ServiceRegistry {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void registerService(String groupName, String rpcServiceName, InetSocketAddress inetSocketAddress) {
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(groupName);
        /**
         * TO-DO:
         * 将Hash中的value做一个类封装起来，用来存放多组数据。
         */
        boundHashOperations.put(rpcServiceName,inetSocketAddress);
    }
}
