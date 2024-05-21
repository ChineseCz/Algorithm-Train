import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoublePoint  {

    //不定长滑动窗口
    public void model1() {
    /* 最长上升
        for (int l = 0; l < n; l++) {
            int r = l;
            // 如果下一个元素大于当前元素，右边界向右移动
            while (r + 1 < n && w[r + 1] > w[r]) {
                r++;
            }
            res = Math.max(res, r - l + 1);
            l = r;
     */


    /* 最大值、最长长度；最小值、最小长度用min
    {
        for(int l=0,r=0;r<n;r++){  // 定义两个指针l,r
        // ...省略代码，需要维护区间相关信息，例如区间和，或者区间某个元素数量
        while (!check()) {  // 当前区间不满足条件
            l++;  // 移动左指针
        }
        res = Math.max(res, cur);  // 更新最大值/最大长度
    }
     */

    /* 方案数
        子数组满足单调性，[l,r]满足，则区间[r,r],[r-1,r],...,[l,r]都是满足条件的
        int n = nums.length, res = 0;  // res为方案数

        for (int l = 0, r = 0; r < n; r++) {   // 定义两个指针l,r
            //维护
            while (!check()) {   // 当前区间不满足条件
                l++;    // 移动左指针
            }
            res += r - l + 1;  // 更新方案数
        }
     */
    }
}
