/**
 * @author Ty Van Heerden
 * @since Oct. 10, 2025
 * @version 1.0
 */

/**
 * Array list implementation
 * implements List interface 
 */
public class ArrayList<T> implements List<T>{

    private T[] arr;
    private int size;

    //constructor referenced from lecture slides
    public ArrayList(){
        this.arr = (T[]) new Object[10];
        this.size = 0;
    }

    /**
     * This represents the grow array function, it increases the size of the arr when necessary
     * grow_array method referenced from slides
     */
    private void grow_array(){
        T[] new_arr = (T[]) new Object[this.arr.length * 3 / 2 + 1];
        for (int i = 0; i < this.arr.length; i++){
            new_arr[i] = this.arr[i];
        }
        this.arr = new_arr;
    }

    /**
     * This represents the size function
     * @return the number of items in the container 
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * This represents the add element at specific index function
     * @param index the index to add the element to
     * @param element the item to add to the container
     * adds an element (param) to container at index (param)
     */
    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index > this.size){
            throw new Exception("Invalid add position");
        }

        if (this.size == this.arr.length){
            grow_array();
        }

        for (int i = this.size; i > index; i--){
            this.arr[i] = this.arr[i - 1];
        }
        this.arr[index] = element;
        ++this.size;
    }

    /**
     * this represents the add element to container function
     * @param element the item to add to the container
     * adds an elemet (param) to container 
     */
    @Override
    public boolean add(T element){
        if (this.size == this.arr.length){
            grow_array();
        }
        this.arr[size++] = element;
        return true;
    } 

    /**
     * this represents the get function
     * @param index the element to get at an index
     * returns the requested element in the container
     */
    @Override
    public T get(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");  
        }
        return this.arr[index];
    }

    /**
     * this represents the remove function
     * @param remove the requested element at index
     * remove and returns the element at index(param)
     */
    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= this.size){
            throw new Exception("Invalid position");
        }
        T temp = this.arr[index];
        for (int i = index; i < this.size - 1; i++){
            this.arr[i] = this.arr[i + 1];
        }
        this.size--;
        return temp;
    }
}
