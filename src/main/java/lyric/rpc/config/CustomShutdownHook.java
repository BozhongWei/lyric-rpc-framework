package lyric.rpc.config;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
//@Slf4j
//public class CustomShutdownHook {
//    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();
//
//    public static CustomShutdownHook getCustomShutdownHook() {
//        return CUSTOM_SHUTDOWN_HOOK;
//    }
//
//    public void clearAll() {
//        log.info("addShutdownHook for clearAll");
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try {
//                InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 9988);
////                CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
//            } catch (UnknownHostException ignored) {
//            }
//            ThreadPoolFactoryUtil.shutDownAllThreadPool();
//        }));
//    }
//}
