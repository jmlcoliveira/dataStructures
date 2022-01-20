package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.Serializable;

/**
 * Iterator Abstract Data Type
 * Includes description of general methods for one way iterator.
 *
 * @param <E> Generic Element
 * @author AED  Team
 * @version 1.0
 */
public interface Iterator<E> extends Serializable {

    /**
     * Returns true if next would return an element
     * rather than throwing an exception.
     *
     * @return true iff the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     */
    E next() throws NoSuchElementException;

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty,
     * next will return the first element in the iteration.
     */
    void rewind();

}
