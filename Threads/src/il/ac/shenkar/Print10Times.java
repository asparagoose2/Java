package il.ac.shenkar;

public class Print10Times implements Runnable{
    private String data;

    public Print10Times(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++)
        {
            System.out.println( data
                                + ",current thread is : "
                                + Thread.currentThread().getName());
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e) {}
        }
    }
}
