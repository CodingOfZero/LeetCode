package Design;

import java.util.Arrays;

public class ImplementHashSet_705 {
    /** Initialize your data structure here. */
    private final boolean[] value;
    public ImplementHashSet_705() {
        value=new boolean[1000001];
    }

    public void add(int key) {
        value[key]=true;
    }

    public void remove(int key) {
        value[key]=false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return value[key];
    }
}
