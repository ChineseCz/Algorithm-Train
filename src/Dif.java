import java.util.Scanner;

/*
   已知an数组，差分实际是前缀和的逆运算
   ai = ∑pj (j from 1 to i)
   pi = ai -ai-1 (p0 = a0)
   类比级数，级数展开 与 求和 互为逆
 */
class Dif {
    int[] dif;//差分数组，△d = xn - xn-1
    int[] nums;
    int n,m;
    int goal;
    public void add(int l,int r,int c) {
        dif[l]+=c;
        dif[r+1]-=c;
    }
    public void test(Scanner scan) {
        n = scan.nextInt();
        m = scan.nextInt();
        nums = new int[n + 1];
        dif = new int[n + 1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
        for (int i=1;i<=n;i++) {
            dif[i] = nums[i] - nums[i-1]; //nums[0]实际不存在（值0），即dif[1] = nums[1];
        }
        //区间修改
        for (int i=1;i<=m;i++) {
            int l,r,c;
            l = scan.nextInt();
            r = scan.nextInt();
            c = scan.nextInt();
            add(l,r,c);
        }
        //更新nums数组，后续查询O(1)
        for (int i=1;i<=n;i++) {
            nums[i] = nums[i-1] + dif[i];
        }
//        for (int i=1;i<=n;i++) {
//            dif[i] += dif[i-1];//此时dif[i]为nums[i];
//            System.out.println(dif[i]);
//        }
        System.out.println(nums[goal]);
    }
}
