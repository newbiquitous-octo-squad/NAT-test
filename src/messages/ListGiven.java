package messages;

import data.Connection;
import data.IPnPort;

import java.util.List;

public class ListGiven extends Message {
    public IPnPort[] data;
    public ListGiven(List<Connection> list) {
            this.data = list.stream().map(connection -> new IPnPort(connection.socket.getInetAddress(), connection.socket.getPort())).toArray(IPnPort[]::new);
    }
}
