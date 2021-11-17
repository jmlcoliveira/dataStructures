package dataStructures;

import dataStructures.exceptions.EmptyListException;

/**
 * Ordered Dictionary interface
 *
 * @author AED team
 * @version 1.0
 */
public interface OrderedList<E extends Comparable<E>> {

    /**
     * Returns the entry with the smallest key in the dictionary.
     *
     * @return entry with the smallest key in the dictionary.
     * @throws EmptyListException if dictionary is empty
     */
    E getFirst() throws EmptyListException;

    /**
     * Returns the entry with the largest key in the dictionary.
     *
     * @return entry with the largest key in the dictionary.
     * @throws EmptyListException if dictionary is empty
     */
    E getLast() throws EmptyListException;

    /**
     * Returns an iterator of the elements in the list (in proper sequence).
     *
     * @return Iterator of the elements in the list
     */
    Iterator<E> iterator();

    /* (non-Javadoc)
     * Returns an iterator of the entries in the dictionary
     * which preserves the key order relation.
     *
     * @see dataStructures.Dictionary#iterator()
     */
    // Iterator<Entry<K,V>> iterator( );  

    E removeElement(E e);

    E add(E e);
} 

