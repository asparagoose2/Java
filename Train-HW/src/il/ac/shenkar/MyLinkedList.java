package il.ac.shenkar;
/**
* Generic Lined List class
 */

public class MyLinkedList<T> {
    ListNode<T> head;
    ListNode<T> tail;
    int numberOfNodes;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        numberOfNodes = 0;
    }


    /**
     * adds a node to the end of the list
     * @param t - element to be added to the list
     */
    public void add(T t) {
        ListNode<T> toInsert = new ListNode<>(t);
        if(numberOfNodes == 0) {
            this.head = this.tail = toInsert;
        } else {
            toInsert.setPrevious(this.tail);
            this.tail.setNext(toInsert);
            this.tail = toInsert;
        }
        this.numberOfNodes++;
    }

    /**
     * returns the Nth Node.
     * index starts at 0.
     * @param n - index of the node
     * @return the node
     * @throws Exception - throws exception if index is out of range
     */
    private ListNode<T> getNthNode(int n) throws Exception {
        if(n >= this.numberOfNodes) {
            throw new Exception("index out of range");
        }
        ListNode<T> node = head;
        for (int i = 0; i  < n; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * removes the n-th element from the list and returns it.
     * Index statrts from 0
     * @param n - undex of the element
     * @return - the removed element
     */
     public T remove(int n) throws Exception {
         if (this.numberOfNodes == 0) {
             return null;
         }
         ListNode<T> node;
         if (n == 0) {
             node = this.head;
             this.head = this.head.getNext();

         } else {
             node = getNthNode(n);
             node.getPrevious().setNext(node.getNext());
         }
        this.numberOfNodes--;
        return node.getData();
     }

    /**
     * returns the nth element
     * @param n - indeex of the element
     * @return - the element
     * @throws Exception - throws exception if index is out of range
     */
     public T getNthElement(int n) throws Exception {
         return this.getNthNode(n).getData();
     }

     public int getNumberOfNodes() {
         return this.numberOfNodes;
     }

     public int size() {
         return getNumberOfNodes();
     }

}
