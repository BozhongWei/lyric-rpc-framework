package lyric.rpc.registry;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {
    /**
     * 服务发现
     * @param rpcServiceName 服务的名称
     * @return 服务的地址
     */
    InetSocketAddress lookupService(String groupName,String rpcServiceName);
}
