package Design;

import java.util.*;

public class ImplementHashMap_706 {
    private class Pair{
        private int key;
        private int value;
        Pair(int key,int value){
            this.key=key;
            this.value=value;
        }
        public int getKey(){return key;}
        public int getValue(){return value;}
        public void setValue(int value){this.value=value;}
    }
    private final int BASE=997;
    private List<Pair>[] data;
    private int hash(int key){
        return key&BASE;
    }
    public ImplementHashMap_706() {
        data=new List[BASE];
        for(int i=0;i<BASE;i++){
            data[i]=new LinkedList<>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h=hash(key);
        for (Pair pair : data[h]) {
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].add(new Pair(key,value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h=hash(key);
        for (Pair pair : data[h]) {
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h=hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey()==key){
                iterator.remove();
                return;
            }
        }
    }
}
