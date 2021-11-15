package il.ac.shenkar;

public class FractionException extends Exception {
    public FractionException(String s) {
        super(s);
    }

    public FractionException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
