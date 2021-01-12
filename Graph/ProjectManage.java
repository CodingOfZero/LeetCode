package Graph;

import java.util.*;

/**
 * 有n个项目m个项目组，项目之间有依赖，安排一下项目的顺序，除此之外，同一个项目组的项目要依次排列
 * 组与组之间拓扑排序
 * 组内任务之间拓扑排序
 *
 */
public class ProjectManage {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //用于表示小组负责的项目
        List<List<Integer>> groupItem=new ArrayList<>();
        //m+n是因为要对无人接手的任务的组id重新进行编号，从m开始,防止与已有小组发生重合
        for(int i=0;i<m+n;i++){
            groupItem.add(new ArrayList<>());
        }
        //组间,项目依赖图
        List<List<Integer>> groupGraph = new ArrayList<>();
        for(int i=0;i<n+m;i++){
            groupGraph.add(new ArrayList<>());
        }
        List<List<Integer>> itemGraph = new ArrayList<>();
        for(int i=0;i<n;i++){
            itemGraph.add(new ArrayList<>());
        }
        //组间入度，组内入度
        int[] groupDegree=new int[n+m];
        int[] itemDegree=new int[n];

        //便于编码，将无人接手的任务的组id重新进行编号，从m开始,最多为2m因为m<=n;
        int leftId=m;
        for(int item=0;item<n;item++){
            if(group[item]==-1){
                group[item]=leftId++;
            }
            //记录小组对应的项目
            groupItem.get(group[item]).add(item);
        }
        //构造依赖图
        for(int item=0;item<n;item++){
            //当前项目对应的小组
            int curGroup=group[item];
            //获取当前项目的前置项目
            for(int beforeItem:beforeItems.get(item)){
                //获取前置项目对应的小组
                int beforeGroup=group[beforeItem];
                //如果是同一个组，构造项目依赖图，否则组间依赖图
                if(beforeGroup==curGroup){
                    itemGraph.get(beforeItem).add(item);
                    itemDegree[item]++;
                }else{
                    groupGraph.get(beforeGroup).add(curGroup);
                    groupDegree[curGroup]++;
                }
            }
        }
        //组的顶点
        List<Integer> ids=new ArrayList<>();
        for(int id=0;id<n+m;id++){
            ids.add(id);
        }
        List<Integer> res=new ArrayList<>();
        //拓扑排序
        List<Integer> groupSort = topologicalSort(ids, groupDegree, groupGraph);
        if(groupSort.size()==0) return new int[0];
        for(int groupId:groupSort){
            List<Integer> items = groupItem.get(groupId);
            if(items.size()==0) continue;
            List<Integer> itemSort = topologicalSort(items, itemDegree, itemGraph);
            if(itemSort.size()==0) return new int[0];
            res.addAll(itemSort);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private List<Integer> topologicalSort(List<Integer> item,int[] inDegree,List<List<Integer>> graph){
        Queue<Integer> queue=new LinkedList<>();
        for(int i:item){
            if(inDegree[i]==0){
                queue.offer(i);
            }
        }
        List<Integer> result=new ArrayList<>();
        while (!queue.isEmpty()){
            int poll = queue.poll();
            result.add(poll);
            for(int adj:graph.get(poll)){
                if(--inDegree[adj]==0){
                    queue.offer(adj);
                }
            }
        }
        if(result.size()!=item.size()){
            return new ArrayList<>();
        }else{
            return result;
        }
    }

    public static void main(String[] args) {
        ProjectManage projectManage = new ProjectManage();
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(new ArrayList<>());
        }
        list.get(0).add(2);
        list.get(0).add(1);
        list.get(0).add(3);
        list.get(1).add(2);
        list.get(1).add(4);
        int[] ints = projectManage.sortItems(5, 5, new int[]{2, 0, -1, 3, 0}, list);
        System.out.println(ints);
    }
}
