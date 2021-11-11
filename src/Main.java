import dataStructures.*;
import dataStructures.Dictionary;
import dataStructures.Iterator;

public class Main {

    public static void main(String[] args) {
        Dictionary<String, String> l = new SepChainHashTable<>(14);

        l.insert("6", "6");
        l.insert("6", "5");
        l.insert("6", "3");
        l.insert("6", "2");
        l.insert("6", "7");
        l.insert("6", "1");
        l.insert("6", "4");
        l.insert("6", "6");
        l.insert("6", "5");
        l.insert("6", "3");
        l.insert("6", "2");
        l.insert("6", "7");
        l.insert("6", "1");
        l.insert("6", "4");

        Iterator<Entry<String, String>> it = l.iterator();

        while (it.hasNext()){
            Entry<String, String> e = it.next();
            System.out.printf("Key: %s; Value: %s\n",e.getKey(), e.getValue());
        }
    }
}
