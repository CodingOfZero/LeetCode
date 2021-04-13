package Heap;

import java.util.*;

/**
 * 给定一个非空单词列表，返回前k
 */
public class TopKFrequentWords_692 {
    /**
     * 使用最小堆，时间复杂度为O(nlogk)，使用容量为K的堆
     */
    public List<String> topKFrequentHeap(String[] words,int k){
        HashMap<String,Integer> hashMap=new HashMap<>();
        for(String word:words){
            hashMap.put(word,hashMap.getOrDefault(word,0)+1);
        }
        //最小堆,最后反转即可
        PriorityQueue<String> minHeap=new PriorityQueue<String>((s1,s2)->{
            if(hashMap.get(s1).equals(hashMap.get(s2))){
                return s2.compareTo(s1);
            }else{
                return hashMap.get(s1)-hashMap.get(s2);
            }
        });
        for(String word:hashMap.keySet()){
            minHeap.add(word);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        List<String> ans=new ArrayList<>();
        while (!minHeap.isEmpty()){
            ans.add(minHeap.poll());
        }
        Collections.reverse(ans);
        return ans;
    }
    /**
     * 根据官方题解，对以下进行修改
     * 知识点，排序并非一定要将排序字段作为待排序数据的一部分
     */
    public List<String> topKFrequentFirst(String[] words,int k){
        HashMap<String,Integer> hashMap=new HashMap<>();
        for(String word:words){
            hashMap.put(word,hashMap.getOrDefault(word,0)+1);
        }
        List<String> res=new ArrayList<>(hashMap.keySet());
        res.sort((s1,s2)->{
            if(hashMap.get(s1).equals(hashMap.get(s2))){
                //相等，按字符串升序
                return s1.compareTo(s2);
            }else{
                return hashMap.get(s2)-hashMap.get(s1);
            }
        });
        return res.subList(0,k);
    }


    static class Word{
        String word;
        int freq;
        Word(String word,int freq){
            this.word=word;
            this.freq=freq;
        }
    }

    /**
     * 思路：先按照频数进行降序排列，对于相同频数的字符串按照字符串升序进行排序
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> hashMap=new HashMap<>();
        for(String word:words){
            hashMap.put(word,hashMap.getOrDefault(word,0)+1);
        }
        int n=hashMap.size();
        Word[] tmp=new Word[n];
        int index=0;
        for(String s:hashMap.keySet()){
            tmp[index++]=new Word(s, hashMap.get(s));
        }
        Arrays.sort(tmp,(a,b)->{
            if(a.freq!=b.freq){
                return b.freq-a.freq;
            }else{
                return a.word.compareTo(b.word);
            }
        });
        List<String> ans=new LinkedList<>();
        for(int i=0;i<k;i++){
            ans.add(tmp[i].word);
        }
        return ans;
    }

    public static void main(String[] args) {
        TopKFrequentWords_692 test = new TopKFrequentWords_692();
        List<String> strings = test.topKFrequentFirst(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        strings.forEach(System.out::println);
    }
}
