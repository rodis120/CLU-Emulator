package pl.rogal.cluemulator;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        var ip = InetAddress.getLocalHost();
        int port = 1234;

        String key = "";
        String iv = "";

        CluCipher cipher = new CluCipher(key, iv);

        try {
            CluServer server = new CluServer(ip,  port, cipher, null);
            server.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
