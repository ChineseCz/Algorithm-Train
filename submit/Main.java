import java.util.*;

public class Main {
    public static void main(String[] args) {
        new AkP1008().output();

    }
}

abstract class Problem {
    Scanner scan = new Scanner(System.in);
    int n,k;
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
/*  对于区间[j+1,i]满足情况，则有
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


