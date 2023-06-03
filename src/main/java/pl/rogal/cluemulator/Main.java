package pl.rogal.cluemulator;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress ip = InetAddress.getByName("localhost");
        int port = 1234;

        String key = "***REMOVED***";
        String iv = "***REMOVED***";

        CluCipher cipher = new CluCipher(key, iv);

        try {
            CluServer server = new CluServer(ip,  port, cipher, new TestProcessor());
            server.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
