package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}

public class EmployeeImportance_690 {

    public int getImportance(List<Employee> employees, int id) {
       //BFS
        int value=0;
        HashMap<Integer,Employee> hashMap=new HashMap<>();
        for(Employee e:employees){
            hashMap.put(e.id,e);
        }

        Queue<Employee> queue=new LinkedList<>();
        queue.add(hashMap.get(id));
        while (!queue.isEmpty()){
            Employee item = queue.poll();
            value+=item.importance;
            for(int sub:item.subordinates){
                queue.add(hashMap.get(sub));
            }
        }
        return value;
    }
}
