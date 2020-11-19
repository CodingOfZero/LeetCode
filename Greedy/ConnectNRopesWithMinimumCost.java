package Greedy;

import java.util.PriorityQueue;

/**
 * 以最小的成本连接n条绳索
 * 有n条不同长度的绳索，我们需要将这些绳索连接成一条绳索。连接两条绳索的成本等于其长度之和。
 * 类似于霍夫曼编码，计算最小成本连接绳索其实计算霍夫曼编码生成中间节点权值之和
 * 先连接最小的两根绳索，然后再重复剩余的绳索。这种方法类似于霍夫曼编码。
 * 我们将最小的绳索放在树上，这样它们可以重复多次而不是更长的绳索重复多次。
 */
public class ConnectNRopesWithMinimumCost {
    public static int minimumCost(int[] ropes){
        int len=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int rope:ropes){
            pq.add(rope);
        }
        while (pq.size()>1){
            int min1=pq.poll();
            int min2=pq.poll();
            int temp=min1+min2;
            len+=(temp);
            pq.add(temp);
        }
        return len;
    }
    public static void main(String args[])
    {
        int len[] = { 4, 3, 2, 6 };
        int size = len.length;

        System.out.println("Total cost for connecting ropes is " + minimumCost(len));
    }
}
