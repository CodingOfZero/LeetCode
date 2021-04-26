package Design;

import java.util.HashMap;
import java.util.Map;

/**
 * 最不常用页面置换
 * 根据某个页面的访问次数
 * 利用最小堆和哈希表
 * 各个操作时间复杂度为logN,受制于最小堆的时间复杂度
 */
public class LFUCache_460 {




    /**
     * 最不常用页面置换
     * 根据某个页面的访问次数
     * 利用最小堆和哈希表
     * 各个操作时间复杂度为logN,受制于最小堆的时间复杂度，实现简单
     */
    /*
    class Node implements Comparable<Node>{
        int key;
        int value;
        int freq;
        int idx;//表示到达次序
        Node(){}
        public Node(int key,int value,int idx){
            this.key=key;
            this.value=value;
            freq=1;
            this.idx=idx;
        }

        @Override
        public int compareTo(Node o) {
            if(freq!=o.freq){
                return freq-o.freq;
            }else{
                return idx-o.idx;
            }
        }


    }
    private int capacity;
    private int size;
    private Map<Integer,Node> cache;
    private PriorityQueue<Node> minHeap;
    private int idx;
    public LFUCache_460(int capacity) {
        cache=new HashMap<>(capacity);
        minHeap=new PriorityQueue<>(capacity);
        this.capacity=capacity;
        this.size=0;
        this.idx=0;
    }

    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }
        Node node = cache.get(key);
        minHeap.remove(node);
        node.freq++;
        node.idx=idx++;
        minHeap.add(node);
        return node.value;
    }
    public void put(int key, int value) {
        if(capacity==0){
            return;
        }
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            minHeap.remove(node);
            node.value=value;
            node.freq++;
            node.idx=idx++;
            minHeap.add(node);
        }else{
            if(size==capacity){
                Node poll = minHeap.poll();
                cache.remove(poll.key);
                this.size--;
            }
            Node node = new Node(key, value, idx++);
            cache.put(key,node);
            minHeap.add(node);
            this.size++;
        }
    }


    */

    /**
     * 自定义双向链表，使用HashMap记录每个频率对应的链表，另一个HashMap记录关键字key对应的Node节点
     * 其中链表使用头插法插入节点，当两个频率相等要去除最早进入的节点时，直接删除最后一个节点即可满足条件
     */
    class Node{
        int key;
        int value;
        int freq;
        Node pre;
        Node post;
        Node(){}
        Node(int key,int value){
            this.key=key;
            this.value=value;
            this.freq=1;
        }
    }
    class DoubleLinkedList{
        Node head;
        Node tail;
        DoubleLinkedList(){
            head=new Node();
            tail=new Node();
            head.post =tail;
            tail.pre=head;
        }
        void removeNode(Node node){
            node.pre.post=node.post;
            node.post.pre=node.pre;
        }
        void addNode(Node node){
            node.post=head.post;
            node.post.pre=node;
            head.post=node;
            node.pre=head;
        }
    }
    //<key,node>
    private Map<Integer,Node> cache;
    //<freq,doubleLinkedList>
    private Map<Integer,DoubleLinkedList> freqHashMap;
    private int capacity;
    private int size;
    private int minFreq;
    LFUCache_460(int capacity){
        cache=new HashMap<>();
        freqHashMap=new HashMap<>();
        this.capacity=capacity;
        size=0;
    }

    public int get(int key){
        Node node = cache.get(key);
        if(node==null){
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    public void put(int key,int value){
        if(capacity==0){
            return;
        }
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.value=value;
            freqInc(node);
        }else{
            if(size==capacity){
                DoubleLinkedList removeList = freqHashMap.get(minFreq);
                Node node=removeList.tail.pre;
                removeList.removeNode(node);
                cache.remove(node.key);
                size--;
            }
            Node node = new Node(key, value);
            cache.put(key,node);
            minFreq=1;
            DoubleLinkedList doubleLinkedList = freqHashMap.get(minFreq);
            if(doubleLinkedList==null){
                doubleLinkedList=new DoubleLinkedList();
                freqHashMap.put(minFreq,doubleLinkedList);
            }
            doubleLinkedList.addNode(node);
            size++;
        }
    }

    private void freqInc(Node node){
        //移除
        int freq=node.freq;
        DoubleLinkedList doubleLinkedList = freqHashMap.get(freq);
        doubleLinkedList.removeNode(node);
        //当最小频率对应的链表为空时，更新最小频率
        if(freq==minFreq&&doubleLinkedList.head.post==doubleLinkedList.tail){
            minFreq=freq+1;
        }
        //加入新链表
        node.freq++;
        DoubleLinkedList store = freqHashMap.get(node.freq);
        if(store==null){
            store=new DoubleLinkedList();
            freqHashMap.put(node.freq, store);
        }
        store.addNode(node);
    }

    public static void main(String[] args) {
        LFUCache_460 test = new LFUCache_460(2);
        test.put(1,1);
        test.put(2,2);
        test.get(1);
        test.put(3,3);
        test.get(2);
        test.get(3);
        test.put(4,4);
        test.get(1);

    }
}
