package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 *  每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，
 *  前面 正好 有 ki 个身高大于或等于 hi 的人。请你重新构造并返回输入数组people 所表示的队列。
 * 返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * 思路：
 *      身高高的人看不到身高低的人，所以先升序
 *      先按hi即身高来升序排列，对于相同身高的将 ki当作第二关键字降序排序
 *      对于 ki而言，它的位置应当位于第  i+1个空位置
 */
public class QueueReconstructionByHeight_406 {
    public static int[][] reconstructQueue(int[][] people) {
        //第一关键字升序，第二关键字降序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }else{
                    return o2[1]-o1[1];
                }
            }
        });
        int[][] res=new int[people.length][];
        //对于 ki而言，它的位置应当位于第  i+1个空位置
        for (int[] person : people) {
            int dis = person[1] + 1;
            for (int j = 0; j < people.length; j++) {
                if (res[j] == null) {
                    dis--;
                    if (dis == 0) {
                        res[j] = person;
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] people=new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        reconstructQueue(people);
        System.out.println("");
    }
}
