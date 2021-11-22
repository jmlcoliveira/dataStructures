package dataStructures;

import java.io.Serializable;

public class DoubleListNode<E> implements Serializable {

    static final long serialVersionUID = 0L;

    // Element stored in the node.
    private E element;

    // (Pointer to) the previous node.
    private DoubleListNode<E> previous;

    // (Pointer to) the next node.
    private DoubleListNode<E> next;

    public DoubleListNode(E theElement, DoubleListNode<E> thePrevious,
                          DoubleListNode<E> theNext) {
        element = theElement;
        previous = thePrevious;
        next = theNext;
    }

    public DoubleListNode(E theElement) {
        this(theElement, null, null);
    }

    public E getElement() {
        return element;
    }

    public void setElement(E newElement) {
        element = newElement;
    }

    public DoubleListNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleListNode<E> newPrevious) {
        previous = newPrevious;
    }

    public DoubleListNode<E> getNext() {
        return next;
    }

    public void setNext(DoubleListNode<E> newNext) {
        next = newNext;
    }
}
