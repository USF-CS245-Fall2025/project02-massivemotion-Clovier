/**
 * @author Ty Van Heerden
 * @since Oct. 11, 2025
 * @version 1.0
 */


 /**
  * Doubly Linked List implementation
  * Implements List interface
  *
  *@param <T> the type of elements that's stored in the list
  */
public class DoublyLinkedList<T> implements List<T>{
    
    /**
     * Static inner class that represents the attributes of a single node in the doubly linked list.
     * Each node stores some type of data and a reference pointer to the next and previous node
     * Code below referenced from lecture slides with modifications made
     */
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

    /**
     * DoublyLinkedList constructor: an empty doubly list with no elements 
     * Head and tail pointer to null and size is set to 0
     */
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Return the number of elements in the list
     * 
     * @return number of elements stored in the list
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Adds node at the the end of the doubly linked list, similar to the traditional add function in singly LinkedList
     * 
     * @param element the item to add to the end of the container
     * @return true if the item has been successfully added to the container
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

    /**
     * Adds an element at a specific index within the doubly linked list
     * 
     * @param index the specific index to add the element
     * @param element the element to add to the list
     */
    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > this.size){
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
            Node<T> current = this.head;
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

    /**
     * Get an element from a specific index
     * 
     * @return the element requested
     */
    @Override
    public T get(int index) throws Exception{
        Node<T> current;

        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");
        }else if (index < size / 2){
            current = this.head;

            for (int i = 0; i < index; i++){
                current = current.next;
            }
        }else{
            current = this.tail;
            for (int i = this.size - 1; i > index; i--){
                current = current.previous;
            }
        }
        return current.data;
    }

    /**
     * Remove an element from a specified index in the list
     * Calls helper methods {@code removeAtFront()} and {@code removeAtEnd()} for code readability
     * 
     * @param index the specified index of the element that wants to be removed
     * @return the element that was removed from that index
     */
    @Override
    public T remove(int index) throws Exception{
        Node<T> current;

        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            return removeAtFront();
        }else if (index == size - 1){
            return removeAtEnd();
        }else if (index < this.size / 2){
            current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            } 
        }else{
            current = this.tail;
            for (int i = this.size - 1; i > index; i++){
                current = current.previous;
            }
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current.next = null;
        current.previous = null;
        --this.size;

        return current.data;
    }

    /**
     * Remove element at front of the list helper method
     * 
     * @return the element that was removed from the front of the list
     */
    private T removeAtFront(){
        Node<T> node = this.head;

        if (this.head == this.tail){
            this.head = null;
            this.tail = null;
            --size;
            return node.data;
        }

        this.head = this.head.next;
        this.head.previous = null;
        node.next = null;
        node.previous = null;
        --this.size;
        return node.data;
    }

    /**
     * Remove element at the end of the list helper method
     * 
     * @return the element that was removed from the end of the list
     */
    private T removeAtEnd(){
        Node<T> node = this.tail;

        if (this.head == this.tail){
            this.head = null;
            this.tail = null;
            --this.size;
            return node.data;
        }

        this.tail = this.tail.previous;
        this.tail.next = null;
        node.next = null;
        node.previous = null;
        --this.size;
        return node.data;
    }
}
