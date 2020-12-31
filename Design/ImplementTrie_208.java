package Design;

import java.util.ArrayList;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */

public class ImplementTrie_208 {
     static class TrieNode{
        private boolean isEnd;
        private TrieNode[] links;

        TrieNode(){
             int r = 26;
             isEnd=false;
             links=new TrieNode[r];
        }
        public boolean containKey(char ch){
            return links[ch-'a']!=null;
        }
        public TrieNode get(char ch){
            return links[ch-'a'];
        }
        public void put(char ch,TrieNode node){
            links[ch-'a']=node;
        }
        public boolean isEnd(){
            return isEnd;
        }
        public void setEnd(){
            isEnd=true;
        }
    }
    /** Initialize your data structure here. */
    private TrieNode root;
    public ImplementTrie_208() {
        root=new TrieNode();
    }

    /** Inserts a word into the trie.
     *  时间空间均为O(m)
     *  */
    public void insert(String word) {
        char[] array = word.toCharArray();
        TrieNode node=root;
        for(char ch:array){
            //不存在，新建一个结点
            if(!node.containKey(ch)){
                node.put(ch,new TrieNode());
            }
            //获取结点
            node=node.get(ch);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node!=null&&node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix)!=null;
    }

    private TrieNode searchPrefix(String prefix){
        TrieNode node=root;
        char[] array = prefix.toCharArray();
        for(char ch:array){
            if(!node.containKey(ch)){
                return null;
            }else{
                node=node.get(ch);
            }
        }
        return node;
    }

    public static void main(String[] args) {
        ImplementTrie_208 trie = new ImplementTrie_208();
        trie.insert("apple");
        boolean apple = trie.search("apple");
        System.out.println(apple);
    }
}
