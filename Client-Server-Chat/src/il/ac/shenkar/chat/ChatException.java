package il.ac.shenkar.chat;

public class ChatException extends Exception{
    public ChatException(String s) {
        super(s);
    }

    public ChatException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
