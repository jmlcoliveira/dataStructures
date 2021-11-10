import dataStructures.*;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
        Dictionary<Integer, String> l = new SepChainHashTable<>();

        l.insert(5, "6");
        l.insert(4, "5");
        l.insert(2, "3");
        l.insert(1, "2");
        l.insert(6, "7");
        l.insert(0, "1");
        l.insert(3, "4");

        Iterator<Entry<Integer, String>> it = l.iterator();

        while (it.hasNext()){
            Entry<Integer, String> e = it.next();
            System.out.printf("Key: %d; Value: %s\n",e.getKey(), e.getValue());
        }
    }
}
