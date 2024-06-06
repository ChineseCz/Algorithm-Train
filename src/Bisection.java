/* 二分查找
* 数组满足单调性(非递减)，二分不断缩小区间
* - 数组有序——全局 or 局部
* - 元素排列顺序对答案的影响？——无影响，考虑排序 + 二分
* - 时间复杂度：操作一次，将n缩小为n/2，
*            设共需k次操作，n*(1/2)^k = 1 -> k = log2(n)
* */


public class Bisection {
    //找到数组中第一个>=target的位置
    
}

/* 二分答案
    整体单调性
    min Max 取中时，要取中左
    max Min 取中时，要取中右
    理解二分的过程，不断枚举缩小合适的范围，直至区间变为点
 */
class BiAns {
    public void minMax() {
        int l = 0,r = 0;
        while (l < r) {
            int mid = (l + r) / 2;//取中左侧
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
    }
    public void maxMin() {
        int l = 0,r = 0;
        while (l < r) {
            int mid = (l + r +1 ) / 2;//取中右侧
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
    }
    public boolean check(int num) {
        return true;
    }
}