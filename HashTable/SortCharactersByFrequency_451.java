package HashTable;

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 */
public class SortCharactersByFrequency_451 {
    public static String frequencySortFaster(String s) {
        //统计频率，使用频率进行排序
        int[] freq=new int[128];
        char[] array = s.toCharArray();
        for(char c:array){
            freq[c]++;
        }
        PriorityQueue<Character> pq=new PriorityQueue<>((a,b)->Integer.compare(freq[b],freq[a]));
        for(int i=0;i<freq.length;i++){
            if(freq[i]!=0){
                pq.offer((char)i);
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        while (!pq.isEmpty()){
            Character c = pq.poll();
            while (freq[c]-- > 0){
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
    public static String frequencySort(String s) {
        //写的太麻烦
        if(s==null||s.length()==0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        int begin=0;
        char c=charArray[0];
        int end=0;
        //[start,end)
        for(int i=0;i<charArray.length;i++){
            if(charArray[i]==c){
                end++;
            }else{
                pq.add(new int[]{begin,end-begin});
                end=i+1;
                begin=i;
                c=charArray[i];
            }
        }
        pq.add(new int[]{begin,end-begin});
        StringBuilder stringBuilder=new StringBuilder();
        while (!pq.isEmpty()){
            int[] poll = pq.poll();
            for(int freq=0;freq<poll[1];freq++){
                stringBuilder.append(charArray[poll[0]]);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String tree = frequencySortFaster("tree");
        System.out.println(tree);
    }
}
