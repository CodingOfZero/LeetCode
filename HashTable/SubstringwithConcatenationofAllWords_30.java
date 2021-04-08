package HashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SubstringwithConcatenationofAllWords_30 {
    //超内存了
//    public List<Integer> findSubstring(String s, String[] words) {
//        if(words==null||words.length==0) return new LinkedList<>();
//        int len=0;
//        for(String word:words){
//            len+=word.length();
//        }
//        if(len>s.length()) return new LinkedList<>();
//        Arrays.sort(words);
//        List<String> res=new LinkedList<>();
//        StringBuilder sb=new StringBuilder();
//        boolean[] visited=new boolean[words.length];
//        helper(words,res,sb,visited,len);
//        List<Integer> ans=new LinkedList<>();
//        for(String item:res){
//            int i=s.indexOf(item);
//            if(i==-1){
//                continue;
//            }
//            ans.add(i);
//            for(int j=i+1;j<s.length();j++){
//                if((s.length()-j)>=item.length()){
//                    if(s.startsWith(item, j)){
//                        ans.add(j);
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//
//    private void helper(String[] words, List<String> res, StringBuilder sb, boolean[] visited,int len) {
//        if(sb.length()==len){
//            res.add(sb.toString());
//            return;
//        }
//        for(int i=0;i<words.length;i++){
//            if(!visited[i]){
//                if(i>0&&words[i].equals(words[i-1])&&!visited[i-1]){
//                    continue;
//                }
//                visited[i]=true;
//                int k=sb.length();
//                sb.append(words[i]);
//                helper(words, res, sb, visited,len);
//                sb.delete(k,sb.length());
//                visited[i]=false;
//            }
//        }
//    }

    /**
     * 单词长度是相同的,利用两个HashMap，第一个存放words中字符串的统计，第二个用于统计在S串中某个子串是否与words中一样，如果是表明
     * 该子串是结果中的一个
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if(words==null||words.length==0) return new LinkedList<>();
        int wordLen=words[0].length();
        //如果单词长度比S都长，那么在S中一定不存在words组成的字符串
        int wordNum=words.length;
        if(wordLen*wordNum>s.length()) return new LinkedList<>();
        HashMap<String,Integer> wordCount=new HashMap<>();
        //统计每个字符串出现的频率
        for(String word:words){
            wordCount.put(word,wordCount.getOrDefault(word,0)+1);
        }
        List<Integer> res=new LinkedList<>();
        //类似于字符串匹配的暴力迭代
        for(int i=0;i<s.length()-wordLen*wordNum+1;i++){
            HashMap<String,Integer> tmp=new HashMap<>();
            int num=0;
            while (num<wordNum){
                String substring = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                if(wordCount.containsKey(substring)){
                    tmp.put(substring, tmp.getOrDefault(substring, 0) + 1);
                    if(tmp.get(substring)>wordCount.get(substring)){
                        break;
                    }else{
                        num++;
                    }
                }else{
                    break;
                }
            }
            if(num==wordNum){
                res.add(i);
            }
        }

        return res;
    }
    public static void main(String[] args) {
        SubstringwithConcatenationofAllWords_30 test = new SubstringwithConcatenationofAllWords_30();
        List<Integer> li = test.findSubstring("foobarfoobar", new String[]{"foo","bar"});
        li.forEach(System.out::println);
    }

}
