package Design;

import javafx.util.Pair;

import java.util.*;

/**
 * 设计一个LRU最近最少使用缓存
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 注意get操作也会变更次序
 */
public class LRUCache_146 {
    /**
     * 设计一个双向链表，
     */
    class  DLinkedNode{
        int key;
        int value;
        DLinkedNode prev,next;
        DLinkedNode(){}
        DLinkedNode(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    class LRUCache{
        private final int capacity;
        private Map<Integer,DLinkedNode> cache=new HashMap<>();
        private DLinkedNode head,tail;
        private int size;

        LRUCache(int capacity){
            this.size=0;
            this.capacity=capacity;
            head=new DLinkedNode();
            tail=new DLinkedNode();
            head.next=tail;
            head.prev=null;
            tail.prev=head;
            tail.next=null;
        }
        public int get(int key){
            DLinkedNode node = cache.get(key);
            if(node==null){
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }
        public void put(int key,int value){
            DLinkedNode node = cache.get(key);
            //如果不存在
            if(node==null){
                DLinkedNode newNode  = new DLinkedNode(key, value);
                //添加到哈希表中
                cache.put(key,newNode);
                //添加到头部
                addHeadNode(newNode);
                size++;
                //判断是否超过容量
                if(size>capacity){
                    //如果超过删除最后一个
                    DLinkedNode lastNode=removeLastNode();
                    //删除哈希表对应项
                    cache.remove(lastNode.key);
                    size--;
                }
            }else{
                node.value=value;
                moveToHead(node);
            }
        }
        private void addHeadNode(DLinkedNode node){
            node.next=head.next;
            head.next.prev=node;
            head.next=node;
            node.prev=head;
        }
        private DLinkedNode removeLastNode() {
            DLinkedNode removeNode = tail.prev;
            removeNode.prev.next=tail;
            tail.prev=removeNode.prev;
            removeNode.prev=null;
            removeNode.next=null;
            return removeNode;
        }

        private void moveToHead(DLinkedNode node) {
            node.prev.next=node.next;
            node.next.prev=node.prev;
            addHeadNode(node);
        }

    }


    /**
     * 通过继承LinkedHashMap
     */
//    class LRUCache extends LinkedHashMap<Integer,Integer>{
//        private final int capacity;
//        LRUCache(int capacity){
//            super(capacity,0.75F,true);
//            this.capacity=capacity;
//        }
//        public int get(int key){
//            return super.getOrDefault(key,-1);
//        }
//        public void put(int key,int value){
//            super.put(key,value);
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size()>capacity;
//        }
//    }

    /**
     * 方法一：效率低下，700多毫秒
     */
    private final int capacity;
    private LinkedList<MyPair<Integer,Integer>> pageList;

    LRUCache_146(int capacity){
        this.capacity=capacity;
        pageList=new LinkedList<>();
    }
    public int get(int key){
        int res=-1;
        MyPair<Integer,Integer> temp=null;
        for(MyPair<Integer,Integer> item:pageList){
            if(item.getKey()==key){
                res=item.getValue();
                temp=item;
            }
        }
        if(temp!=null){
            MyPair<Integer, Integer> pair = new MyPair<>(temp.getKey(), temp.getValue());
            pageList.remove(temp);
            pageList.addFirst(pair);
        }
        return res;
    }
    public void put(int key,int value){
        MyPair<Integer, Integer> pair = new MyPair<>(key, value);
        if(pageList.size()<capacity){
            if(get(key)!=-1){
                pageList.remove(pair);
            }
        }else{
            if(get(key)!=-1){
                pageList.remove(pair);
            }else{
                pageList.removeLast();
            }
        }
        pageList.addFirst(pair);
    }
    static class MyPair<K,V>{
        private K key;
        private V value;
        MyPair(K key,V value){
            this.key=key;
            this.value=value;
        }
        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyPair<?, ?> myPair = (MyPair<?, ?>) o;
            return key.equals(myPair.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

}
