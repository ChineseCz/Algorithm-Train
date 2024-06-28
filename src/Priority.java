/* 优先队列支持以下几种主要操作：

1. 插入（Insert）：将一个新的元素插入到优先队列中，并赋予它一个优先级。对应的时间复杂度为$$O(logn)$$
2. 删除并返回优先级最高的元素（DeleteMin / DeleteMax）：删除并返回队列中优先级最高的元素。如果是最小优先队列（Min-Heap），则返回优先级最小的元素；如果是最大优先队列（Max-Heap），则返回优先级最大的元素。对应的时间复杂度为$$O(logn)$$
3. 查看优先级最高的元素（FindMin / FindMax）：返回队列中优先级最高的元素，但不删除它。对应的时间复杂度为$$O(1)$$

 */
import java.util.Collections;
import java.util.PriorityQueue;

public class Priority {
    public static void main(String[] args) {
        // 定义大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");  // 依次弹出堆顶元素
        }
        System.out.println();

        // 定义小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");  // 依次弹出堆顶元素
        }
    }
}
