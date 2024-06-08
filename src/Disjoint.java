/* 传递性
* 合并
* 判断是否同一集合
* 统计集合数量，集合中元素数量
* */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Disjoint {
     int n = 5;
     int[] p,cnt;//p为根节点；cnt为各集合元素数量
     public void merge(int x,int y) {
         int px = find(x),py = find(y);
         if (px != py) {
             cnt[py] += cnt[px];//不能颠倒
             p[px] = py;


         }

     }
     public int find(int x) {
         if (p[x] == x) return x;
         else {
             p[x] = find(p[x]);
             return p[x];
         }
     }
     public void init() {
         p = new int[n+1];
         cnt = new int[n+1];
         for (int i=1;i<=n;i++) {
             p[i] = i;
             cnt[i] = 1;
         }
         merge(1,5);
         merge(1,2);
         // 经过一系列merge操作之后
         List<Integer> res = new ArrayList<>();  // 记录每个集合中的元素数量
         for (int i = 1; i <= n; i++) {
             if (find(i) == i) {   // 找到根节点
                 res.add(cnt[i]);
             }
         }
         System.out.println(res.size());  // 输出集合个数
         for (int x : res) System.out.print(x + " ");  // 输出每个集合中的元素数量

     }

}
class test {
    public static void main(String[] args) {
        new Disjoint().init();
    }
}