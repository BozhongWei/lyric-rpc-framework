package lyric.rpc.net.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    /**
     *
     * 当客户端调用服务端的rpc方法时，客户端需要传一个Request请求给对方，RpcRequest中包含了要调用的目标方法和类的名称，参数等数据。
     * group用于解决一个接口有多个类实现的冲突情况
     */
//    private static final long serialVersionUID = 1905122041950251207L;
    private String clusterName;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;
    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
