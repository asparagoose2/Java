package il.ac.shenkar;

/**
 * Generic linked list node class
 * @param <T>
 */
public class ListNode<T> {
    private T data;
    private ListNode<T> next;
    private ListNode<T> previous;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode<T> previous) {
        this.previous = previous;
    }


}
