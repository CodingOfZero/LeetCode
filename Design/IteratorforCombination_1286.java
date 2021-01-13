package Design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个Iterator类，它有
 * 一个构造函数，接受一个由不同的小写英文字母组成的字符串和一个数字组合长度combinationLength作为参数。
 * 一个函数next()，按照字母顺序返回下一个长度为combinationLength的组合。
 * 一个函数hasNext()，如果且仅当存在下一个组合时返回True。
 * 思路：用一个队列保存所有结果，该结果为组合数
 */
public class IteratorforCombination_1286 {
    /**
     * 生成法，仅根据当前的组合就可以快速地得到下一个组合，而不需要提前将所有的组合存储在数据结构中。
     */
    //存放组合字符下标
    private int[] pos;
    private boolean finished;
    private char[] str;
    IteratorforCombination_1286(String characters, int combinationLength){
        finished=false;
        pos=new int[combinationLength];
        str=characters.toCharArray();
        //第一个组合就是字符串的前combinationLength个字符
        for(int i=0;i<combinationLength;i++){
            pos[i]=i;
        }
    }
    public String next() {
        StringBuilder stringBuilder=new StringBuilder();
        //1.构造结果
        for(int index:pos){
            stringBuilder.append(str[index]);
        }
        //2.寻找下一次组合
        int flag=-1;
        //对于组合中的第 i个位置，它的最大值是字符串中的第 len - k + i 个字母；len-(k-i);
        //从后往前查看,判断是否还有字符没有到达最大位置处
        for(int i=pos.length-1;i>=0;i--){
            //不等表明还未到达最大位置处
            if(pos[i]!=str.length-pos.length+i){
                flag=i;
                break;
            }
        }
        //为-1表明后续没有组合了
        if(flag==-1){
            finished=true;
        }else{
            //更新组合位置信息，为下次调用next返回结果做准备
            pos[flag]++;
            //后续位置参数均为前面更新后的位置参数的后一位
            for(int j=flag+1;j<pos.length;j++){
                pos[j]=pos[j-1]+1;
            }
        }
        return stringBuilder.toString();
    }

    public boolean hasNext() {
        return !finished;
    }

    /**
     * 思路：用一个队列保存所有结果，该结果为组合数
     */
//    private Queue<String> queue;
//
//    public IteratorforCombination_1286(String characters, int combinationLength) {
//        queue=new LinkedList<>();
//        combiantion(characters,0,"",combinationLength);
//    }
//
//    private void combiantion(String characters, int start, String s, int k) {
//        if(k==0){
//            queue.add(s);
//            return;
//        }
//        for(int i=start;i<characters.length();i++){
//            combiantion(characters,i+1,s+characters.charAt(i),k-1);
//        }
//    }
//
//    public String next() {
//        return queue.poll();
//    }
//
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }
}
