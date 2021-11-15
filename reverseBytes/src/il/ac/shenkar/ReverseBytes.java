package il.ac.shenkar;

import java.io.*;

public class ReverseBytes {
    public static void reverseBytes(String src, String dest) throws IOException {
        FileOutputStream destination = new FileOutputStream(dest);
        RandomAccessFile source = new RandomAccessFile(src, "r");
        for(long p = source.length() - 1; p >= 0; p--) {
            source.seek(p);
            int b = source.read();
            destination.write(b);
        }
    }
    public static void main(String[] args) throws IOException {
        reverseBytes("test.txt", "bla.txt");
    }
}
