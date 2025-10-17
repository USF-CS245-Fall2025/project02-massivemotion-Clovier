/**
 * @author Ty Van Heerden
 * @since Oct. 12, 2025
 * @version 1.0
 */

 /**
  * DummyHead Linked List class representing a singly linked list that uses a dummy head node to take advantage of the simiplicity in its' add and remove methods (no edge cases needed).
  * 
  *@param <T> the type of elements that's stored in the list
  */
public class DummyHeadLinkedList<T> implements List<T>{

    /**
     * Referenced Linked List implementation 
     */
    static class Node<T>{
        T data;
        Node<T> next; 

        public Node(T value){
            this.data = value;
            this.next = null;
        }
    }

    /**The dummy head node that's placed at the front of the list */
    final Node<T> dummyHead;
    private int size;

    /**
     * Constructs an empty dummy head linked list
     * Initialize dummy head node to null and set size to 0
     */
    public DummyHeadLinkedList(){
        this.dummyHead = new Node<>(null);
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list
     * 
     * @return the number of elements in the list
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Adds an element to the end of the list
     * 
     * @param element the element to be added to the list
     * @return true if the element has sucessfully been added to the list
     */
    @Override
    public boolean add(T element){
        Node<T> node = this.dummyHead;
        while (node.next != null){
            node = node.next;
        }
        Node<T> last = new Node<>(element);
        node.next = last;
        ++this.size;
        return true;
    }

    /**
     * Adds an element to a specific position in the list
     * 
     * @param index the index of which to add the element to
     * @param element the element to be added to the specific index position
     */
    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > this.size){
            throw new Exception("Index out of bounds");
        }
        Node<T> node = new Node<>(element);
        Node<T> previous = this.dummyHead;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        node.next = previous.next;
        previous.next = node;
        ++this.size;
    }

    /**
     * Remove an element at a specific index position
     * 
     * @param index the index of which the element is to be removed
     * @return the element that was removed from the list
     */
    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Invalid remove position");
        }

        Node<T> previous = this.dummyHead;
        for (int i = 0; i < index; i++){
            previous = previous.next;
        }
        Node<T> node = previous.next;
        previous.next = node.next;
        --this.size;
        return node.data;
    }

    /**
     * Gets the element at the specified index
     * 
     * @param index the index of which the element is stored
     * @return the requested element
     */
    @Override
    public T get(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");
        }

        Node<T> current = this.dummyHead.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }
}
