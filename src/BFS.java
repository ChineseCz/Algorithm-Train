import java.util.*;

/*
BFS的实现步骤
1. 初始化队列：首先，将起始节点放入队列中。
2. 标记节点：为了防止重复访问节点，需要一个数据结构（例如哈希表或布尔数组）来标记已经访问过的节点。
3. 迭代访问：从队列的头部取出一个节点，访问该节点所有未被访问过的邻居节点，并将这些邻居节点加入队列。
4. 重复过程：重复上述步骤，直到队列为空，表示所有可以访问的节点都已经访问过
 */
public class BFS {
    List<List<Integer>> graph;
    int start;
    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println("Visited node: " + node);  // 处理当前节点

            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}