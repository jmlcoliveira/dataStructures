package dataStructures;

public class OrderedDoubleList<E extends Comparable<E>> extends DoubleList<E> implements OrderedList<E> {

    static final long serialVersionUID = 0L;

    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    /**
     * Returns the node which contains the key or <code>null</code> if key does not exist
     *
     * @param e element
     * @return node containing the key or <code>null</code> if key does not exist
     */
    @Override
    protected DoubleListNode<E> findNode(E e) {
        if (isEmpty()) return null;
        if (e.compareTo(head.getElement()) == 0) return head;
        if (e.compareTo(tail.getElement()) == 0) return tail;
        DoubleListNode<E> node;
        for (node = head.getNext(); node != null && node.getElement().compareTo(e) <= 0; node = node.getNext()) //only search if the key of current node is <= than target key
            if (node.getElement().equals(e))                                                                                      // because list is ordered
                return node;
        return null;
    }

    @Override
    public E add(E e) {
        DoubleListNode<E> node = findNode(e);
        if (node != null) { //replace old value and return the old value
            E oldValue = node.getElement();
            node.setElement(e);
            return oldValue;
        }

        node = new DoubleListNode<>(e, null, null);
        if (isEmpty()) {
            head = node;
            tail = node;
            currentSize++;
            return null;
        }
        // if key is lower than head, insert before head
        if (e.compareTo(head.getElement()) < 0) {
            addFirst(e);
            return null;
        }
        // if key is higher than tail, insert after tail
        if (e.compareTo(tail.getElement()) > 0) {
            addLast(e);
            return null;
        }

        insertMiddle(node);
        return null;
    }

    //Pre-Condition:node is not in the first position nor the last position
    protected void insertMiddle(DoubleListNode<E> node) {
        //find pos to insert
        //nodes are ordered by ascending order of keys
        for (DoubleListNode<E> currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            //insert before the first element which is greater than key
            if (currentNode.getElement().compareTo(node.getElement()) > 0) {
                DoubleListNode<E> prevNode = currentNode.getPrevious();
                prevNode.setNext(node);
                node.setPrevious(prevNode);
                node.setNext(currentNode);
                currentNode.setPrevious(node);
                currentSize++;
                break;
            }
        }
    }

    @Override
    public E removeElement(E e) {
        DoubleListNode<E> node = findNode(e);
        if (node == null) return null;

        if (node == head) {
            removeFirstNode();
        } else if (node == tail) {
            removeLastNode();
        } else
            removeMiddleNode(node);

        return node.getElement();
    }
}
