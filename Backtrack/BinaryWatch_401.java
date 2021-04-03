package Backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 二进制手表
 * 可归约为组合问题，10个灯里面选n个的方案
 */
public class BinaryWatch_401 {
//    public List<String> readBinaryWatch(int num) {
//        if(num>10) num=10;
//        boolean[] visited=new boolean[10];
//        List<String> res=new LinkedList<>();
//        dfs(visited,num,0,res,0);
//        return res;
//    }
//    private void dfs(boolean[] visited,int num,int k,List<String> res,int start){
//        if(k==num){
//            String s = printSolution(visited);
//            if(s!=null){
//                res.add(s);
//            }
//            //为空表示不符合要求，抛弃
//            return;
//        }
//        for(int i=start;i<visited.length;i++){
//            if(visited.length-start+1<num-k){
//                continue;
//            }
//            visited[i]=true;
//            dfs(visited,num,k+1,res,i+1);
//            visited[i]=false;
//        }
//    }
//
//    private String printSolution(boolean[] visited) {
//        //0-3时钟,4-9分钟
//        int hour=0;
//        for(int i=0;i<4;i++){
//            if(visited[i]){
//                hour+=Math.pow(2,i);
//            }
//        }
//        if(hour>11) return null;
//        int minutes=0;
//        for(int i=4;i<10;i++){
//            if(visited[i]){
//                minutes+=Math.pow(2,i-4);
//            }
//        }
//        if(minutes>59) return null;
//        return String.format("%d:%02d",hour,minutes);
//    }
    //优化版
    public List<String> readBinaryWatch(int num) {
        List<String> res=new LinkedList<>();
        if(num<0) return res;
        int[] led=new int[]{8,4,2,1,32,16,8,4,2,1};
        if(num>10) num=10;
        dfs(led,num,0,res,0,0);
        return res;
    }

    private void dfs(int[] led, int num, int start, List<String> res,int hour,int minute) {
        //终止条件
        if(num==0){
            if(hour>11||minute>59){
                return;
            }
            StringBuilder tmp=new StringBuilder();
            tmp.append(hour).append(':');
            if(minute<10)
                tmp.append('0');
            tmp.append(minute);
            res.add(tmp.toString());
            return;
        }
        for(int i=start;i<led.length;i++){
            if(i<4){
                //小于4，为小时
                hour+=led[i];
            }else{
                minute+=led[i];
            }
            dfs(led, num-1, i+1, res, hour, minute);
            //复原
            if(i<4){
                //小于4，为小时
                hour-=led[i];
            }else{
                minute-=led[i];
            }
        }
    }

    public static void main(String[] args) {
        BinaryWatch_401 watch = new BinaryWatch_401();
        List<String> strings = watch.readBinaryWatch(1);
        strings.forEach(System.out::println);
        System.out.println(strings.size());
    }
}
