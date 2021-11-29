package il.ac.shenkar.chat;

public class ClientDescriptor implements StringConsumer, StringProducer {

    private MessageBoard mb;


    @Override
    public void consume(String str) {
        mb.consume(str);
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        mb = (MessageBoard) sc;
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        mb.removeConsumer(this);
        mb = null;
    }
}
