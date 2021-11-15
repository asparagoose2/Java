package il.ac.shenkar;

public class ThreadEx {
    public static void main(String[] args) {
        Thread t1 ,t2;
        Print10Times a = new Print10Times("Life, the Universe, and Everything");
        Print10Times b = new Print10Times("is 42");
        t1 = new Thread(a);
        t2 = new Thread(b);
        t1.start();
        t2.start();
    }
}
