package lyric.rpc.registry;

import java.net.InetSocketAddress;

public interface ServiceRegistry {
    /**
     * 注册服务到注册中心
     * @param rpcServiceName 服务的名称
     * @param inetSocketAddress 服务的地址
     */
    void registerService(String groupName, String rpcServiceName, InetSocketAddress inetSocketAddress);
}
