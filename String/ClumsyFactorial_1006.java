package String;

import java.util.Deque;
import java.util.LinkedList;

public class ClumsyFactorial_1006 {
    public static int clumsy(int N) {
        if(N<=1) return N;
        //'*','/','+','-'
        Deque<Integer> num=new LinkedList<>();
        num.push(N);
        N--;
        int i=0;
        while(N>0){
            if(i%4==0){
                num.push(num.pop()*N);
            }else if(i%4==1){
                num.push(num.pop()/N);
            }else if(i%4==2){
                num.push(N);
            }else{
                num.push(-N);
            }
            i++;
            N--;
        }
        int res=0;
        while (!num.isEmpty()){
            res+=num.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int clumsy = clumsy(10);
        System.out.println(clumsy);
    }
}
