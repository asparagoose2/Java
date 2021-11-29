package il.ac.shenkar.chat;


import java.util.ArrayList;

public class MessageBoard implements StringConsumer, StringProducer {

    private ArrayList<StringConsumer> consumers;

    public MessageBoard() {
        consumers = new ArrayList<StringConsumer>();
    }


    @Override
    public void consume(String str) {
        for(StringConsumer consumer: consumers) {
            try {
                consumer.consume(str);
            } catch (Exception e) {
                removeConsumer(consumer);
            }

        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        consumers.add(sc);
        System.out.println("There are " + consumers.size() + " clients!");
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        consumers.remove(sc);

    }
}