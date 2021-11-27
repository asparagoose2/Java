package il.ac.shenkar.chat;


import java.util.ArrayList;

public class MessageBoard implements StringConsumer, StringProducer {

    private ArrayList<ConnectionProxy> consumers;

    public MessageBoard() {
        consumers = new ArrayList<ConnectionProxy>();
    }


    @Override
    public void consume(String str) {
        for(StringConsumer consumer: consumers) {
            System.out.println("send!");
            consumer.consume(str);
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        consumers.add((ConnectionProxy) sc);
        System.out.println("There are " + consumers.size() + " clients!");
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        consumers.remove((ConnectionProxy) sc);
    }
}