package Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 森林中兔子的数量
 *  1.相同颜色的兔子回答是一样的
 *  2.不同颜色的兔子回答不同
 *  将相同回答的分为一组，求出最小兔子数，进行累加
 *  公式： x只兔子回答y,则最小兔子数  x/(y+1)向上取整，再乘以y+1
 *
 *  另一种思路
 *  需要知道，相同颜色的兔子回答是一样的
 *  遍历的时候只算第一次出现的数字然后加1（本身）即可，但是还有另一种情况就是可能有两种颜色兔子拥有相同的
 *  数量，这时对重复进行统计，超出数量后，再遇到相同的数字认为是新的颜色
 */
public class RabbitsinForest_781 {
    public int numRabbits(int[] answers) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int x:answers){
            map.put(x,map.getOrDefault(x,0)+1);
        }
        int ans=0;
        for(Map.Entry<Integer,Integer> item:map.entrySet()){
            int y=item.getKey(),x=item.getValue();
            //(x+y)/(y+1)等价于 x/(y+1)向上取整
            ans+=(x+y)/(y+1)*(y+1);
        }
        return ans;
    }
    public int numRabbitsSecond(int[] answers) {
        Map<Integer,Integer> count=new HashMap<>();
        int ans=0;
        for(int i=0;i<answers.length;i++){
            if(count.containsKey(answers[i])){
                count.put(answers[i],count.get(answers[i])-1);
                if(count.get(answers[i])==0){
                    count.remove(answers[i]);
                }
            }else{
                ans+=answers[i]+1;
                if(answers[i]!=0){
                    count.put(answers[i],answers[i]);
                }
            }
        }
        return ans;
    }
}
