/**
 * @author Ty Van Heerden
 * @since Oct. 10, 2025
 * @version 1.0
 */


 /**
  * Singly linked list implementation implementing generic List interface
  *
  *@param <T> the type of elements that's stored in the list
  */
public class LinkedList<T> implements List<T>{
    
    /**
     * Static inner class that represents the attributes of a single node in the linked list.
     * Each node stores some type of data and a reference pointer to the next node
     * Code below referenced from lecture slides with modifications made
     */
    static class Node<T>{
        T data;
        Node<T> next; 

        /**
         * Node constructor containng a specific value
         * 
         * @param value the value to be stored in this node
         */
        public Node(T value){
            this.data = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    /**
     * LinkedList constructor: an empty Linkedlist with no current elements
     * Head pointer to null and size is 0
     */
    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list
     * 
     * @return number of elements stored in the list
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Get an element from a specified index
     * 
     * @param index the position of the element to get
     * @return the element at that specified index
     * @throws Exception if the index is out of bounds
     */
    @Override
    public T get(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Index out of bounds");
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * Appends an element to the end of the list 
     * 
     * @param element the element to be added to the list
     * @return true if the element has been added to the list 
     */
    @Override
    public boolean add(T element){
        
        if (this.head == null){
            head = new Node<>(element);
            ++size;
            return true;
        }

        Node<T> node = this.head;
        while(node.next != null){
            node = node.next;
        }
        Node<T> last = new Node<>(element);
        node.next = last;
        ++this.size;
        return true;
    }

    /**
     * Add an element at a specified index within the list
     * 
     * @param index the position of which to add the new element at
     * @param element the element of which to add to the list
     * @throws Exception if the index is out of bounds
     */
    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > this.size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            Node<T> node = new Node<>(element);
            node.next = this.head;
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
        ++this.size;
    }

    /**
     * Removes an element at a specified index within the list
     * 
     * @param index the position of which to remove the element
     * @return the element that was removed from the list
     * @throws Exception if the index is out of bounds
     */
    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");
        }

        if (index == 0){
            Node<T> node = this.head;
            this.head = this.head.next;
            --this.size;
            return node.data;
        }
        
        Node<T> previous = this.head;
        for (int i = 0; i < index - 1; i++){
            previous = previous.next;
        }
        Node<T> node = previous.next;
        previous.next = node.next;
        --this.size;
        return node.data;
    }
}
