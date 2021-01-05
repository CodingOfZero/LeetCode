package Stack_Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个字符串
 * 所有包含大于或等于三个连续字符的分组为 较大分组
 * 输出较大分组的区间下标
 */
public class PositionsLargeGroups_803 {
    public List<List<Integer>> largeGroupPositions(String s) {
        char[] charArray=s.toCharArray();
        List<List<Integer>> res=new LinkedList<>();
        int i=0;
        int j=0;
        while(i<charArray.length){
            for(j=i+1;j<=charArray.length;j++){
                if(j==charArray.length||charArray[i]!=charArray[j]){
                    if((j-i)>2){
                        List<Integer> list=new LinkedList<>();
                        list.add(i);
                        list.add(j-1);
                        res.add(list);
                    }
                    i=j;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new PositionsLargeGroups_803().largeGroupPositions("abbxxxxzzy");
        for(List<Integer> list:res){
            System.out.println(list.get(0)+"  "+list.get(1));
        }
    }
}
