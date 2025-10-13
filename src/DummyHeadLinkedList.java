/**
 * @author Ty Van Heerden
 * @since Oct. 12, 2025
 * @version 1.0
 */

public class DummyHeadLinkedList<T> implements List<T>{
    private class Node<T>{
        T data;
        Node<T> next; 

        public Node(T value){
            data = value;
            next = null;
        }
    }

    Node<T> head;
    int size;

    public DummyHeadLinkedList(){
        head = new Node(null);
        size = 0;
    }
}
