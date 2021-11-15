// Ofir Duchovne & Koral Tsaba
package il.ac.shenkar;
import java.io.File;

public class ListFiles {
    public  static void main(String[] args)
    {
        File file = new File(".");
        for (String f : file.list()) {
            System.out.println(f);
        }
    }
}
