package il.ac.shenkar;

import java.util.LinkedList;

public class Train {
    private Locomotive locomotive;
    private MyLinkedList<Cabin> cabins;

    public Train(Locomotive locomotive, MyLinkedList<Cabin> cabins) {
        this.locomotive = locomotive;
        this.cabins = cabins;
    }

    public Train() {
        this.locomotive = new Locomotive();
        this.cabins = new MyLinkedList<>();
    }

    /**
     * adds n new cabins to the train
     * @param n - number if cabin to be added
     */
    public void addCabin(int n) {
        for (int i = 0; i < n; i++) {
            this.cabins.add(new Cabin());
        }
    }

    /**
     * adds a cabin to the end of the train
     * @param c - Cavin instance to be added to train
     */
    public void addCabin(Cabin c) {
        this.cabins.add(c);
    }

    /**
     * removes the cabin from index n.
     * Index starts at 0.
     * @param n - index of the cabin to be removed
     * @return - returns the cabin that was removed
     * @throws Exception - throws exception if index is out of range
     */
    public Cabin removeCabin(int n) throws Exception {
        return this.cabins.remove(n);
    }

    /**
     * removes the cabin from the train if it is there.
     * If cabin is not in tarin null is returned.
     * @param c - the instance of the cabin to be removed
     * @return - the cabin that was removed or null if was not found
     */
    public Cabin removeCabin(Cabin c) {

        for(int i = 0; i < this.numberOfCabins(); i++) {
            try {
                if (c == this.cabins.getNthElement(i)) {
                    return this.cabins.remove(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int numberOfCabins() {
        return this.cabins.size();
    }

    public static void main(String[] args) {
        Train myTrain = new Train();
        try {
            myTrain.addCabin(2);
            System.out.println("Size of the train is: " + myTrain.numberOfCabins());
            myTrain.removeCabin(0);
            myTrain.removeCabin(0);
            myTrain.removeCabin(0);
            System.out.println("Size of the train is: " + myTrain.numberOfCabins());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
