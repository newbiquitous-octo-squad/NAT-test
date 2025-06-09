package messages;

public class Hello extends Message {
    public String hello;
    public Hello(String hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return hello;
    }
}