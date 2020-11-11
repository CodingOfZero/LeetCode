# Greedy算法

1. 是一种编程范式，每次选择能带来直接利润/利益的步骤。它选择的是 "局部最优解"，而不考虑未来的后果。贪婪算法不一定能得到全局最优解。

2. 特点

   - 有一个有序的资源清单，比如利润，成本，价值等；
   - 取所有资源中的最大值。比如在 fractional knapsack 问题，根据可用容量，先取最大（价值/重量）

3. 优点

   - 实现简单
   - 时间复杂度低
   - 可用于优化的目的，或者在NP Hard问题的情况下寻找接近优化的方法

4. 缺点：可能不是全局最优

5. 基本结构

   ```java
   getOptimal(Item, arr[], int n)
     1) Initialize empty result : result = {}  
     2) While (All items are not considered)
   
         // We make a greedy choice to select
         // an item.
         i = SelectAnItem() 
   
         // If i is feasible, add i to the 
         // result
         if (feasible(i))
           result = result U i 
     3) return result
   ```

   