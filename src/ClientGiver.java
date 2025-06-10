import data.Connection;
import messages.ListGiven;
import messages.ListPlease;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientGiver {
    public static void main(String[] args) {
        List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
        try (ServerSocket s = new ServerSocket(12345)) {
            while (true) {
                Connection d = new Connection(s.accept());
                connections.add(d);
                new Thread(() -> {
                    while (true) {
                        try {
                            switch (d.in.readObject()) {
                                case ListPlease listPlease -> d.out.writeObject(new ListGiven(connections));
                                default -> System.out.println("read unknown object");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("uh oh");
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("eye oh!");
            e.printStackTrace();
        }
    }
}

