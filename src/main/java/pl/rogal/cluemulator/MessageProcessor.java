package pl.rogal.cluemulator;

import java.net.InetAddress;

public interface MessageProcessor {
    String process(String message, InetAddress address);
}
