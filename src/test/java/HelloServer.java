import lyric.rpc.net.transport.socket.SocketRpcServer;

public class HelloServer {
    public static void main(String[] args) {
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        socketRpcServer.start(9988);
    }
}
