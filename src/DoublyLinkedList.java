/**
 * @author Ty Van Heerden
 * @since Oct. 11, 2025
 * @version 1.0
 */

public class DoublyLinkedList<T> implements List<T>{
    
    static class Node<T>{
        T data;
        Node<T> next;
        Node<T> previous;
        
        public Node(T value){
            this.data = value;
            this.next = null;
            this.previous = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    /**
     * this function inserts node at the the end of the list, similar to the traditional add function in singly LinkedList
     * 
     * @param element the item to add to the end of the container
     * @return
     */
    @Override
    public boolean add(T element){
        Node<T> node = new Node<>(element);
        if (this.head == null){
            this.head = node;
            this.tail = node;   
        }else{
            this.tail.next = node;
            node.previous = this.tail;
            this.tail = node;
        }  
        ++this.size;
        return true;
    }


    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > size){
            throw new Exception("Invalid position");
        }

        Node<T> node = new Node<>(element);
        if (index == 0){
            node.next = this.head;
            this.head.previous = node;
            this.head = node;
        }else if (index == size){
            node.previous = this.tail;
            this.tail.next = node;
            this.tail = node;
        }else{
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++){
                current = current.next;
            }
            node.next = current.next;
            node.previous = current;
            current.next.previous = node;
            current.next = node;
        }
        ++this.size;     
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
