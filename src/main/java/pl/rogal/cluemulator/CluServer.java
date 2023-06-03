package pl.rogal.cluemulator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CluServer {

    private final CluCipher cipher;
    private final MessageProcessor processor;
    private DatagramSocket socket;

    public CluServer(InetAddress ip, int port, CluCipher cipher, MessageProcessor processor) throws SocketException {
        this.cipher = cipher;
        this.processor = processor;

        this.socket = new DatagramSocket(port, ip);
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    byte[] decrypted = this.cipher.decrypt(Arrays.copyOf(packet.getData(), packet.getLength()));

                    String response = processor.process(new String(decrypted, StandardCharsets.UTF_8));

                    byte[] encrypted = this.cipher.encrypt(response.getBytes(StandardCharsets.UTF_8));

                    packet = new DatagramPacket(encrypted, encrypted.length, packet.getAddress(), packet.getPort());
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
