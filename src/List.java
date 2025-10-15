/**
 * @author Ty Van Heerden/project provided interface with modifications made
 * @since Oct 14. 2025
 * @version 1.0
 */

/**
 * Generic List interface 
 * 
 * @param <T> the type of elements that will be stored in this list
 */
public interface List<T> {

    public void add (int index, T element) throws Exception;
    public boolean add (T element);
    public T get (int index) throws Exception;
    public T remove (int index) throws Exception;
    public int size ();
}
