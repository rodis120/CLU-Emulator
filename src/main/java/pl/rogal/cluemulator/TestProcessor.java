package pl.rogal.cluemulator;

import java.net.InetAddress;

public class TestProcessor implements MessageProcessor {
    @Override
    public String process(String message, InetAddress address) {
        var parts = message.split(":");
        var id = parts[2];

        StringBuilder sb = new StringBuilder();
        sb.append("req:").append(address.toString()).append(":");
        sb.append(id).append(":\"OK!\"");

        return sb.toString();
    }
}
