import java.util.*;

public class Main {
    public static void main(String[] args) {
        new AkP1022().input();

    }
}

abstract class Problem {
    Scanner scan = new Scanner(System.in);
    int n,m,q,k,T;
    int[] nums;
    long ans;
    public abstract void input();

    public abstract void output();
    public void numsInput() {
        n = scan.nextInt();
        nums = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
    }

}
class AkP1022 extends Problem {
    int sco[];
    @Override
    public void input() {
        m = scan.nextInt();
        n = scan.nextInt();
        sco = new int[m+1];//不能开多一位，因为会把最后的0排到前面
        nums = new int[n+1];
        for (int i=0;i<m;i++)
            sco[i]  = scan.nextInt();
        for (int i=0;i<n;i++)
            nums[i] = scan.nextInt();
        output();
    }

    @Override
    public void output() {
        Arrays.sort(sco);
        for (int i=0;i<n;i++) {
            ans += bisection(nums[i]);
        }
        System.out.println(ans);
    }
    public int bisection(int target) {
        int l=0;
        int r=m-1;
        while (l < r) {
            int mid = (l + r)/2;
            if (sco[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        int res;
//        System.out.println(sco[l]);

        if ( sco[l] >= target )
            if (l!=0)
                res = Math.min(target - sco[l-1],sco[l] - target);
            else
                res = sco[l] - target;
        else
            res = target - sco[l];
//        System.out.println(res);
        return res;
    }
}
class AkP1020 extends Problem {
    int target;
    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n+1];
        target = scan.nextInt();
        for (int i=0;i<n;i++)
            nums[i] = scan.nextInt();
        output();
    }

    @Override
    public void output() {
        int l = 0,r = n-1;
        while (l<r) {
            int mid = (l+r)/2;
            if (nums[mid] > target)
                r = mid ;
            else
                l = mid + 1;
        }
        /* 找到第一个大于target的位置，为什么这样写不行呢，因为数组非递减！*/
        if (l == 0) System.out.println(-1);
        else if (nums[l-1] == target) System.out.println(l-1);
        else System.out.println(-1);
    }
}
class AkP1018 extends Problem {
    int target;
    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n+1];
        target = scan.nextInt();
        for (int i=0;i<n;i++)
            nums[i] = scan.nextInt();
        output();
    }

    @Override
    public void output() {
        int l = 0,r = n-1;
        while (l<r) {
            int mid = (l+r)/2;
//            System.out.println(mid);
            if (nums[mid] >= target)
                r = mid ;
            else
                l = mid + 1;
            /* 为什么不能这样写？
                1. 因为（l+r)/2 是向下取整，总会靠左，当nums[l]不是target，而nums[r]是target时会死循环
                2. 逻辑看似对，实际不对，不存在=时，大于的数被排除了，如 0 2 3，target为1
            if (nums[mid] > target )
                r = mid - 1;
            else (nums[mid] <= target)
                l = mid;
                代码随想录是直接找target，按区间的两种模板，右端是否闭合，l都是mid+1，本质因为向下取整
             */

        }
        if (nums[l] >= target) System.out.println(l);
        else System.out.println(-1);
    }
}
class AkP1015 extends Problem {
    long[] dif;
    long[] sum;
    long[] nums;
    public void add(int l,int r,int c) {
        dif[l] += c;
        dif[r+1] -= c;
    }
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        q = scan.nextInt();
        nums = new long[n+2];
        dif = new long[n+2];
        sum = new long[n+2];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextLong();
            dif[i] = nums[i] - nums[i - 1];
        }
        for (int i=0;i<m;i++) {
            int l,r,c;
            l = scan.nextInt();
            r = scan.nextInt();
            c = scan.nextInt();
            add(l,r,c);
        }
        for (int i=1;i<=n;i++) {
            nums[i] = dif[i] + nums[i-1];
            sum[i] = nums[i] + sum[i-1];
        }
        for (int i=0;i<q;i++) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            System.out.println(sum[r]-sum[l-1]);
        }
    }

    @Override
    public void output() {

    }
}
class Acw2041 extends Problem {
    int[] dif;
    public void add(int l,int r) {
        dif[l]+=1;
        dif[r+1]-=1;
    }
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        dif = new int[n+2];
        nums = new int[n+1];
        for (int i=0;i<k;i++) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            add(l,r);
        }
        output();
    }

    @Override
    public void output() {
        for (int i=1;i<=n;i++) {
            nums[i] = nums[i-1] + dif[i];
        }
        Arrays.sort(nums);
        System.out.println(nums[n/2 + 1]);
    }
}
class AkP1013 extends Problem {
    int[] dif = new int[1000002];
    public void add(int l,int r) {
        dif[l] += 1;
        dif[r+1] -= 1;
    }
    @Override
    public void input() {
        n = scan.nextInt();
        int left = Integer.MAX_VALUE,right=0;
        nums = new int[1000002];//每个点监考的数量；
        //如果nums作为收益，那么dif每次计算时，需要知道nums[i]的值，那需要求和，复杂度进而n^2
        for (int i=0;i<n;i++) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            left = Math.min(left,l);
            right = Math.max(right,r);
            add(l,r);
        }
        for (int i=left;i<=right;i++) {
            nums[i] = nums[i-1] + dif[i];
            if (nums[i] == 0) ans++;
            else if(nums[i] == 1) ans+=3;
            else ans+=4;
        }
        System.out.println(ans);
    }

    @Override
    public void output() {

    }
}
class AkP1012 extends Problem {
    int[] dif;
    public void add(int l,int r,int c) {
        dif[l]+=c;
        dif[r+1]-=c;
    }
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n+1];
        dif = new int[n+2];//add()中涉及dif[n+1]，实际上并不需要，但减少写边界判断，且不影响，因为区间只考虑到n
        /* dif为nums的差分 */
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
            dif[i] = nums[i] - nums[i - 1];
        }
        for (int i=1;i<=k;i++) {
            int l,r,c;
            l = scan.nextInt();
            r = scan.nextInt();
            c = scan.nextInt();
            add(l,r,c);
        }
        output();
    }

    @Override
    public void output() {
        ans = Integer.MAX_VALUE;
        /* 由nums为dif的前缀和，更新nums */
        for (int i=1;i<=n;i++) {
            nums[i] = nums[i-1] + dif[i];
            ans = Math.min(nums[i],ans);
        }
        System.out.println(ans);
    }
}
/* 该题前后缀 + 枚举 最灵活；本题不是三元组类型
    要有区间思想，划分为三部分[0,i-1],[i,i+1],[i+2,n-1]
    前后缀记录 [i,n-1]，[0，i-1]
* */
class AkP1011 extends Problem {
    int[] rmin,rmax;//[i,n-1]
    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n];
        rmin = new int[n + 1];//开大一位，方便后续不用多写初始化rmin[n-1] = nums[n-1];
        rmax = new int[n + 1];
        for (int i=0;i<n;i++) {
            nums[i] = scan.nextInt();
        }
        Arrays.fill(rmin,Integer.MAX_VALUE);
        for (int i=n-1;i>=0;i--) {
            rmin[i] = Math.min(rmin[i+1],nums[i]);
            rmax[i] = Math.max(rmax[i+1],nums[i]);
        }
        output();
    }

    @Override
    public void output() {
        ans = Integer.MAX_VALUE;
        int lmin = Integer.MAX_VALUE,lmax = 0;//[0,i-1]
        for (int i=0;i<n-1;i++) {
            if (i!=0) {
                lmin = Math.min(lmin,nums[i-1]);
                lmax = Math.max(lmax,nums[i-1]);
            }
            int x = nums[i] + nums[i+1];//[i,i+1]

            int max = Math.max(x,Math.max(lmax,rmax[i+2]));//rmax[n]，rmin[n]实际不可能取到,[n,n-1]不存在
            int min = Math.min(x,Math.min(lmin,rmin[i+2]));
            ans = Math.min(ans,max - min);
        }
        System.out.println(ans);
    }
}
/* p1010用printf会超时，必须用print，原理还不了解 */
class AkP1010 extends Problem {

    int[] l0,r0,l1,r1;
    int[] ans;
    int cnt;
    @Override
    public void input() {
        T = scan.nextInt();
        while(T-->0) {
            n = scan.nextInt();
            scan.nextLine();
            nums = new int[n];

            for (int i=0;i<n;i++)
                nums[i] = scan.nextInt();

            l0 = new int[n];
            r0 = new int[n];
            l1 = new int[n];
            r1 = new int[n];
            for (int i=1;i<n;i++) {
                 if (nums[i-1] == 0) {
                     l0[i] = l0[i - 1] + 1;
                     l1[i] = l1[i - 1];
                 }
                 else {
                     l1[i] = l1[i - 1] + 1;
                     l0[i] = l0[i - 1];
                 }
            }
            for (int i=n-2;i>=0;i--) {
                if (nums[i+1] == 0) {
                    r0[i] = r0[i + 1] + 1;
                    r1[i] = r1[i + 1];
                }
                else {
                    r1[i] = r1[i + 1] + 1;
                    r0[i] = r0[i + 1];
                }
            }
//            System.out.println(Arrays.toString(l0));
//            System.out.println(Arrays.toString(l1));
//            System.out.println(Arrays.toString(r0));
//            System.out.println(Arrays.toString(r1));
            output();

        }
    }

    @Override
    public void output() {
        ans = new int[n];
        if (nums[0] == 0) ans[0] = r0[0];
        else ans[0] = r1[0];
        System.out.print(ans[0]+" ");
        for (int i=1;i<n;i++) {

            if (nums[i] == 0)
                ans[i] = l1[i] + r0[i];
            else
                ans[i] = l0[i] + r1[i];

            System.out.print(ans[i] + " ");
        }
        System.out.println();



    }
}
class Acw4114 extends Problem {
    String strs;
    int[] l,r;
    int cnt;
    @Override
    public void input() {
        T = scan.nextInt();
        while(T-->0) {
            n = scan.nextInt();
            cnt++;
            scan.nextLine();
            nums = new int[n];
            strs = scan.nextLine();
            l = new int[n];
            Arrays.fill(l,-1);
            for (int i=0;i<n;i++) {
                nums[i] = strs.charAt(i) - '0';
                if (nums[i] == 1)
                    l[i] = i;
                else if (nums[i] == 0 && i!=0)
                    l[i] = l[i-1];
            }
            r = new int[n];
            Arrays.fill(r,-1);
            for (int i=n-1;i>=0;i--) {
                if (nums[i] == 1)
                    r[i] = i;
                else if (nums[i] == 0 && i!=n-1)
                    r[i] = r[i+1];
            }
            output();
//            System.out.println(Arrays.toString(l));
//            System.out.println(Arrays.toString(r));


        }
    }

    @Override
    public void output() {
        for (int i=0;i<n;i++) {
            int right = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
            if (r[i] != -1) right = r[i] - i;
            if (l[i] != -1) left = i - l[i];

            ans += Math.min(left,right);
        }
        System.out.printf("Case #%d: %d\n",cnt,ans);
        ans = 0;
    }
}
class Acw4977 extends Problem {
    long[] nums;
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new long[n];
        for (int i=0;i<n;i++)
            nums[i] = scan.nextLong();
    }

    @Override
    public void output() {
        input();
        int[] l = new int[n];
        l[0] = 0;
        Map<Long,Integer> map = new HashMap<>();
        map.put(nums[0],1);
        for (int i=1;i<n;i++) {
            if (nums[i]%k == 0 && map.containsKey(nums[i]/k))
                l[i] += map.get(nums[i]/k);

            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int[] r = new int[n];
        r[n-1] = 0;
        Map<Long,Integer> map1 = new HashMap<>();
        map1.put(nums[n-1],1);
        for (int i=n-2;i>=0;i--) {
            if (map1.containsKey(nums[i]*k))
                r[i] += map1.get(nums[i]*k);

            map1.put(nums[i],map1.getOrDefault(nums[i],0)+1);
        }

//        System.out.println(Arrays.toString(l));
//        System.out.println(Arrays.toString(r));
        for (int i=1;i<n-1;i++) {
            ans += (long)l[i]*r[i];
        }
        System.out.println(ans);
    }
}
class Lc2909 extends Problem {

    @Override
    public void input() {

    }

    @Override
    public void output() {
        nums = new int[]{8,6,1,5,3};
        n = nums.length;
        int[] l = new int[n];//l[i]，i左侧最小的元素
        int[] r = new int[n];
        l[0] = Integer.MAX_VALUE;
        r[n-1] = Integer.MAX_VALUE;
        for (int i=1;i<n;i++) {
            l[i] = Math.min(l[i-1],nums[i-1]);
        }
        for (int i=n-2;i>=0;i--) {
            r[i] = Math.min(r[i+1],nums[i+1]);
        }
        for (int i=0;i<n;i++) {
            if (nums[i] > l[i] && nums[i] > r[i]) {
                ans = l[i] + r[i] + nums[i];
            }
        }
        System.out.println(ans);
    }
}
/*      对于区间[j+1,i]满足情况，则有
    Si-Sj = (i-j)*k
    Si - ik = Sj - jk
    ∴逆推：
        遍历i，对于每个Si，若j<i时  已有Sj - jk = Si-ik；则[j+1,i]成立
*/
class AkP1008 extends Problem {

    long[] s;
    long k;
    long[] nums;
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextLong();
        nums = new long[n+1];
        s = new long[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
    }
    public void output() {
        input();
        Map<Long,Integer> map = new HashMap<>();
        long sum = 0;
        map.put(0L,0);
        for (int i=1;i<=n;i++) {
            sum += nums[i];
            if (map.containsKey(sum - i*k)) {//即Sj-j*k
                int j = map.get(sum - i*k);
                ans = Math.max(ans,i-j);
            }
            else map.put(sum - i*k,i);//最长，只记录第一次出现下标
        }
        System.out.println(ans);
    }
    public void outputError() {
        input();
        Map<Long,Integer> map = new HashMap<>();
        long sum = 0;
        map.put(0L,0);
        for (int i=1;i<=n;i++) {
            sum += nums[i];
            sum %= k;

            if (map.containsKey(sum)) {
                int j = map.get(sum);
                if ( (long)(i-j)*k == s[i]-s[j] )
                    ans = Math.max(ans,i-map.get(sum));
            }
            else map.put(sum,i);//会错过一些情况,存列表的话时间复杂度高
        }
//        System.out.println(map);
        System.out.println(ans);
    }
}
class Acw3553 extends Problem {
    String str;
    @Override
    public void input() {
        str = scan.nextLine();
    }

    @Override
    public void output() {
        input();
        n = str.length();
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for (int i=1;i<=n;i++) {
            int num = str.charAt(i-1) - '0';
            if (num == 0) num = -1;

            sum = (sum + num);

            if (map.containsKey(sum) ) {
                ans = Math.max(ans,i-map.get(sum));
            }
            else map.put(sum,i);

        }
//        System.out.println(map);
        System.out.println(ans);
    }
}
class AkP1009 extends  Problem {

    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
    }
    /*
        法1：取模
        (Si-Sj)% 2 == 0
        S为-1的个数

        法2：异或：二进制的位运算，相同运算为0，不同为1

     */
    @Override
    public void output() {
        input();
        int sum = 0; //空数组负数个数为0
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//空数组时，负数个数为0也是一种情况
        for (int i=1;i<=n;i++) {
           if (nums[i] < 0) sum = (sum + 1) % 2;
           else sum%=2;

           if (map.containsKey(sum)) {
               ans += map.get(sum);
           }
           map.put(sum,map.getOrDefault(sum,0)+1);

        }
        System.out.printf("%d %d",n*(n+1)/2-ans,ans);
    }
}
class Acw1230 extends Problem {

    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
    }
    /*  a>=0,b>=0
        (a-b)%k = a%k - b%k
        (a+b)%k = (a%k + b%k) % k  通用
     */
    public void output() {
        input();
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//哈希表存前缀和%k
        for (int i=1;i<=n;i++) {
            sum = (sum + nums[i]) % k;
            if (map.containsKey(sum)) {
                ans += map.get(sum);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
//        System.out.println(map);
        System.out.println(ans);
    }
}
class Acw3771 extends Problem {
    @Override
    public void input() {
        numsInput();//nums为价值
    }
    public void output() {
        input();
        Map<Integer,Long> map = new HashMap<>();
        for (int i=1;i<=n;i++) {
            map.put(nums[i]-i,map.getOrDefault(nums[i]-i,0L)+nums[i]);

        }
//        System.out.println(map);
        for (Map.Entry<Integer,Long> entry:map.entrySet()) {
            ans = Math.max(ans,entry.getValue());
        }
        System.out.println(ans);

    }
}
/* 本题用Set结构更好 */
class Ak1002 extends Problem{
    public void input() {
//        scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n];
    }

    public void output() {
        input();
        Map<Integer,Integer> map = new TreeMap<>();
        for (int i=0;i<n;i++) {
            nums[i] = scan.nextInt();
            int temp = map.getOrDefault(map.get(nums[i]),0);
            map.put(nums[i],temp+1);

        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (k>0) ans++;
            k--;

        }
        System.out.println(ans);
    }
}

class Acw4716 extends Problem {
    String team1,team2;
    public void input() {
        scan = new Scanner(System.in);
        n = scan.nextInt();
        scan.nextLine();
    }
    public void output () {
        input();
        Map<String,Integer> map = new HashMap<>();

        for (int i=0;i<n;i++) {
            String str = scan.nextLine();
            map.put(str,map.getOrDefault(str,0)+1);
            if (team1 == null) team1 = str;
            else if (!team1.equals(str) && team2 == null) team2 = str;
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new mapCompare());
        System.out.println(list.getFirst().getKey());
//
//        if (team2 == null) return team1;
//        return map.get(team1)>map.get(team2)?team1:team2;

    }
    class mapCompare implements Comparator<Map.Entry<String,Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue()-o1.getValue();
        }
    }
}
class Ak1001 extends Problem {
    int[] nums2,nums5;
    long ans =0;
    int cnt2=0,cnt5=0;

    @Override
    public void output() {

    }

    public void input() {
        ;
    }
    public long res(int n,int k,Scanner scan) {
        nums = new int[n];
        nums2 = new int[n];
        nums5 = new int[n];

        for (int i=0;i<n;i++) {
            nums[i] = scan.nextInt();
            while (nums[i]%2 ==0) {
                nums[i]/=2;
                nums2[i]++;
                cnt2++;
            }
            while (nums[i]%5 == 0) {
                nums[i]/=5;
                nums5[i]++;
                cnt5++;
            }
        }

        for (int l=0,r=0;r<nums.length;r++) {
            cnt2 -= nums2[r];
            cnt5 -= nums5[r];
            while ( l<=r && !checkZero(k)) {
                cnt2 += nums2[l];
                cnt5 += nums5[l];
                l++;
            }
            ans += r-l+1;
        }
        return ans;
    }
    public boolean checkZero(int k) {
        return Math.min(cnt2,cnt5) >= k;
    }
}


