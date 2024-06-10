import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = s.length();
        int res = 0;
        // 枚举1~2^n-1的所有状态
        for (int state = 1; state < (1 << n); state++) {
            StringBuilder t = new StringBuilder();
            boolean flag = true;  // 来判断当前字符串是否合法，也就是不能有两个连续的0
            // 判断是否有两个连续的0
            for (int i = 0; i < n - 1; i++) {
                boolean f1 = (state >> i & 1) == 1;
                boolean f2 = (state >> (i + 1) & 1) == 1;
                if (!f1 && !f2) {  // 有两个连续的0
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;  // 不合法，直接枚举下一个状态
            // 判断第i个字符是否被选择
            for (int i = 0; i < n; i++) {
                // 如果第i个字符被选择
                if ((state >> i & 1) == 1) {
                    t.append(s.charAt(i));
                }
            }
            // 如果子串包含"bengtie"，则计数器加一
            if (t.toString().contains("bengtie")) {
                res++;
            }
        }
        System.out.println(res);
    }
}