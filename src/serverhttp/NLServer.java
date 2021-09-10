
package serverhttp;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author MarcoMedina
 */
public class NLServer {

    public static void main(String[] args) throws IOException {
        int port = 81;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started ar " + port);
        server.createContext("/", new RootHandler());
        server.createContext("/echoHeader", new EchoHeaderHandler());
        server.createContext("/echoGet", new EchoGetHandler());
        server.createContext("/echoPost", new EchoPostHandler());
        server.createContext("/concentradora", new Concentradora());
        server.setExecutor(null);
        server.start();
    }
    
}
