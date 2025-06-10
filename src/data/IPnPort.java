package data;

import java.io.Serializable;
import java.net.InetAddress;

public record IPnPort(InetAddress IP, int port) implements Serializable {
}
