import data.Connection;
import data.IPnPort;
import messages.Hello;
import messages.ListGiven;
import messages.ListPlease;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static void main(String[] args) throws Exception {
        Connection data = new Connection(new Socket("129.80.150.205", 12345));
        data.out.writeObject(new ListPlease());

        for (IPnPort datum : ((ListGiven) data.in.readObject()).data) {
            Connection connection = new Connection(new Socket(datum.IP(), datum.port()));
            Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        connection.out.writeObject(new Hello("HI THERE"));
                    } catch (IOException e) {
                        System.out.println("eye oh no (in timer)");
                        e.printStackTrace();
                    }
                }
            }, 10, 100);
        }

    }
}

class Listener implements Runnable {
    Connection connection;
    public Listener(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(connection.in.readObject().toString());
            } catch (Exception e) {
                System.out.println("uhh oh got exception reading message");
                e.printStackTrace();
            }
        }
    }
}