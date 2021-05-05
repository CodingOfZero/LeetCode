package Graph;


import java.util.*;

/**
 * 八数码问题  平台Vijos
 */
public class EightDigital_Vijos_1360 {

    private int[][] nextPos=new int[][]{
            {-1,-1,3,1},{-1,0,4,2},{-1,1,5,-1},
            {0,-1,6,4},{1,3,7,5},{2,4,8,-1},
            {3,-1,-1,7},{4,6,-1,8},{5,7,-1,-1}
    };
    private final static String END_STATE ="123804765";
//    private final static String END_STATE ="123456780";
    class StateNode implements Comparable<StateNode>{
        String state;
        int zeroPos;
        int step;
        int cost;
        StateNode(){}
        StateNode(String state,int zeroPos,int step){
            this.state=state;
            this.zeroPos=zeroPos;
            this.step=step;
            setCost();
            //setCostByOuLaDistance();
        }
        //以“不在位”作为启发函数
        private void setCost(){
            for(int i=0;i<state.length();i++){
                if(state.charAt(i)!= END_STATE.charAt(i)){
                    this.cost++;
                }
            }
            this.cost+=this.step;
        }
        //以欧拉距离作为启发函数
        private void setCostByOuLaDistance(){

            for(int i=0;i<END_STATE.length();i++){
                int j=0;
                //求出与目标状态对应字符所在的下标
                for(;j<state.length();j++){
                    if(END_STATE.charAt(i)==state.charAt(j)){
                        break;
                    }
                }
                //欧拉：根号下 (x1-x2)^2+(y1-y2)^2
                this.cost+=(int)Math.sqrt(Math.pow((double)(i/3-j/3),2)+Math.pow(i%3-j%3,2));
            }
            this.cost+=this.step;
        }

        @Override
        public int compareTo(StateNode node) {
            return this.cost- node.cost;
        }
    }

    /**
     * 访问集合，存储已访问的状态
     */
    private Set<String> visited=new HashSet<>();
    /**
     * 以f(n)的大小作为排序准则
     */
    private PriorityQueue<StateNode> minHeap=new PriorityQueue<>();

    public int bfs(String startState){
        //找到初始空格位置
        int index=0;
        for(;index<startState.length();index++){
            if(startState.charAt(index)=='0'){
                break;
            }
        }
        StateNode node = new StateNode(startState, index, 0);
        minHeap.add(node);
        while (!minHeap.isEmpty()){
            StateNode minNode = minHeap.poll();
            visited.add(minNode.state);
            if(END_STATE.equals(minNode.state)){
                return minNode.step;
            }
            //上下左右四个方向
            for(int i=0;i<4;i++){
                //找到空格可到达的位置
                int pos=nextPos[minNode.zeroPos][i];
                if(pos!=-1){
                    //生成新的状态
                    StringBuilder sb=new StringBuilder();
                    for(int j=0;j<minNode.state.length();j++){
                        if(j== minNode.zeroPos){
                            sb.append(minNode.state.charAt(pos));
                        }else if(j==pos){
                            sb.append(minNode.state.charAt(minNode.zeroPos));
                        }else{
                            sb.append(minNode.state.charAt(j));
                        }
                    }
                    String nextState=sb.toString();
                    //判断是否到达最终状态
                    if(END_STATE.equals(nextState)){
                        return minNode.step+1;
                    }
                    //如果该状态没有访问过，将其入队
                    if(!visited.contains(nextState)){
                        StateNode nextNode = new StateNode(nextState, pos, minNode.step + 1);
                        minHeap.add(nextNode);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        //输入：216408753， 输出：18
        String input=in.nextLine();
        EightDigital_Vijos_1360 test = new EightDigital_Vijos_1360();
        long start = System.currentTimeMillis();

        int minCost = test.bfs(input);
        System.out.println(minCost);
        long end = System.currentTimeMillis();

        System.out.println(end-start+"ms");

    }
}
