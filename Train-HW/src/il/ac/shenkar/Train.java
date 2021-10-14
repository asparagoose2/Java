package il.ac.shenkar;

import java.util.LinkedList;

public class Train {
    private Locomotive locomotive;
    private LinkedList<Cabin> cabins;

    public Train(Locomotive locomotive, LinkedList<Cabin> cabins) {
        this.locomotive = locomotive;
        this.cabins = cabins;
    }

    public Train() {
        this.locomotive = new Locomotive();
        this.cabins = new LinkedList<Cabin>();
    }

    public void addCabin(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            if(!this.cabins.add(new Cabin())) {
                throw new Exception("Unable to add cabin to train!");
            }
        }
    }

    public int numberOfCabins() {
        return this.cabins.size();
    }

    public static void main(String[] args) {
        Train myTrain = new Train();
        try {
            myTrain.addCabin(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Size of the train is: " + myTrain.numberOfCabins());
    }
}
