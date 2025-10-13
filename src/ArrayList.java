/**
 * @author Ty Van Heerden
 * @since Oct. 10, 2025
 * @version 1.0
 */

/**
 * This class represents ArrayList and its' functions discussed in class 
 * implements List interface 
 */
public class ArrayList<T> implements List<T>{

    T arr[];
    int size;

    /**
     * This represents the grow array function
     * it increases the size of the arr when necessary
     * how it works is that it creates a temp size array by x2
     * copies all the elements into the temp array 
     * set a new reference from arr to new_arr
     */
    protected void grow_array(){
        T[] new_arr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }

    /**
     * This represents the size function
     * @return the number of items in the container 
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * This represents the add element at specific index function
     * @param index the index to add the element to
     * @param element the item to add to the container
     * adds an element (param) to container at index (param)
     */
    @Override
    public void add(int index, T element) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }

        if (size == arr.length - 1){
            grow_array();
        }

        for (int i = size; i > index; i--){
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        ++size;
    }

    /**
     * this represents the add element to container function
     * @param element the item to add to the container
     * adds an elemet (param) to container 
     */
    @Override
    public boolean add(T element){
        if (size == arr.length - 1){
            grow_array();
        }
        arr[size++] = element;
        return true;
    } 

    /**
     * this represents the get function
     * @param index the element to get at an index
     * returns the requested element in the container
     */
    @Override
    public T get(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Invalid position");  
        }
        return arr[index];
    }

    /**
     * this represents the remove function
     * @param remove the requested element at index
     * remove and returns the element at index(param)
     */
    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("Invalid position");
        }
        T temp = arr[index];
        for (int i = index; i < arr.length - 1; i++){
            arr[i] = arr[i + 1];
        }
        size--;
        return temp;
    }

    
}
