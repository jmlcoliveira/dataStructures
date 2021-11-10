import org.junit.Test;
import static org.junit.Assert.*;
import dataStructures.*;

public class Tests {

    List<Number> list;
    Number n0;
    Number n1;
    Number n2;

    public Tests(){
        list = new DoubleList<>();
        n0 = new Number(0);
        n1 = new Number(1);
        n2 = new Number(2);
    }

    @Test
    public void testAddFirst(){
        list.addFirst(n0);
        list.addFirst(n1);
        list.addFirst(n2);
        assertEquals(list.get(0), n2);
        assertEquals(list.get(1), n1);
        assertEquals(list.get(2), n0);
        assertEquals(list.size(), 3);
    }

    @Test
    public void testAddLast(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        assertEquals(list.get(0), n0);
        assertEquals(list.get(1), n1);
        assertEquals(list.get(2), n2);
        assertEquals(list.size(), 3);
    }

    @Test
    public void testRemoveFirst(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        assertEquals(list.removeFirst(), n0);
        assertEquals(list.get(0), n1);
        assertEquals(list.get(1), n2);
        assertEquals(list.size(), 2);
        assertEquals(list.removeFirst(), n1);
        assertEquals(list.get(0), n2);
        assertEquals(list.size(), 1);
        assertEquals(list.removeFirst(), n2);
        assert(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void testRemoveLast(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        assertEquals(list.removeLast(), n2);
        assertEquals(list.get(0), n0);
        assertEquals(list.get(1), n1);
        assertEquals(list.size(), 2);
        assertEquals(list.removeLast(), n1);
        assertEquals(list.get(0), n0);
        assertEquals(list.size(), 1);
        assertEquals(list.removeFirst(), n0);
        assert(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void testIndexOf(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        assertEquals(list.indexOf(n0), 0);
        assertEquals(list.indexOf(n1), 1);
        assertEquals(list.indexOf(n2), 2);
    }

    @Test
    public void testIterator(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        Iterator<Number> it = list.iterator();
        int i = 0;
        while (it.hasNext()){
            assertEquals(it.next().getNumber(), i++);
        }
    }

    @Test
    public void testAppend(){
        list.addLast(n0);
        list.addLast(n1);
        list.addLast(n2);
        DoubleList<Number> list2 = new DoubleList<>();
        Number n4 = new Number(4);
        Number n5 = new Number(5);
        Number n6 = new Number(6);
        list2.addLast(n4);
        list2.addLast(n5);
        list2.addLast(n6);
        ((DoubleList<Number>) list).append(list2);
        assertEquals(list.size(), 6);
        assert(list2.isEmpty());
        assertEquals(list.getFirst(), n0);
        assertEquals(list.get(3), n4);
        assertEquals(list.get(4), n5);
        assertEquals(list.get(5), n6);
    }
}
