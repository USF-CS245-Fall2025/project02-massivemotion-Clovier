/**
 * @author Ty Van Heerden
 * @since Oct. 10, 2025
 * @version 1.0
 */

public class LinkedList<T> implements List<T>{
    
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

    public LinkedList(){
        head = null;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean add(T element){
        
        if (head == null){
            head = new Node<>(element);
            ++size;
            return true;
        }

        Node<T> node = head;
        while(node.next != null){
            node = node.next;
        }
        Node<T> last = new Node<>(element);
        node.next = last;
        ++size;
        return true;
    }

    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            Node<T> node = new Node<>(element);
            node.next = head;
            head = node;
        }else{
            Node<T> node = new Node<>(element);
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++){
                previous = previous.next;
            }
            node.next = previous.next;
            previous.next = node;
        }
        ++size;
    }

    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            Node<T> node = head;
            head = head.next;
            --size;
            return node.data;
        }
        
        Node<T> previous = head;
        for (int i = 0; i < index - 1; i++){
            previous = previous.next;
        }
        Node<T> node = previous.next;
        previous.next = node.next;
        --size;
        return node.data;
    }
    
    
}
