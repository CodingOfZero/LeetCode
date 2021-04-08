package HashTable;

import java.util.HashMap;

public class MinimumWindowSubstring_76 {
    public String minWindow(String s, String t) {
        //滑着走
        int sLen=s.length();
        int tLen=t.length();
        if(sLen<tLen) return "";

        HashMap<Character,Integer> sHashMap=new HashMap<>();
        HashMap<Character,Integer> tHashMap=new HashMap<>();
        for(int i=0;i<tLen;i++){
            tHashMap.put(t.charAt(i),tHashMap.getOrDefault(t.charAt(i),0)+1);
        }
        //滑动窗口
        int left=0,right=0;
        int minLen=sLen+1;
        int begin=-1;
        int len=0;
        while(right<sLen){
            //扩展
            if(tHashMap.containsKey(s.charAt(right))){
                char c=s.charAt(right);
                if(!sHashMap.containsKey(c)){
                    sHashMap.put(c,1);
                    len++;
                }else{
                    if(sHashMap.get(c) < tHashMap.get(c)){
                        len++;
                    }
                    sHashMap.put(c,sHashMap.get(c)+1);
                }
            }
            right++;
            //收缩
            while(len==tLen){
                //判断是否需要更新
                if(right-left<minLen){
                    begin=left;
                    minLen=right-left;
                }
                if(!tHashMap.containsKey(s.charAt(left))){
                    left++;
                    continue;
                }
                char c=s.charAt(left);
                if(sHashMap.get(c).equals(tHashMap.get(c))){
                    len--;
                }
                sHashMap.put(c,sHashMap.get(c)-1);
                left++;
            }
        }

        if(minLen==(sLen+1)){
            return "";
        }else{
            return s.substring(begin,begin+minLen);
        }
    }

    public static void main(String[] args) {
        MinimumWindowSubstring_76 test = new MinimumWindowSubstring_76();
        String s = test.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}
