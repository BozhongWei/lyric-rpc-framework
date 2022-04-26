package lyric.rpc.config;

import lombok.*;

/**
 * @author shuang.kou
 * @createTime 2020年07月21日 20:23:00
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcServiceConfig {
    /**
     * 集群名称
     */
    private String clusterName = "lyric-first";
    /**
     * service version
     */

    private String version = "Apr-A";
    /**
     * when the interface has multiple implementation classes, distinguish by group
     */
    private String group = "A-";

    /**
     * target service
     */
    private Object service;

    public String getRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }

    public String getServiceName() {
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }
}
