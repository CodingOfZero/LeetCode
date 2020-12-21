package Stack_Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 *
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，
 * 那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 * 单调递减栈，遇到大的出栈
 */
public class StockSpanner_901 {
    private int[] stack; //模拟栈
    private int[] weight;//离上一个价格之间（即最近的一个大于它的价格之间）的天数
    private int sp;
    public StockSpanner_901(){
        stack=new int[100000];
        weight=new int[100000];
        sp=-1;
    }
    public int next(int price) {
        int w=1;
        while (sp!=-1&&price>=stack[sp]){
            //出栈并将栈顶对应的距离上次价格的天数进行累加
            w+=weight[sp--];
        }
        stack[++sp]=price;
        weight[sp]=w;
        return w;
    }

    /**
     * 方法一
     */
//    private ArrayList<Integer> list;
//    public StockSpanner_901(){
//        list=new ArrayList<>();
//    }
//    public int next(int price) {
//        list.add(price);
//        int res=0;
//        for(int i=list.size()-1;i>=0;i--){
//            if(list.get(i)>price){
//                break;
//            }
//            res++;
//        }
//        return res;
//    }
}
