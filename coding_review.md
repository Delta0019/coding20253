# Coding Review

## Special Structure

1. IP 地址
   1. 对于 IP 增量处理，注意使用:
      1. a[3] = (a[3] + 1) % 256
      2. 这在判断两个 IP 是否连续时非常重要

## 三种距离

1. 三种距离定义
    1. 曼哈顿距离 (L~1~)
    2. 欧几里得距离 (L~2~)
    3. 切比雪夫距离 (L~fin~)
2. 曼哈顿距离和切比雪夫距离
   1. Overview
      1. 经过不同的坐标转化，可以将求切比雪夫距离转化为求曼哈顿距离，反之亦然。
   2. 曼哈顿距离
      1. 与前缀和结合使用，可以O(1)求出所有点到某一点的距离总和
      2. 变换公式:
         1. **(x+y,x-y)**
         2. **原坐标系的曼哈顿距离等于新坐标系的切比雪夫距离**
      3. 便于计算出合适的**中心点**
      4. 典型情景
         1. 便于用在k-medians/聚类
         2. 对异常值的敏感度低于L2，可用于鲁棒统计
   3. 切比雪夫距离
      1. 变换公式:
         1. ((x+y)/2,(x-y)/2)
         2. **原坐标系中的切比雪夫距离等于新坐标系中的曼哈顿距离**
      2. 在排序后可以便利地求出两坐标差的**最大值**
      3. 典型情景
         1. 8‑邻接栅格路径规划（带对角移动的国际象棋棋盘）

## Array

1. Array类别
   1. int[]
   2. List (接口)
2. 适用范围
   1. int[]
      1. int[] 是固定空间，适合只需要改变元素大小的情况。如果需要增删，则不得不重新分配一块内容。
      2. 排序使用Arrays.sort(int[], Comparator)
      3. 初始化方式
         1. int[] arr = new int[5]; // 所有元素初始化为 0
         2. int[] arr2 = {1, 2, 3}; // 静态初始化
      4. 转字符串方式：Arrays.sort()
      5. 填充方式：Arrays.fill()
   2. List<>
      1. List<>适合需要对外层元素进行增删的情况，使用add(index, element)可以方便地对首尾进行元素添加，**高频头部操作建议改用LinkedList**
      2. 排序使用Collections.sort(List<>, Comparator)
      3. 初始化方式 (注意：List是接口，需要使用实现类初始化)
         1. **ArrayList<>**：基于数组，适合随机访问，增删慢
         2. **LinkedList<>**：基于链表，适合头尾插入/删除，访问慢
      4. 转字符串方式：list.toString()
      5. 填充方式：Collections.fill()
      6. 遍历时修改
         1. 遍历List时不能同时修改它（例如使用remove），推荐使用迭代器

         ```java

         List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
         Iterator<Integer> iterator = list.iterator();
         while (iterator.hasNext()) {
            int val = iterator.next();
            if (val % 2 == 0) {
               iterator.remove();  // 安全地移除当前元素
            }
         }

         System.out.println(list);  // 输出: [1, 3, 5]
         ```
