/**
 * @author Ty Van Heerden
 * @since Oct. 11, 2025
 * @version 1.0
 */

public class DoublyLinkedList<T> implements List<T>{
    
    private class Node<T>{
        T data;
        Node<T> next;
        Node<T> previous;
        
        public Node(T value){
            data = value;
            next = null;
            previous = null;
        }
    }

    Node<T> head;
    Node<T> tail;
    int size;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean add(T element){
        return addAtEnd(element);
    }

    /**
     * reference https://www.geeksforgeeks.org/dsa/introduction-to-doubly-linked-lists-in-java/)
     * this function inserts node at the the end of the list, similar to the traditional add function in singly LinkedList
     * @param element the item to add to the end of the container
     * @return
     */
    private boolean addAtEnd(T element){
        Node<T> node = new Node<>(element);
        if (head == null){
            head = node;
            tail = node;
            ++size;
            return true;    
        }
        tail.next = node;
        node.previous = tail;
        tail = node; 
        ++size;

        return true;
    }

    private boolean addAtFront(T element){
        Node<T> node = new Node<>(element);
        if (head == null){
            head = node;
            tail = node;
            ++size;
            return true;
        }
        head.previous = node;
        node.next = head;
        head = node;
        ++size;

        return true;
    }

    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            addAtFront(element);
        }else if (index == size){
            addAtEnd(element);
        }else{
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++){
                current = current.next;
            }
            Node<T> node = new Node<>(element);
            node.next = current.next;
            node.previous = current;
            current.next.previous = node;
            current.next = node;
            ++size;
        }     
    }

    @Override
    public T get(int index) throws Exception{
        Node<T> current;

        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }else if (index < size / 2){
            current = head;

            for (int i = 0; i < index; i++){
                current = current.next;
            }
        }else{
            current = tail;
            for (int i = size - 1; i > index; i--){
                current = current.previous;
            }
        }
        return current.data;
    }

    @Override
    public T remove(int index) throws Exception{
        Node<T> current;

        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            return removeAtFront();
        }else if (index == size - 1){
            return removeAtEnd();
        } 

        if (index < size / 2){
            current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            } 
        }else{
            current = tail;
            for (int i = size - 1; i > index; i++){
                current = current.previous;
            }
        }
        
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current.next = null;
        current.previous = null;
        --size;

        return current.data;
    }

    private T removeAtFront(){
        if (head == null && tail == null){
            return null;
        }

        Node<T> node = head;

        if (head == tail){
            head = null;
            tail = null;
            --size;
            return node.data;
        }

        head = head.next;
        head.previous = null;
        node.next = null;
        --size;
        return node.data;
    }

    private T removeAtEnd(){
        if (head == null && tail == null){
            return null;
        }

        Node<T> node = tail;

        if (head == tail){
            head = null;
            tail = null;
            --size;
            return node.data;
        }

        tail = tail.previous;
        tail.next = null;
        node.previous = null;
        --size;
        return node.data;
    }
}
