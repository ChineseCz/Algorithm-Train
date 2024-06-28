
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new LongUpArr().input();
    }
}

abstract class Problem {
    Scanner scan = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    int n,m,q,k,T;
    int[] nums;
    long ans;
    String str;
    public abstract void input() throws IOException;

    public abstract void output() throws IOException;

    class Node {
        int x;
        int y;
        int state;
        public Node(int x,int y) {
            this.x = x;
            this.y = y;
        }
        public Node(int x,int y,int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
}
//状态定义
class LongUpArr extends Problem {
    long[] num;
    int[] dp;
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        num = new long[n+1];
        dp = new int[n+1];
        for (int i=1;i<=n;i++) {
            num[i] = scan.nextLong();
        }
        dp[1] = 1;
        for (int i=2;i<=n;i++) {
            if (num[i] > num[i-1]) {
                dp[i] = dp[i-1] + 1;
            }
            else
                dp[i] = 1;
            ans = Math.max(ans,dp[i]);
        }
        System.out.println(ans);
    }

    @Override
    public void output() throws IOException {

    }
}
class TiaoGeZi extends Problem {
    long[] sco;
    long[] dp;
    List<Integer> fb = new ArrayList<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        sco = new long[n+1];
        dp = new long[n+1];
        Arrays.fill(dp,Long.MIN_VALUE);
        feiBo();
        for (int i=1;i<=n;i++) {
            sco[i] = scan.nextLong();
        }
        dp[1] = sco[1];
        for (int i=2;i<=n;i++) {
            for (int j=0;j<fb.size();j++) {
                int jump = fb.get(j);
                if (i - jump < 0 )
                    break;
                dp[i] = Math.max(dp[i - jump] + sco[i], dp[i]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }
    public void feiBo() throws IOException {
        int f1,f2,f3;
        f1 = 1;
        f2 = 1;
        fb.add(1);
        for (int i = 3; i <= 100; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;

            if (f2 >= (int)1e5 * 2)
                break;
            fb.add(f2);
        }

    }
    @Override
    public void output() throws IOException {

    }
}
class TiaoTiaoQi extends Problem {
    int[] sco;
    int[] dp;
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        sco = new int[n+1];
        dp = new int[n+1];
        for (int i=1;i<=n;i++) {
            sco[i] = scan.nextInt();
        }
        dp[1] = sco[1];
        for (int i=2;i<=n;i++) {
            dp[i] = Math.max(dp[i-2]+sco[i],dp[i-1]);
        }
        System.out.println(dp[n]);
    }

    @Override
    public void output() throws IOException {

    }
}
class FeiBo extends Problem {
    long f1 = 1;
    long f2 = 1;
    long f3;
    int mod = (int)1e9 +7;
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        if (n == 1) System.out.println(1);
        else if (n == 2) System.out.println(1);
        else {
            for (int i = 3; i <= n; i++) {
                f3 = f1 + f2;
                f3 %= mod;
                f1 = f2;
                f2 = f3;
            }
            System.out.println(f3);
        }
    }

    @Override
    public void output() throws IOException {

    }
}
class AkP1157 extends Problem {
    class Child {
        int index;
        long gift;
        public Child(int index,long gift) {
            this.index = index;
            this.gift = gift;
        }
    }
    class myComparator implements Comparator<Child> {

        @Override
        public int compare(Child o1, Child o2) {
            if (Math.ceil((double) o1.gift /m) != Math.ceil((double) o2.gift /m)) return (int) (o1.gift - o2.gift);
            else return o1.index - o2.index;
        }
    }
    PriorityQueue<Child> que = new PriorityQueue<>(new myComparator());
    @Override
    public void input() throws IOException {

        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        for (int i=0;i<n;i++) {
            long q = Long.parseLong(str[i]);
            que.add(new Child(i,q));
        }
        for (int i=0;i<n;i++) {
            writer.write(que.poll().index + 1 + " ");
        }
        writer.flush();
    }

    @Override
    public void output() throws IOException {

    }
}
class AkP1153 extends Problem {
    long[] sco;
    long[] dif;
    PriorityQueue<Long> que = new PriorityQueue<>();
    PriorityQueue<Long> queMax = new PriorityQueue<>(Comparator.reverseOrder());
    long max;
    @Override
    public void input() throws IOException {
        String[] nm = reader.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        String[] str = reader.readLine().split(" ");
        for (int i=0;i<n;i++) {
            long sco = Long.parseLong(str[i]);
            que.add(sco);
            max = Math.max(sco,max);
//            queMax.add(sco);
        }
        String[] str1 = reader.readLine().split(" ");
        for (int i=0;i<m;i++) {
            long dif = Long.parseLong(str1[i]);
            long sco = que.remove();
//            queMax.remove((Long) sco);
            sco += dif;
            que.add(sco);
            max = Math.max(sco,max);
//            queMax.add(sco);
            writer.write(max+"\n");
        }
        writer.flush();
    }

    @Override
    public void output() throws IOException {

    }
}
//连通块 + 多源BFS
class lc934 {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    class Node {
        int x,y;
        public Node(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
    int n,num;
    int[][] dist;
    boolean[][] visit;
    int[][] grid;
    Queue<Node> que = new LinkedList<>();
    int ans = Integer.MAX_VALUE;
    public void dfs(int x,int y) {
        visit[x][y] = true;
        que.offer(new Node(x,y));
        dist[x][y] = 0;
        for (int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx == n || ny < 0 || ny == n || visit[nx][ny] || grid[nx][ny] == 0)
                continue;
            dfs(nx,ny);
        }
    }
    //其实找到1就可以返回了，因为BFS每层递增都是最近的情况
    public void bfs() {
        while (!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            for (int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx == n || ny < 0 || ny == n )
                    continue;
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    que.offer(new Node(nx,ny));
                }
            }
        }
    }
    public int shortestBridge(int[][] grid) {
        n = grid.length;
        dist = new int[n][n];
        visit = new boolean[n][n];
        this.grid = grid;
        for (int i=0;i<n;i++)
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    dfs(i,j);
                    num++;
                    if (num == 1) break;
                }
            }
            if (num == 1) break;
        }
        bfs();
        System.out.println(Arrays.deepToString(visit));
        System.out.println(Arrays.deepToString(dist));
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    ans = Math.min(dist[i][j],ans);
                }
            }
        }
        return ans - 1;
    }
}
class AkP1075 extends Problem {
    int[][] g;
    Queue<Node> que = new LinkedList<>();
    int[][] dist;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    @Override
    public void input() throws IOException {
        String[] nk = reader.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        m = Integer.parseInt(nk[1]);
        g = new int[n][m];
        dist = new int[n][m];
        for (int i=0;i<n;i++) {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            String[] str = reader.readLine().split(" ");
            for (int j=0;j<m;j++) {
                g[i][j] = Integer.parseInt(str[j]);
                if (g[i][j] == 1 && j == 0) {
                    que.offer(new Node(i,j));
                    dist[i][j] = 0;
                }
            }
        }
        bfs();
        output();
    }
    public void bfs() {
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            for (int i=0;i<4;i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (nx <0 || ny<0||nx>=n || ny>=m || g[nx][ny] == 0)
                    continue;
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    que.add(new Node(nx, ny));
                }
            }
        }
    }
    @Override
    public void output() throws IOException {
        ans = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            ans = Math.min(ans,dist[i][m-1]);
        }
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
class Acw3083 extends Problem {
    int[][] g;
    Queue<Node> que = new LinkedList<>();
    int[][] dist;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    @Override
    public void input() throws IOException {
        String[] nk = reader.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        m = Integer.parseInt(nk[1]);
        g = new int[n][m];
        dist = new int[n][m];
        for (int i=0;i<n;i++) {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            String str = reader.readLine();
            for (int j=0;j<m;j++) {
                g[i][j] = str.charAt(j) - '0';
                if (g[i][j] == 1) {
                    que.offer(new Node(i,j));
                    dist[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(dist));
        bfs();
        output();

    }
    public void bfs() {
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            for (int i=0;i<4;i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (nx <0 || ny<0||nx>=n || ny>=m )
                    continue;
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    que.add(new Node(nx, ny));
                }
            }
        }
    }
    @Override
    public void output() throws IOException {
        for (int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                writer.write(dist[i][j] + " ");

            }
            writer.write("\n");
        }
        writer.flush();
    }
}
class AkP1074 extends Problem {
    String[][] g;
    int[][] trick;
    int strx,stry,endx,endy;
    int[] dx = {-1,0,1,0,0};
    int[] dy = {0,-1,0,1,0};
    int[][][] dist;
    int state;
    Queue<Node> que = new LinkedList<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        k = scan.nextInt();
        g = new String[n][n];
        trick = new int[n][n];
        dist = new int[3][n][n];
        for (int i=0;i<3;i++)
            for (int j=0;j<n;j++)
                Arrays.fill(dist[i][j],Integer.MAX_VALUE);
        for (int i=0;i<k;i++) {
            int x,y;
            x = scan.nextInt();
            y = scan.nextInt();
            trick[x][y] = 1;
        }
        endx = scan.nextInt();
        endy = scan.nextInt();
        strx = scan.nextInt();
        stry = scan.nextInt();
        scan.nextLine();
        for (int i=0;i<n;i++) {
            String[] str = scan.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                g[i][j] = str[j];
            }
        }
        que.offer(new Node(strx,stry,0));
        dist[0][strx][stry]= 0;
        bfs();
//        for (int i=0;i<3;i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < n; k++) {
//                    if (dist[i][j][k] == Integer.MAX_VALUE) dist[i][j][k] = -1;
//                    System.out.print(dist[i][j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        output();
    }
    public void bfs() {

        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            int s = node.state;
            for (int i=0;i<5;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int ns = (s + 1)%3;
                if (nx <0 || nx >= n || ny < 0 || ny >= n
                        || trick[nx][ny] == 1
                        || g[nx][ny].charAt(ns) == '1'//注意这里别弄错，char1和int1
                        || dist[ns][nx][ny] != Integer.MAX_VALUE)
                    continue;

                que.offer(new Node(nx,ny,ns));
                dist[ns][nx][ny] = dist[s][x][y] +1;


            }

        }
    }

    @Override
    public void output() throws IOException {
        ans = Integer.MAX_VALUE;
        for (int i=0;i<3;i++) {
            ans = Math.min(ans,dist[i][endx][endy]);
        }
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
//如何输出路径是本题难点
//可以通过一个数组来记录当前节点的父节点信息 然后从终点逆向推到起点
// （这一过程其实很像动态规划）
// 再对数组进行reverse即可顺序输出从起点到终点的路径
class AkP1053 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    int[] w;
    int[] dist;
    int[] degree;
    int[] fa;
    int goal;
    List<Integer> res = new ArrayList<>();
    Queue<Integer> que = new LinkedList<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        m = scan.nextInt();
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        w = new int[n+1];//这里开n不够。被堵路口有n，数据不合理
        dist = new int[n];
        degree = new int[n];
        fa = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        for (int i=1;i<=m;i++){
            int u,v;
            u = scan.nextInt();
            v = scan.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }
        for (int i=0;i<n;i++) {
            Collections.sort(g.get(i));
        }
        k = scan.nextInt();
        for (int i=0;i<k;i++) {
            int a = scan.nextInt();
            w[a] = 1;
        }
        que.offer(0);
        dist[0] = 0;
        goal = bfs();
        output();
    }
    public int bfs() {
        if (w[0] == 1) return -1;
        while(!que.isEmpty()) {
            int node = que.poll();
            for (int next:g.get(node)) {
                if (w[next] == 0 && dist[next] == Integer.MAX_VALUE) {
                    que.offer(next);
                    dist[next] = dist[node] + 1;
                    fa[next] = node;
                    if (degree[next] == 1 ) {
                        return next;
                    }
                }
            }
        }
        return -1;
    }
    @Override
    public void output() throws IOException {
        if (goal == -1) {
            System.out.println("NULL");
            return;
        }
        while(goal != 0) {
            res.add(goal);
            goal = fa[goal];
        }
        System.out.print("0");
        for (int i=res.size()-1;i>=0;i--) {
            System.out.print("->"+res.get(i));
        }

    }
}
//dist为什么要开long，n不是最大10^5吗
class AkP1052 extends Problem {
    long[] dist;
    long k;
    int[] w;
    List<List<Integer>> g = new ArrayList<>();
    Queue<Integer> que = new LinkedList<>();
    List<Integer> res = new ArrayList<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        k = scan.nextLong();
        scan.nextLine();
        String[] str = scan.nextLine().split(" ");
        dist = new long[n + 1];
        w = new int[n + 1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=1;i<=n;i++) {
//            w[i] = str[i-1].charAt(0);//妈的，你这个只取第一位啊！今天脑子进水了，太幽默了。。
            int v = Integer.parseInt(str[i-1]);
            dist[i] = Long.MAX_VALUE;//为什么必须LONG
            g.get(i).add(v);
        }
//        System.out.println(Arrays.toString(w));
        //以下没问题
        que.offer(1);
        dist[1] = 0;
        bfs();
        output();
    }
    public void bfs() {
        while (!que.isEmpty() ) {
            int cur = que.poll();

            for (int node:g.get(cur)) {
                if (dist[node] > dist[cur] + 1) {
                    que.offer(node);
                    dist[node] = dist[cur] + 1;
                }
            }
        }
    }

    @Override
    public void output() throws IOException {
//        Collections.sort(res);
//        for (int i=0;i<res.size()-1;i++)
//            System.out.print(res.get(i)+" ");
//        System.out.print(res.get(res.size()-1));
        for (int i=1;i<=n;i++)
            if (dist[i] <= k)
                System.out.print(i+" ");
    }
}
class AkP1073 extends Problem {
    int x,y;
    int[][] g;
    int[][] dist;
    int[] dx = {-2,-2,-1,-1,1,1,2,2};
    int[] dy = {-1,1,-2,2,-2,2,-1,1};
    Queue<Node> que = new ArrayDeque<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        m = scan.nextInt();
        x = scan.nextInt();
        y = scan.nextInt();
        dist = new int[n+1][m+1];
        for (int[] arr:dist)
            Arrays.fill(arr,Integer.MAX_VALUE);
        que.offer(new Node(x,y));
        dist[x][y] = 0;
        bfs();
        output();
    }
    public void bfs() {
        while(!que.isEmpty()) {
            Node node = que.poll();
            for (int i=0;i<8;i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m || dist[nx][ny] != Integer.MAX_VALUE)
                    continue;
                dist[nx][ny] = dist[node.x][node.y] + 1;
                que.offer(new Node(nx,ny));
            }
        }
    }

    @Override
    public void output() throws IOException {
        for (int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) dist[i][j] = -1;
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }
}
class AkP1072 extends Problem {
    int x1,y1,x2,y2;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    int[][] g;
    int[][] dist;
    Queue<Node> que = new ArrayDeque<>();
    class Node {
        int x,y;
        public Node(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        scan.nextLine();
        g = new int[n][n];
        dist = new int[n][n];
//        dist[x1][y1] = 0;
        for (int[] num:dist)
            Arrays.fill(num, Integer.MAX_VALUE);
        for (int i=0;i<n;i++) {
            str = scan.nextLine();
            for (int j=0;j<n;j++) {
                g[i][j] = str.charAt(j) - '0';
            }
        }
        String[] str = scan.nextLine().split(" ");
        x1 = Integer.parseInt(str[0]) - 1;
        y1 = Integer.parseInt(str[1]) - 1;
        x2 = Integer.parseInt(str[2]) - 1;
        y2 = Integer.parseInt(str[3]) - 1;
        dist[x1][y1] = 0;
        que.offer(new Node(x1,y1));
        bfs();
//        System.out.println(Arrays.deepToString(dist));
        output();
    }
    public void bfs() {
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            for (int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n
                        && g[nx][ny] != 1
                        && dist[nx][ny] == Integer.MAX_VALUE) {//没访问过,或者 dist[nx][ny] > dist[x][y] + 1,∵访问过的点，要么比他小，要么为当前点+1
                    que.offer(new Node(nx,ny));
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }



        }
    }

    @Override
    public void output() throws IOException {
        ans = dist[x2][y2];
        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
class IEEEP6 extends Problem {
    class Node {
        int next;
        int w;//边权
        public Node(int next,int w) {
            this.next = next;
            this.w = w;
        }
    }
    int[] w;
    List<List<Node>> g = new ArrayList<>();
    public void dfs(int u,int fa) {
        for (Node t : g.get(u)) {//访问u节点的所有节点
            int x = t.next,w = t.w;
            if (x == fa) continue;//访问过，两点互通
            dfs(x,u);
            // do things
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        for (int i=1;i<=m;i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            g.get(a).add(new Node(b,c));
            g.get(b).add(new Node(a,c));
        }
        for (int i=1;i<=n;i++) {
            w[i] = scan.nextInt();
        }
        dfs(1,-1);
    }
    @Override
    public void output() throws IOException {

    }
}
class IEEEP3 extends Problem {
    String[] grid;
    boolean ans;
    @Override
    public void input() throws IOException {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        grid = new String[n+1];
        for (int i=0;i<n;i++)
            grid[i] = scan.nextLine();
        if (n == 1 && m == 1 && (grid[0].charAt(0)=='/' || grid[0].charAt(0)=='\\')) {
            System.out.println("Stable");
            return;
        }
        ans = isStable();
        if (ans) System.out.println("Stable");
        else System.out.println("Unstable");

    }
    private boolean isStable() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                boolean hasSlash = false;
                boolean hasBackslash = false;

                char[] corners = {
                        grid[i].charAt(j), grid[i].charAt(j + 1),
                        grid[i + 1].charAt(j), grid[i + 1].charAt(j + 1)
                };
//                System.out.println(corners);
                for (char c : corners) {
                    if (c == '/') {
                        hasSlash = true;
                    }
                    if (c == '\\') {
                        hasBackslash = true;
                    }
                }


                if (!hasSlash && !hasBackslash) {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public void output() throws IOException {

    }
}
//字符串切割、进制转换
class IEEEP2 extends Problem {
    long num1,num2,out;
    String[] equal;
    String op;
    List<Integer> res = new ArrayList<>();
    @Override
    public void input() throws IOException {
        String str = scan.nextLine();
        equal = str.split("(?<=[-+*/=])|(?=[-+*/=])");
//        System.out.println(Arrays.toString(equal));

        for (int i=2;i<=16;i++) {
            if (check(i))
                res.add(i);
        }
        output();

    }
    public boolean isValid(String s,int radix) {
        for (char c:s.toCharArray()) {
            if (Character.digit(c,radix)<0) return false;
        }
        return true;
    }
    public boolean check(int radix) {
        if (!isValid(equal[0], radix) || !isValid(equal[2], radix) || !isValid(equal[4], radix)) {
            return false;
        }
        num1 = Long.parseLong(equal[0],radix);
        op = equal[1];
        num2 = Long.parseLong(equal[2],radix);
        out = Long.parseLong(equal[4],radix);
        switch (op) {
            case "+":
                return (num1 + num2) == out;
            case "-":
                return (num1 - num2) == out;
            case "*":
                return (num1 * num2) == out;
            case "/":
                return ((double) num1 / num2) == out;
            default:
                return false;
        }
    }
    @Override
    public void output() throws IOException {
        if (res.isEmpty()) System.out.println("-1");
        else {
            for (Integer radix:res)
                System.out.print(radix + " ");
        }
    }
}
//题意，原数组不能排序
class IEEEP7 extends Problem {

    @Override
    public void input() throws IOException {
        String[] nk = reader.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);
        nums = new int[n];
        String[] str = reader.readLine().split(" ");
        for (int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        while(k-->0) {
            String[] str1 = reader.readLine().split(" ");
            int l = Integer.parseInt(str1[0]);
            int r = Integer.parseInt(str1[1]);
            int kth = Integer.parseInt(str1[2]);
            l--;
            r--;
            solve(l,r,kth);

        }
        writer.flush();

    }
    public void solve(int l,int r,int kth) throws IOException {
        int[] numsTemp = new int[r-l+1];
        for (int i=0,j=l;i<numsTemp.length;i++,j++) {
            numsTemp[i] = nums[j];
        }

        Arrays.sort(numsTemp);
        int goal = kth - 1;
        if ( (goal+1<numsTemp.length && numsTemp[goal] == numsTemp[goal+1])
                || (goal-1 >= 0 && numsTemp[goal] == numsTemp[goal-1]))
            writer.write("Not Found\n");
        else
            writer.write(numsTemp[goal]+"\n");


    }

    @Override
    public void output() throws IOException {

    }
}
//救援，求中位数点
class IEEEP10 extends Problem {
    List<Point> list = new ArrayList<>();
    int midX,midY;//中位数
    int totalInjur;
    int ansX,ansY;
    class Point {
        int x,y,num;
        public Point(int x,int y,int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    @Override
    public void input() throws IOException {
        n = Integer.parseInt(reader.readLine());

        while(n-->0) {
            String[] num = reader.readLine().split(" ");
            int x = Integer.parseInt(num[0]);
            int y = Integer.parseInt(num[1]);
            int injur = Integer.parseInt(num[2]);
            totalInjur += injur;
            list.add(new Point(x,y,injur));

        }
        midX = findMidX();
        midY = findMidY();
        output();
    }
    class CompareByX implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.x - o2.x;
        }
    }
    class CompareByY implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.y - o2.y;
        }
    }
    public int findMidX() {
        list.sort(new CompareByX());
        int sum = 0;
        for (int i=0;i<list.size();i++) {
            sum+=list.get(i).num;
            if (sum >= (totalInjur+1)>>1)
                return list.get(i).x;
        }
        return 0;
    }
    public int findMidY() {
        list.sort(new CompareByY());
        int sum = 0;
        for (int i=0;i<list.size();i++) {
            sum+=list.get(i).num;
            if (sum >= (totalInjur+1)>>1)
                return list.get(i).y;
        }
        return 0;
    }
    @Override
    public void output() throws IOException {
        long min = Long.MAX_VALUE;
        for (Point loc : list) {
            long dis = Math.abs(loc.x - midX) + Math.abs(loc.y - midY);
            if (dis < min) {
                min = dis;
                ansX = loc.x;
                ansY = loc.y;
            }
        }
        System.out.println(ansX +" "+ansY);
    }
}
//零钱兑换，动态规划
class IEEEP8 extends Problem {
    int[] c;
    int[] dp;
    @Override
    public void input() throws IOException {
        k = scan.nextInt();
        c = new int[k+1];
        for (int i=1;i<=k;i++) {
            c[i] = scan.nextInt();
        }
        T = scan.nextInt();
        dp = new int[T+1];
        Arrays.fill(dp,T+1);
        dp[0] = 0;

        for (int i=1;i<=T;i++)
            for (int j=0;j<c.length;j++) {
                if (i >= c[j]) dp[i] = Math.min(dp[i],dp[i-c[j]] + 1);
            }
        output();
    }

    @Override
    public void output() throws IOException {
        System.out.println(dp[T]);
    }
}
//字符串匹配，我的解法O(n)但错在哪里？
class IEEEP11 extends Problem {
    String T,P;
    List<Integer> str = new ArrayList<>();
    @Override
    public void input() throws IOException {
        T = scan.nextLine();
        P = scan.nextLine();
        int tlen = T.length();
        int plen = P.length();
        for (int i=0;i<=tlen-plen;i++) {
            if (T.substring(i,i+plen).equals(P)) {
                str.add(i);
            }
        }
//        for (int i=0,j=0;i<=T.length()-P.length();i++) {
//            if (T.charAt(i) == P.charAt(j)) {
//                int temp = i;
//                i++;
//                j++;
//                while (j < P.length() && i < T.length() && T.charAt(i) == P.charAt(j)) {
//                    i++;
//                    j++;
//                }
//                if (j == P.length()) {
//                    str.add(temp);
//                }
//                j = 0;
//                i--;
//
//            }
//        }
        output();
    }

    @Override
    public void output() throws IOException {
        if (str.isEmpty()) System.out.println("Not Found");
        else {
            for (int i=0;i<str.size();i++) {
                if (i!=0)
                    System.out.print(" " + str.get(i));
                else System.out.print(str.get(0));
            }
            System.out.println();
        }
    }
}
//区间不重叠最大数量，贪心
class IEEEP9 extends Problem {
    List<Interval> list = new ArrayList<>();
    class Interval {
        int str,end;
        public Interval (int str,int end) {
            this.end = end;
            this.str = str;
        }
    }
    class MyComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {
            if (o1.end != o2.end) return o1.end - o2.end;
            else return o1.str - o2.str;
        }
    }
    @Override
    public void input() throws IOException {
        n = Integer.parseInt(reader.readLine());
        while (n-->0) {
            String[] str = reader.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            list.add(new Interval(a,b));
        }
        list.sort(new MyComparator());

        int curEnd = 0;
        for (Interval node:list) {
            if (node.str >= curEnd) {
                ans++;
                curEnd = node.end;
            }
        }
        output();
    }

    @Override
    public void output() throws IOException {
        System.out.println(ans);
    }
}
//子串是否能分割为三个回文，即枚举两个切割点再判断是否回文，但加上动态预判断回文
class IEEEP12 extends Problem {
    boolean[][] dp;
    @Override
    public void input() throws IOException {
        str = scan.nextLine();
        n = str.length();
        dp = new boolean[n][n];
        for (int i=0;i<n;i++)
            dp[i][i] = true;

        for (int len = 2;len<=n;len++) {
            for (int i=0;i <=n-len;i++) {
                int j = i + len - 1;
                if (str.charAt(i) == str.charAt(j)) {
                    if (len == 2 || dp[i+1][j-1])
                        dp[i][j] = true;
                }
            }
        }

        for (int i = 1;i<n;i++)
            for (int j=1;j<n;j++) {
                if (dp[0][i-1] && dp[i][j-1] && dp[j][n-1]) {
                    ans = 1;
                    break;
                }
            }
        if (ans == 1) System.out.println("true");
        else System.out.println("false");
    }


    @Override
    public void output() throws IOException {

    }
}
//递归题
class IEEEP4 extends Problem {
    int len;
    int res;
    @Override
    public void input() throws IOException {
        str = reader.readLine();
        len = str.length();
        n = Integer.parseInt(reader.readLine());
        while(n-->0) {
            long layer = 0;
            long kth = Long.parseLong(reader.readLine());
            kth--;
            res = (int) (kth % len);
            long k = (kth / len);
            if (k != 0)
                layer = (long) (Math.log(k) / Math.log(2)) +1;

            ans = dfs(layer,k);
            if (ans == 0) writer.write("0\n");
            else writer.write("1\n");


        }
        writer.flush();

    }
    public int dfs(long n,long k) {
        if (n == 0) return str.charAt(res)-'0';
        else if (k % 2 ==0)
            return dfs(n-1,k/2);
        else
            return 1 - dfs(n-1,k/2) ;
    }
    @Override
    public void output() throws IOException {

    }
}
class AkP1063 extends Problem {
    int[] a;
    int[][] f;
    int[] cnt2,cnt5;
    int[] w;


    List<List<Integer>> g = new ArrayList<>();
    @Override
    public void input()  throws IOException {
        n = Integer.parseInt(reader.readLine());
        a = new int[n+1];
        cnt2 = new int[n+1];
        cnt5 = new int[n+1];
        w = new int[n+1];
        f = new int[n+1][2];
        String[] tokens = reader.readLine().split("\\s");
        for (int i=1;i<=n;i++) {
            g.add(new ArrayList<>());
            a[i] = Integer.parseInt(tokens[i-1]);
            while (a[i]%2 == 0) {
                a[i]/=2;
                cnt2[i]++;
            }
            while (a[i]%5 == 0) {
                a[i]/=5;
                cnt5[i]++;
            }
        }

        g.add(new ArrayList<>());
        for (int i=2;i<=n;i++) {
            tokens = reader.readLine().split("\\s");
            int u,v;
            u = Integer.parseInt(tokens[0]);
            v = Integer.parseInt(tokens[1]);
            g.get(u).add(v);
            g.get(v).add(u);
        }

        dfs(1,-1);
        output();

    }

    public int zeroCnt(int u) {
        return Math.min(f[u][0],f[u][1]);
    }
    public void dfs(int u,int fa) {
        f[u][0] += cnt2[u];
        f[u][1] += cnt5[u];
        if (g.get(u).size() == 1 && u!= 1) {//注意排除根节点
            w[u] = zeroCnt(u);
            return;
        }
        for (Integer node:g.get(u)) {
            if (node == fa) continue;
            dfs(node,u);
            f[u][0] += f[node][0];
            f[u][1] += f[node][1];
        }
        w[u] = zeroCnt(u);
    }
    @Override
    public void output() throws IOException {
        for (int i=1;i<=n;i++) {
            writer.write(w[i] + " ");
        }
        writer.flush();
    }
}
/*原来思路错误点
 1.只考虑裁剪depth=3的点，但可能最高层值很大，裁到第二层减少的更多，脑子热昏了
 2.两次DFS，当n为10^5时，栈的空间消耗过大
 */

class AkP1062 extends Problem {
    int[] a;
    long[] factor;
    long[] total;
    int[] depth;

    long max,cutSum,cutDepth;
    List<List<Integer>> g = new ArrayList<>();
    @Override
    public void input() throws IOException {
        n = scan.nextInt();

        a = new int[n+1];
        total = new long[n+1];
        depth = new int[n+1];
        factor = new long[n+1];

        for (int i = 1; i <= n; i++) {
            g.add(new ArrayList<>());
            a[i] = scan.nextInt();
        }
        g.add(new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int u,v;

            u = scan.nextInt();
            v = scan.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        dfs(1,-1,1);
        output();
    }

    public void dfs(int u,int fa,int depth) {
        factor[u] = (long) depth * a[u];
        total[u] = a[u];
        this.depth[u] = depth;
        for (Integer node: g.get(u)) {
            if (node == fa) continue;
            dfs(node,u,depth+1);
            total[u] += total[node];
            factor[u] += factor[node];
        }

    }
    @Override
    public void output() throws IOException {
        ans = Long.MAX_VALUE;
        for (int i=1;i<=n;i++) {
            ans = Math.min(ans,factor[1] - (total[i]*(depth[i]-2)));
        }

        System.out.println(ans);
    }
}
class AkP1059 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    int[] cnt;
    int[] degree;
    Integer[] node;

    List<Integer> res = new ArrayList<>();
    class myComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (cnt[o1] != cnt[o2]) return cnt[o2] - cnt[o1];
            else return o1-o2;
        }
    }

    @Override
    public void input() {
        n = scan.nextInt();
        cnt = new int[n];
        degree = new int[n];
        node = new Integer[n];
        for (int i=0;i<n;i++) {
            g.add(new ArrayList<>());
        }
        for (int i=0;i<n;i++) {
            int u = scan.nextInt();
            if (u!= -1) {
                g.get(u).add(i);
                degree[i]++;
            }
        }
        for (int i=0;i<n;i++) {
            node[i] = i;
            if (degree[i] == 0) {
                dfs(i);
            }
        }
        Arrays.sort(node,new myComparator());
        for(int i = 0; i < n; i++) {
            System.out.print(node[i] + " ");
        }
//        for (int i=0;i<n;i++) {
//            map.get(cnt[i]).add(i);
//        }
//        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//            Collections.sort(entry.getValue());
//        }
//        Arrays.sort(cnt);
//        output();


    }
    public void dfs(int u) {
        cnt[u] = 1;
        for (Integer node:g.get(u)) {
            dfs(node);
            cnt[u] += cnt[node];
        }
    }
    @Override
    public void output() {
//        for (int i=n-1;i>=0;i--) {
//            if (map.containsKey(cnt[i]))
//                for (Integer num : map.get(cnt[i])) {
//                    if (!res.contains(num)) {
//                        res.add(num);
//                    }
//                }
//        }
//        for (Integer num : res) {
//            System.out.print(num + " ");
//        }
    }
}
class AkP1060 extends Problem {
    List<List<Node>> g = new ArrayList<>();
    Integer[] node;
    int[] prior;
    int[] degree;
    class Node {
        int next;
        int weight;
        public Node(int next,int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
    class myComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (prior[o1] != prior[o2]) return prior[o2] - prior[o1];
            else return o1-o2;
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        node = new Integer[n+1];
        prior = new int[n+1];
        degree = new int[n+1];
        for (int i=0;i<=n;i++) {
            g.add(new ArrayList<>());
            node[i] = i;
        }
        for (int i=0;i<m;i++) {
            int pro,con,latency;
            pro = scan.nextInt();
            con = scan.nextInt();
            latency = scan.nextInt();
            g.get(pro).add(new Node(con,latency));
            degree[con]++;
        }
        for (int i=1;i<=n;i++)
            if (degree[i] == 0)
                dfs(i);

//        System.out.println(Arrays.toString(prior));
        Arrays.sort(node,new myComparator());
//        Arrays.sort(node,1,n,new myComparator());1到n排序后是从0开始放的
        for (int i=0;i<=n;i++)
            if (node[i]!=0 ) System.out.print(node[i]+" ");
    }
    public void dfs(int u) {
        for (Node node:g.get(u)) {
            dfs(node.next);
            prior[u] = Math.max(prior[u],prior[node.next] + node.weight);
        }
    }
    @Override
    public void output() {

    }
}
class AkP1061 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    int[] d;
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        d = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=2;i<=n;i++) {
            int u,v;
            u = scan.nextInt();
            v = scan.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        dfs(1,-1,-1);
        output();
    }
    //已经有实例变量表示深度状态了，其实不需要形参int depth
    public void dfs(int u,int fa,int depth) {
        d[u] = depth + 1;
        int size = g.get(u).size();

        if (d[u] <= k) {
            ans++;
            if (size == 1 && fa != -1) ans += k - d[u];//叶子节点,注意判断不是根节点，根节点可能只有1个出度，一旦判断为叶子就多算了
        }

        for (Integer node:g.get(u)) {
            if (node == fa) continue;
            dfs(node,u,d[u]);
        }
    }

    @Override
    public void output() {
        System.out.println(ans);
    }
}
//树形DFS题，裁剪一条边后，两个节点各自形成子树，此题不是路径类，不应该枚举n^2.
class AkP1066 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    int[] w;
    int[][] f;
    int[] total = new int[3];
    @Override
    public void input() {
        n = scan.nextInt();
        w = new int[n+1];
        f = new int[n+1][3];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=2;i<=n;i++) {
            int u = scan.nextInt();
            g.get(u).add(i);
            g.get(i).add(u);
        }
        scan.nextLine();
        str = scan.nextLine();
        for (int i=1;i<=n;i++) {
            if(str.charAt(i-1) == 'R') w[i] = 0;
            else if (str.charAt(i-1) == 'G') w[i] = 1;
            else w[i] = 2;
        }

        for (int i=1;i<=n;i++) {
            if (w[i] == 0 ) total[0]++;
            else if (w[i] == 1 ) total[1]++;
            else total[2]++;
        }
        dfs(1,-1);//从根开始搜，根其实从哪开始都行
        output();


    }
    public void add(int u) {
        if (w[u] == 0) f[u][0]++;
        else if (w[u] == 1) f[u][1]++;
        else f[u][2]++;
    }
    public void dfs(int u,int fa) {
        add(u);
        for (Integer node:g.get(u)) {
            if (node == fa) continue;
            dfs(node,u);
            //加上子节点的
            for (int i=0;i<3;i++)
                f[u][i] += f[node][i];
        }
        if (f[u][0]>=1 && f[u][1]>=1 && f[u][2] >=1 && total[0] > f[u][0] && total[1] > f[u][1] && total[2] > f[u][2])
            ans++;
    }
    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1064 extends Problem {
    long l,r;
    int[] w;
    List<List<Integer>> g = new ArrayList<>();

    @Override
    public void input() {
        n = scan.nextInt();
        w = new int[n+1];
        for (int i=0;i<=n;i++) {
            g.add(new ArrayList<>());
        }
        l = scan.nextLong();
        r = scan.nextLong();
        scan.nextLine();
        str = scan.nextLine();

        for (int i=1;i<=n;i++) {
            w[i] = str.charAt(i-1) - '0';
        }
        for (int i=2;i<=n;i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        for (int i=1;i<=n;i++) {
            dfs(i,-1,0);
        }
        output();
    }
    public void dfs(int u,int fa,long sum) {
        sum = (sum<<1) + w[u];//这不就好了！

        if (sum > r) return;
        if (fa!=-1 && sum>=l && sum <=r) {
            ans++;
        }


        for (Integer node:g.get(u)) {
            if (node == fa) continue;
            dfs(node,u,sum);
        }
    }
    public void dfs(int u,int fa,List<Integer> path) {
        path.add(w[u]);

        if (path.size() >= 2) {
            long sum = 0;
            //每条路径求和，共n*(n-1)条，则复杂度n*(n-1)*(路径的点数)，最差时路径有n个点
            // 若是链表，遍历到最后一个节点需要n-1次，此时还需要n次操作；复杂度又上升到n^3,直接类比前缀和，用移位的思想
            for (int i=0;i<path.size();i++) {
                int size = path.size()-1;
                if ( path.get(i) == 1 ) sum += (int) Math.pow(2,size - i);
            }


            if (sum >= l && sum <= r) {
//                System.out.println(path);
//                System.out.println(sum);
                ans++;
            }
            else if (sum > r) return;
        }
        for (Integer node:g.get(u)) {
            if (node == fa) continue;
//            System.out.print(node + path.toString()+"\n");
            dfs(node,u,path);//传的是引用变量，相当于指针，所以需要回溯
            path.remove((Integer) w[node]);//回溯,必须包装！！！，否则会移除下标对应元素
//            System.out.print("back" + path.toString() + "\n");
        }
    }
    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1065 extends Problem {
    int[] w;
    int[] blue;
    List<List<Integer>> g = new ArrayList<>();
    public int dfs(int u) {
        int size = g.get(u).size();
        if (size == 0) w[u] = 1;
        else if (size == 1) {
            w[u] = dfs(g.get(u).get(0));
//            w[u] = w[g.get(u).get(0)];脑溢血的写法，没有继续递着搜索
        }
        else {
            w[u] = 0;
            for (Integer node : g.get(u)) {
                if (blue[u] == 1) {
                    w[u] += dfs(node);
                }
                else {
                    w[u] ^= dfs(node);//异或，按位异则取1
                }
            }
        }
        return w[u];
    }
    @Override
    public void input() {
        n = scan.nextInt();
        w = new int[n+1];
        blue = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=2;i<=n;i++) {
            int fa = scan.nextInt();
            g.get(fa).add(i);
        }
        for (int i=1;i<=n;i++)
            blue[i]  = scan.nextInt();

        ans = dfs(1);
        output();

    }

    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1058 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    List<Integer> res = new ArrayList<>();
    int[] w;
    @Override
    public void input() {
        n = scan.nextInt();
        w = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=1;i<=n-1;i++) {
            int a = scan.nextInt(),b = scan.nextInt();
            g.get(a).add(b);
            g.get(b).add(a);//题意并没有说输入一定是父指向子
        }
        output();
    }
    public int dfs(int u,int fa) {
        w[u] = 1;
        for (Integer node:g.get(u)) {
            if (node == fa) continue;//无向中的指向父
            w[u] += dfs(node,u);
        }
        return w[u];

    }

    @Override
    public void output() {
        dfs(1,-1);
        for (int i=1;i<=n;i++)
            System.out.print(w[i] + " ");
    }
}
class AkP1078 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    Queue<Integer> que = new ArrayDeque<>();
    List<Integer> tplist = new ArrayList<>();
    int[] degree;
    @Override
    public void input() {
        n = scan.nextInt();
        scan.nextLine();
        for (int i=0;i<=n;i++) {
            g.add(new ArrayList<>());
        }
        degree = new int[n+1];
        for (int i=1;i<=n;i++) {
            String[] str = scan.nextLine().split(" ");
            int m = Integer.parseInt(str[0]);
            for (int j=1;j<=m;j++) {
                int v = Integer.parseInt(str[j]);
                g.get(v).add(i);
                degree[i]++;
            }
        }
        tpsort();
        output();
    }
    public void tpsort() {
        for (int i=1;i<=n;i++) {
            if (degree[i] == 0) {
                que.offer(i);
                //dist[i] = 1;
            }
        }
        while (!que.isEmpty()) {
            int size = que.size();
//            System.out.println(que);
            for (int i=1;i<=size;i++) {
                int node = que.poll();
                tplist.add(node);
                for (Integer num : g.get(node)) {
                    degree[num]--;
                    if (degree[num] == 0) {
                        que.offer(num);
//                        dist[num] = dist[node] + 1;dist表示到根的距离，另一种写法：递推关系式
                    }
                }
            }
            ans++;

        }
    }

    @Override
    public void output() {
        if (tplist.size() == n ) System.out.println(ans);
        else System.out.println(-1);
    }
}
// 解除依赖则执行，若解除依赖后不需要马上执行呢？则不用求和
class AkP1079 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    Queue<Integer> que = new ArrayDeque<>();
    int[] w;
    int[] degree;
    @Override
    public void input() {
        n = scan.nextInt();
        w = new int[n+1];
        degree = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=1;i<=n;i++)
            w[i] = scan.nextInt();
        for (int i=1;i<=n;i++)
            for (int j=1;j<=n;j++) {
                int a = scan.nextInt();
                if (a == 1 ) {
                    g.get(j).add(i);
                    degree[i]++;
                }

            }
        tpsort();
        output();

    }
    public void tpsort() {
        for (int i=1;i<=n;i++) {
            if (degree[i] == 0) {
                que.offer(i);
            }
        }
        while (!que.isEmpty()) {
            int memory = 0,sz = que.size();
            while (sz-->0) {//同层相加,原写法是错的,会多算下层的与这层未清空的
                int node = que.poll();
                memory += w[node];
                list.add(node);
                for (Integer num : g.get(node)) {
                    degree[num]--;
                    if (degree[num] == 0) {
                        que.offer(num);

                    }
                }
            }
            ans = Math.max(memory,ans);
//            System.out.println(memory);

        }
        //错误的写法
        /*while (!que.isEmpty()) {
            int memory = 0;
            for (Integer m:que)
                memory+=w[m];
            ans = Math.max(ans,memory);
            System.out.println(memory);
            int node = que.poll();
            list.add(node);

            for (Integer num:g.get(node)) {
                degree[num]--;
                if (degree[num] == 0) {
                    que.offer(num);

                }
            }

        }

         */
    }



    @Override
    public void output() {
        if (list.size() == n) System.out.println(ans);
        else System.out.println(-1);
    }


}
class AkP1077 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    Queue<Integer> q = new ArrayDeque<>();
    int[] degree;
    int flag = 0;
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        degree = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        for (int i=0;i<m;i++) {
            int a = scan.nextInt(),b = scan.nextInt();
            g.get(a).add(b);
            degree[b]++;
        }
        output();
    }
    public int tpsort() {
        int res = 1;
        for (int i=1;i<=n;i++)
            if (degree[i] == 0)
                q.offer(i);
        while(!q.isEmpty()) {
            int node = q.poll();
            list.add(node);
            for (Integer num:g.get(node)) {
                degree[num]--;
                if (degree[num] == 0) {
                    q.offer(num);
                }
            }
            if (g.get(node).size()>1) res = 2;
        }
        if (list.size() < n) return 0;
        else return res;

    }

    @Override
    public void output() {
        ans = tpsort();
        if (ans == 0) System.out.println("No Results");
        else if (ans == 1) System.out.println("Only One Sorted Results");
        else System.out.println("Many Sorted Results");
    }
}
/* 优先队列
    小根堆：将编号小的点先进行出边删除
    大根堆：将编号大的…
 */
class Acw3704 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    PriorityQueue<Integer> que = new PriorityQueue<>();
    int[] degree;
    @Override
    public void input() {
        n = scan.nextInt();
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());
        degree = new int[n+1];
        m = scan.nextInt();
        for (int i=1;i<=m;i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            g.get(u).add(v);
            degree[v]++;
        }
        tpsort();
        output();

    }
    public void tpsort() {
        for (int i=1;i<=n;i++) {
            if (degree[i] == 0) {
                que.add(i);
            }
        }
        while(!que.isEmpty()) {
            Integer node = que.poll();
            list.add(node);
            for (Integer num:g.get(node)) {
                degree[num]--;
                if (degree[num] == 0) {
                    que.add(num);
                }
            }

        }
    }

    @Override
    public void output() {
        for (Integer num:list)
            System.out.print(num+" ");
    }
}
class AkP1076 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    int[] inDegree;
    Queue<Integer> q = new ArrayDeque<>();
    List<Integer> tplist = new ArrayList<>();
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        inDegree = new int[n+1];
        for (int i=0;i<=n;i++)
            g.add(new ArrayList<>());


        for (int i=0;i<m;i++) {
            int u = scan.nextInt(),v = scan.nextInt();
            g.get(u).add(v);
            inDegree[v]++;
        }
        tpsort();
        output();
    }
    public void tpsort() {
        for (int i=1;i<=n;i++) {
            if (inDegree[i] == 0) {
                tplist.add(i);
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int node = q.poll();
//            tplist.add(node); 在这里入拓扑表即可
            for (Integer num:g.get(node)) {
                inDegree[num]--;
                if (inDegree[num] == 0) {
                    tplist.add(num);
                    q.offer(num);
                }
            }
//            g.remove(node);入度减一已相当于删除边，这里多余，会造成数组变小而越界
        }

    }
    @Override
    public void output() {
        if (tplist.size() == n) System.out.println("YES");
        else System.out.println(tplist.size());
    }
}
class AkP1055 extends Problem {
    List<List<Integer>> g = new ArrayList<>();
    public void dfs(int u,int fa) {
        //do
        for (Integer num:g.get(u)) {
            if (num == fa) continue;
            dfs(num,u);//表示u已经访问过
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        for (int i=0;i<=n;i++) {
            g.add(new ArrayList<>());
        }
        for (int i=1;i<n;i++) {
            int u = scan.nextInt(),v = scan.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        for (List<Integer> list:g) {
            int cnt = 0;
            for (Integer num:list) {
//                dfs(num,-1);
                cnt++;
            }
            if (cnt == 2)
                ans++;
        }
        output();
    }

    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1054 extends Problem {

    @Override
    public void input() {
        T = scan.nextInt();
        while(T-->0) {
            n = scan.nextInt();
            m = scan.nextInt();
            long max = (long) n *(n-1)>>1;
            if (m>= n-1 && m <= max) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    @Override
    public void output() {

    }
}
class AkP1071 extends Problem {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    long res = Integer.MAX_VALUE;
    public void query(int n,int m) {
        char[][] mat = new char[n][m];
        for (int i = 0;i<n;i++) {
            for (int j=0;j<m;j++ ) {
                int index = i*m + j;
                mat[i][j] = str.charAt(index);
            }
        }
        boolean[][] visit = new boolean[n][m];
        int cnt = 0;
        for (int i = 0;i<n;i++) {
            for (int j=0;j<m;j++ ) {
                if (!visit[i][j]) {
                    dfs(i, j, mat[i][j], n, m, visit, mat);
                    cnt++;
                }
            }
        }
//        System.out.println(cnt);
        res = Math.min(cnt,res);
    }
    @Override
    public void input() {
        n = scan.nextInt();
        scan.nextLine();
        str = scan.nextLine();

        for (int k=1;k<=n;k++) {
            int row = 0,col = 0;
            if (n % k == 0) {
                row = k;
                col = n/k;
                query(row,col);
            }
        }
        output();
    }
    public void dfs(int x,int y,char c,int n,int m,boolean[][] visit,char[][] mat) {
        if (x < 0 || y< 0 || x == n || y == m || mat[x][y] != c ||  visit[x][y])
            return;
        visit[x][y] = true;
        for (int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(nx,ny,c,n,m,visit,mat);
        }

    }
    @Override
    public void output() {
        System.out.println(res);
    }
}
class AkP1070 extends Problem {
    int[] dx = {-1,0,1,0,-1,-1,1,1};
    int[] dy = {0,-1,0,1,-1,1,-1,1};
    int[][] mat;
    int cnt;
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        mat = new int[n][m];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                mat[i][j] = scan.nextInt();

        /*疑惑：连续性传染，与从哪里开始感染无关，只是传染的方向不同
            本质就是连通快
        */
        for (int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 ) {
                    dfs(i,j);
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
    public void dfs(int x,int y) {
        if (x < 0 || y < 0 || x== n || y == m || mat[x][y] == 0) {
            return;
        }
        mat[x][y] = 0;
        for (int i=0;i<8;i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            dfs(cx,cy);
        }
    }
    @Override
    public void output() {

    }
}
class AkP1069 extends Problem {
    char[][] mat,mat1;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    boolean[][] visit;
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        mat = new char[n][m];
        mat1 = new char[n][m];
        for (int i=0;i<n;i++) {
            String[] t = scan.nextLine().split(" ");
            for (int j = 0; j < m; j++) {
                mat[i][j] = t[j].charAt(0);
                mat1[i][j] = t[j].charAt(0);
            }
        }
        for (int i = 0; i < n;i++) {
            for (int j = 0; j < m; j++) {

                mat1[i][j] = 'W';
                int cnt = 0;
                boolean[][] visit = new boolean[n][m];
                for (int i1 = 0; i1 < n; i1++) {
                    for (int j1 = 0; j1 < m; j1++) {
                        if (mat1[i1][j1] == 'R' && !visit[i1][j1]) {
                            dfs(i1, j1, visit);
                            cnt++;
                        }
                    }
                }
                System.out.print(cnt + " ");
                if (mat[i][j] == 'R') mat1[i][j] = 'R';

            }
            System.out.println();
        }
    }
    public void dfs(int x,int y,boolean[][] visit) {
        if (x < 0 || y < 0 || x == n || y == m || mat1[x][y] == 'W' || visit[x][y])
            return;
        visit[x][y] = true;

        for (int k = 0;k<4;k++) {
            int cx = x + dx[k];
            int cy = y + dy[k];
            dfs(cx,cy,visit);
        }

    }

    @Override
    public void output() {

    }
}
class AkP1068 extends Problem {
    int res,res1;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    char[][] mat;
    char[][] mat1;
    boolean[][] visit;
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        mat = new char[n][m];
        mat1 = new char[n][m];
        visit = new boolean[n][m];

        for (int i=0;i<n;i++) {
            String t = scan.nextLine();
            for (int j = 0; j < m; j++) {
                mat[i][j] = t.charAt(j);
                mat1[i][j] = t.charAt(j);
                if (mat1[i][j] == 'G') mat1[i][j] = 'B';
            }
        }
//        System.out.println(Arrays.deepToString(mat));
//        System.out.println(Arrays.deepToString(mat1));
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++) {
                if (!visit[i][j]) {
                    dfs(i, j, mat, mat[i][j]);
                    res++;
                }

            }

        for (int i =0;i<n;i++)
            Arrays.fill(visit[i],false);

        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++) {
                if (!visit[i][j]) {
                    dfs(i, j, mat1, mat1[i][j]);
                    res1++;
                }
            }
        output();
    }
    public void dfs(int x,int y,char[][] matrix,char color) {
        if (x < 0 || y < 0 || x >= n || y >= m || matrix[x][y] != color || visit[x][y] )
            return;
        visit[x][y] = true;
        for (int k = 0;k<4;k++) {
            int cx = x + dx[k];
            int cy = y + dy[k];
            dfs(cx,cy,matrix,matrix[x][y]);
        }

    }

    @Override
    public void output() {
        System.out.println(res - res1);
    }
}
//并查集 + DFS两种写法，一般二维坐标建议DFS
class lc200{
    int n,m,ans;
    int[] root;
    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    class SolutionDisjoint {
        public void merge(int x,int y) {
            int rx = find(x),ry = find(y);
            if (rx != ry) {
                root[rx] = ry;
            }
        }
        public int find(int x) {
            if (root[x] == x) {
                return x;
            }
            else {
                root[x] = find(root[x]);
                return root[x];
            }
        }
        public int numIslands(char[][] grid) {
            n = grid.length;
            m = grid[0].length;
            for (int i=0;i<n*m;i++)
                root[i] = i;
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++) {
                    if (grid[i][j] == '1') {
                        for (int k=0;k<4;k++){
                            int cx = i + dx[k];
                            int cy = j + dy[k];
                            if (cx >=0 && cy >=0 && cx <n && cy < m && grid[cx][cy] == '1') {
                                int tmp = cx * m + cy;
                                int ctmp = i*m + j;
                                merge(ctmp,tmp);
                            }
                        }
                    }
                }
            for (int i=0;i<n*m;i++)
                if (root[i] == i)
                    ans++;
            return ans;
        }
    }
    class SolutionDfs {
        public int numIslands1(char[][] grid) {
            n = grid.length;
            m = grid[0].length;
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++) {
                    if (grid[i][j] == '1') {
                        dfs(i,j,grid);
                        ans++;
                    }
                }
            return ans;
        }
        public void dfs(int x,int y,char[][] grid) {
            if (x < 0 || y < 0 || x == n || y == m || grid[x][y] == '0')
                return;
            grid[x][y] = '0';//标记为访问过
            for (int k=0;k<4;k++) {
                int cx = x + dx[k];
                int cy = y + dy[k];
                dfs(cx,cy,grid);
            }
        }
    }

}
class AkP1050 extends Problem {
    int[] num;
    int[] sum = new int[4];
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();
    @Override
    public void input() {
        ans = 15*(int)1e5;
        n = scan.nextInt();
        nums = new int[n + 1];
        num = new int[n+1];
        for (int i = 1; i <= n; i++)
            nums[i] = scan.nextInt();
        Arrays.sort(nums);
        for (int j=1,i= n; i >= 1; i--) {
            num[i] = nums[j];
            j++;
        }
        dfs(1);
        output();


    }
    public void dfs(int u) {
        if (u == n + 1) {
            if (!list1.isEmpty() && !list2.isEmpty() && !list3.isEmpty()) {


                if (sum[1] > sum[2] && sum[2] > sum[3]) {
                    int res = sum[1] - sum[3];
                    ans = Math.min(ans,res);
                }
            }
        }
        else {
            //放入一等
            sum[1] += nums[u];
            list1.add(nums[u]);
            dfs(u+1);
            sum[1] -= nums[u];//回溯到未放入一等状态
            list1.remove((Integer) nums[u]);
            //放入二等
            sum[2] += nums[u];
            list2.add(nums[u]);
            dfs(u+1);
            sum[2] -= nums[u];
            list2.remove((Integer) nums[u]);
            //放入三等
            sum[3] += nums[u];
            list3.add(nums[u]);
            dfs(u+1);
            sum[3] -= nums[u];
            list3.remove((Integer) nums[u]);

        }
    }


    @Override
    public void output() {
        if (ans == 15*(int)1e5 ) System.out.println(0);
        else System.out.println(ans);
    }

}
class AkP1051 extends Problem {
    int x,y;
    int[] dx = {0,-1,0,1};
    int[] dy = {1,0,-1,0};
    @Override
    public void input() {
        x = scan.nextInt();
        y = scan.nextInt();
        dfs(0,"");
        output();
    }
    /* 伪代码
        迭代
    public void circle() {
        for (int state = 0; state < state_max; state++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cat[i][j] = false;
                    dog[i][j] = false;
                }
            }
            int State = state, cat_cnt = 0, dog_cnt = 0;
            for (int j = 0; j < 9; j++) {//9位三进制数
                int a = j / 3, b = j % 3;
                if (State % 3 == 1) {
                    cat[a][b] = true;
                    cat_cnt++;
                }
                if (State % 3 == 2) {
                    dog[a][b] = true;
                    dog_cnt++;
                }
                State /= 3;//相当于三进制的右移一位
            }
            if (cat_cnt != x || dog_cnt != y || !check()) {
                continue;
            }
            res++;
        }
    }

     */
    public void dfs(int u,String t) {
        if (u == 9) {
            int[][] mat = new int[3][3];
            int[] cnt = new int[3];
            for (int i=0;i<3;i++)
                for (int j=0;j<3;j++) {
                    int index = 3*i + j;
                    mat[i][j] = t.charAt(index) - '0';
                    cnt[mat[i][j]]++;
                }

            if (cnt[1]!= x || cnt[2] != y || !judge(mat)) {
                return;
            }

            ans ++;
        }
        //不放、放猫、放狗
        else {
            dfs(u + 1, t + "0");
            dfs(u + 1, t + "1");
            dfs(u + 1, t + "2");

        }
    }
    public boolean judge(int[][] mat) {
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++) {
                if (mat[i][j] == 1 || mat[i][j] == 2)
                    for (int k=0;k<4;k++) {
                        int cx = i + dx[k];
                        int cy = j + dy[k];
                        if (cx >=0 && cx <= 2 && cy >=0 && cy <= 2 && mat[cx][cy] == mat[i][j]) {

                            return false;
                        }
                    }

            }

        return true;
    }

    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1049 extends Problem {
    List<List<Integer>> list  = new ArrayList<List<Integer>>();
    List<Integer> tmp = new ArrayList<>();
    @Override
    public void input() {
        ans = 1000;
        for (int i=0;i<7;i++) {
            n = scan.nextInt();
            list.add(new ArrayList<>());
            List<Integer> list_tmp = new ArrayList<>();
            for (int j=0;j<n;j++) {
                list_tmp.add(scan.nextInt());
            }
            for (int k=0;k<=9;k++) {
                if (!list_tmp.contains(k)) {
                    list.get(i).add(k);
                }
            }
        }
        dfs(0,new ArrayList<>());
        output();

    }
    public void dfs(int u,List<Integer> tmp) {
        if (u == 10) {

//            System.out.println(tmp);
            for (List<Integer> temp:list) {
                boolean flag = false;
                for (Integer num:temp) {
                    if (tmp.contains(num)) {
                        flag = true;
                    }
                }
                if (!flag) return;
            }

            ans=Math.min(ans,tmp.size());
        }
        else {
            tmp.remove((Integer) u);
            dfs(u+1,tmp);
            tmp.add(u);
            dfs(u+1,tmp);
        }
    }

    @Override
    public void output() {
        if (ans == 1000) System.out.println(-1);
        else System.out.println(ans);
    }
}
class AkP1048 extends Problem {
    List<Integer> list = new ArrayList<>();
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n+1];
        for (int i=1;i<=n;i++)
            nums[i] = scan.nextInt();
        dfs(1);
        output();

    }
    public boolean judge(int num) {
        for (int i=2;i<=Math.sqrt(num);i++) {
            if (num%i == 0)
                return false;
        }
        return true;
    }
    public void dfs(int u) {
        if (u == n+1 ) {
            if (list.size() == k) {
                int sum = 0;
                for (Integer num:list)
                    sum += num;
                if (judge(sum)) ans++;
            }
        }
        else {
            list.remove((Integer)nums[u]);
//            if (list.contains(nums[u])) list.remove(nums[u]);错误的写法，remove会以为是下标
            dfs(u+1);
            list.add(nums[u]);
            dfs(u+1);
        }
    }
    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1047 extends Problem {
    long[] nums;
    @Override
    public void input() {
        n = scan.nextInt();
        nums = new long[n+1];
        for (int i=1;i<=n;i++)
            nums[i] = scan.nextLong();
        output();
    }
    //其实ans加上满足和大于等于0的集合中元素个数即可
    public void dfs(int u,long sum,int i,boolean flagi) {
        if (u == n + 1) {
            if (sum >= 0 && flagi) {
                ans ++;
            }
        }
        else {
            dfs(u+1,sum,i,flagi);
            if (u == i) flagi = true;
            dfs(u+1,sum+nums[u],i,flagi);

        }
    }

    @Override
    public void output() {
        for (int i=1;i<=n;i++)
            dfs(1,0,i,false);
        System.out.println(ans);
    }
}
class AkP1046 extends Problem {
    int[] pre;
    int x;

    @Override
    public void input() {
        x = scan.nextInt();
//        dfs(1,"",0);
//        if (ans == 0) System.out.println(-1);
//        else System.out.println(ans);
        output();//迭代法

    }

    public void dfs(int u,String t,int sum) {
        if (u == 10 || sum >= x) {
            if (sum == x) {
                int tmp = Integer.parseInt(t);
//                System.out.println(tmp);
                ans = Math.max(tmp,ans);
            }
        }
        else {
            dfs(u+1,u+t,sum + u);
            dfs(u+1,t,sum);
        }
    }
    @Override
    public void output() {
        for (int s = 1;s <= (1<<9) - 1;s++) {
            List<Integer> ans =  new ArrayList<>();
            int sum = 0;
            for (int i = 0;i< 9;i++) {//可以改成从高位枚举，就不用倒着输出
                boolean flag = ((s >> i) & 1) == 1;
                if (flag) {
                    ans.add(i+1);
                    sum+=i+1;

                }
            }
            if (sum == x) {
                StringBuilder t = new StringBuilder();
                for (int i = ans.size()-1;i>=0;i--) {
                    t.append(ans.get(i));
                }
//                System.out.println(t.toString());
                int max = Integer.parseInt(t.toString());
                this.ans = Math.max(this.ans,max);

            }

        }
        if (ans != 0) System.out.println(ans);
        else System.out.println(-1);

    }
}
class Acw92 extends Problem {

    @Override
    public void input() {
        n = scan.nextInt();
//        dfs(1,"");
        output();
    }
    public void dfs(int u,String t) {
        if (u == n + 1) {
            System.out.println(t);
        }
        else {
            dfs(u+1,t);
            dfs(u+1,t+ u + " ");
        }

    }

    @Override
    public void output() {
//        System.out.println(" ");
        for (int i = 1;i <= (1<<n);i++) {
            List<Integer> ans = new ArrayList<>();
            for (int j=0;j<n;j++) {//000各个位置对应321
                boolean flag = (((i>>j)&1) == 1);
                if (flag)  {
                    ans.add(j);
                }
            }
            for (int j=1;j<=n;j++) {//000各个位置对应123
                boolean flag = ((i<<j)&(int)Math.pow(2,n)) == (int)Math.pow(2,n);
                if (flag) {
                    ans.add(j);
                }
            }
            for (Integer num:ans) {
                System.out.print(num+" ");
            }
            System.out.println();//相当于空集了
        }
    }
}
class AkP1045 extends Problem {
    Set<String> set = new HashSet<>();
    @Override
    public void input() {
        str = scan.nextLine();
        n = str.length();
        dfs(0,0,"");
        output();
    }
    public void dfs(int u,int flag,String t) {
        if (u == n) {
            if (!t.isEmpty()) {
                if (t.contains("bengtie")) {
//                    System.out.println(t);
                    ans++;
                }
                return;
            }
        }
        else {
            if (flag == 0) dfs(u+1,1,t);
            dfs(u+1,0,t+str.charAt(u));
        }
    }
    @Override
    public void output() {
        System.out.println(ans);
    }
}
class AkP1044 extends Problem {
    List<String> ans = new ArrayList<>();
    @Override
    public void input() {
        str = scan.nextLine();
        n = str.length();
        dfs(0,"");
        Collections.sort(ans);
        for (String str:ans) {
            System.out.println(str);
        }
    }
    public void dfs(int u,String t) {
        if (u == n) {
            if (!t.isEmpty()) {
                ans.add(t);
            }
            return;
        }
        else {

            dfs(u+1,t+str.charAt(u));
            dfs(u+1,t);
        }
    }
    @Override
    public void output() {

    }
}
//此题有疑惑
class AkP1042 extends Problem {
    int bx,by,hx,hy;
    //右下左上
    int[] dx = {0,1};
    int[] dy = {1,0};
    int[][] limit = new int[12][12];


    @Override
    public void input() {
        bx = scan.nextInt();
        by = scan.nextInt();
        hx = scan.nextInt();
        hy = scan.nextInt();
        init();

        ans = dfs(0,0);

        output();
    }
    public int dfs(int x,int y) {
        int cnt = 0;



        if (x > bx || y > by || limit[x][y] == 1) return 0;

        if (x == bx && y == by) return 1;//这句必须在return 0之后？为什么，马控制区可能在目标点处

        for (int i=0;i<2;i++) {
            int nx = x,ny = y;
            nx += dx[i];
            ny += dy[i];
            cnt += dfs(nx,ny);
        }

        return cnt;

    }
    @Override
    public void output() {
        System.out.println(ans);
    }
    public void init() {
        if (hx>=1 && hy>=2) limit[hx-1][hy-2] = 1;
        if (hx>=1 && hy<=8) limit[hx-1][hy+2] = 1;
        if (hx<=9 && hy>=2) limit[hx+1][hy-2] = 1;
        if (hx<=9 && hy<=8) limit[hx+1][hy+2] = 1;
        if (hx>=2 && hy>=1) limit[hx-2][hy-1] = 1;
        if (hx>=2 && hy<=9) limit[hx-2][hy+1] = 1;
        if (hx<=8 && hy>=1) limit[hx+2][hy-1] = 1;
        if (hx<=8 && hy<=9) limit[hx+2][hy+1] = 1;
        limit[hx][hy] = 1;
    }

}

/* 进制类题
    借位判断 + 递归
    可表示证明：3^0+~+3^i，不够时借位，再减去，直到足够
 */
class AkP1041 extends Problem {
    int x;
    int flag = 1;
    boolean fx = false;
    @Override
    public void input() {
        x = scan.nextInt();

        int res = x;
        dfs1(x);
        output();
    }
    public void dfs1(int x) {
        if (x <= 1) {

            if ( x == 1 ) {
                if (flag == 1 && fx) System.out.print('+');
                System.out.print(x*flag);
            }
            return;
        }
        int mi = (int)(Math.log(x)/Math.log(3));
        int limit =(int) (Math.pow(3,mi+1) - 1 )/2;
        if (x > limit) {
            int out = (int)Math.pow(3,mi+1);
            if (flag == 1 && fx) System.out.print('+');

            System.out.print(out*flag);
            flag *= -1;//借位，溢出需变符号
            fx = true;
            dfs1(out - x);//传绝对值
        }
        else {
            int out = (int)Math.pow(3,mi);
            if (flag == 1 && fx) System.out.print('+');
            System.out.print(out*flag);
            fx = true;
            dfs1(x-out);
        }
    }
    //错误思路
    public void dfs(int x) {
        int mi = 1;
        if (Math.abs(x) == 1 || Math.abs(x) == 3) {
            System.out.println(Math.abs(x));
            return;
        }
        while (Math.pow(3,mi)<Math.abs(x)) {
            mi++;
        }

        int temp = (int)Math.pow(3,mi-1);
        int temp1 = (int)Math.pow(3,mi);

        if (temp1 - Math.abs(x) > Math.abs(x) - temp) {
            System.out.print(temp);
            if (x > 0) {
                System.out.print("+");
                dfs(x - temp);
            }
            else {
                System.out.print("-");
                dfs(-x - temp);
            }
        }
        else {
            System.out.print(temp1);
            if (x > 0) {
                System.out.print("-");
                dfs(x - temp1);
            }
            else {
                System.out.print("+");
                dfs( x + temp1);
            }
        }


    }
    @Override
    public void output() {

    }
}
/* 递归的二叉结构，问题可化为同类子问题，调用自身解决 */
class AkP1040 extends Problem {
    int[][] tr;
    long k;
    @Override
    public void input() {
        T = scan.nextInt();
        while(T-->0) {
            n = scan.nextInt();
            k = scan.nextLong();
            ans = dfs(n,k);
            output();
        }
    }
    public int dfs(int n,long k) {
        if (n == 1) return 0;
        else if (k % 2 ==0)
            return 1 - dfs(n-1,k/2);
        else
            return dfs(n-1,k/2);
    }

    @Override
    public void output() {
        if (ans == 0) System.out.println("red");
        else System.out.println("blue");
    }
}

class Acw3695 extends Problem {
    long k;
    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextLong();
        output();
    }
    public int dfs(int n,long k) {
        if (n == 1 || k % 2 != 0) return 1;
        else return dfs(n-1,k/2) + 1;
    }
    @Override
    public void output() {
        ans = dfs(n,k);
        System.out.println(ans);
    }
}
class AkP1101 extends Problem {

    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n+10];
        nums[1] = 1;
        nums[2] = 1;
        fb1(n);
//        output();
    }

    @Override
    public void output() {
        System.out.println(fb(n));
    }
    public void fb1(int n) {
        for (int i=3;i<=n;i++) {
            nums[i] = nums[i-1] + nums[i-2];
        }
        System.out.println(nums[n]);
    }
    public int fb(int n) {
        if (n == 1 || n == 2) return 1;

        return fb(n-1) + fb(n-2);
    }
}
class Acw1597 extends Problem {
    int[] root;
    int[] cnt;

    ArrayList<ArrayList<Integer>> list  = new ArrayList<>();
    public int find(int x) {
        if (root[x] == x)
            return x;
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y ) {
        int rx = find(x),ry = find(y);
        if (rx != ry) {
            cnt[ry] += cnt[rx];
            root[rx] = ry;
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();

        scan.nextLine();

        root = new int[n+1];
        cnt  = new int[n+1];
        for (int i=1;i<=n;i++) {
            root[i] = i;
            cnt[i] = 1;

        }
        for (int i=0;i<1001;i++)
            list.add(new ArrayList<>());

        for (int i=1;i<=n;i++) {
            String[] input = scan.nextLine().split(": ");
            int k = Integer.parseInt(input[0]);
            String[] num = input[1].split(" ");
            for (int j=0;j<k;j++) {
                int hob = Integer.parseInt(num[j]);
                list.get(hob).add(i);
            }
        }

        output();
    }

    @Override
    public void output() {
        for (int i=1;i<=1000;i++) {
            if (list.get(i).size()<2) continue;
            else {
                for (int j=0;j<list.get(i).size()-1;j++) {
                    merge(list.get(i).get(j),list.get(i).get(j+1));
                }
            }
        }
        List<Integer> cnt1 = new ArrayList<>();
        for (int i=1;i<=n;i++) {
            if (root[i] == i) {
                ans++;
                cnt1.add(cnt[i]);
            }
        }
        Collections.sort(cnt1,Collections.reverseOrder());
        System.out.println(ans);
        for (Integer num:cnt1)
            System.out.print(num+" ");
    }
}
class Acw3719 extends Problem {
    int[] root;
    public int find(int x) {
        if (root[x] == x)
            return x;
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y ) {
        int rx = find(x),ry = find(y);
        if (rx != ry) {
            root[rx] = ry;
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        root = new int[n+1];
        for (int i=1;i<=n;i++)
            root[i] = i;
        for (int i=0;i<m;i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            merge(x,y);
        }
        output();
    }

    @Override
    public void output() {
        int cnt = 0;
        for (int i=1;i<=n;i++) {
            if (root[i] == i) {
                cnt++;
            }
        }
        System.out.println(cnt-1);
    }
}
class Lc547 {
    int n,ans;
    int[] root;
    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        root = new int[n];
        for (int i=0;i<n;i++) {
            root[i] = i;
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (isConnected[i][j]==1) {
                    merge(i,j);
                }
            }
        }
        for (int i=0;i<n;i++) {
            if (root[i]==i) {
                ans++;
            }
        }
        return ans;
    }
    public int find (int x) {
        if (root[x] == x) {
            return x;
        }
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y ) {
        int rx = find(x),ry = find(y);
        if (rx != ry) {
            root[rx] = ry;
        }
    }
}
class AkP1033 extends Problem {
    public int find(int x) {
        if (root[x] == x) {
            return x;
        }
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y) {
        int rx = find(x),ry = find(y);
        if (rx != ry) {
            root[rx] = ry;
        }
    }
    int[] root;
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        root = new int[n];
        for (int i=0;i<n;i++) {
            root[i] = i;
        }
        for (int i=0;i<m;i++) {
            int x,y;
            x = scan.nextInt();
            y = scan.nextInt();
            merge(x,y);
        }
        output();
    }

    @Override
    public void output() {
        for (int i=0;i<n;i++) {
            if (root[i]==i) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
class AkP1032 extends Problem {
    int[][] matrix;
    int[] root;
    int[] xsd;
    public int find(int x) {
        if (root[x] == x)
            return x;
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y) {
        int rx = find(x),ry = find(y);
        xsd[ry] += matrix[x][y];
        if (rx != ry) {
            xsd[ry] += xsd[rx];
            root[rx] = ry;
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        root = new int[n];
        xsd = new int[n];
        matrix = new int[n][n];
        for (int i=0;i<n;i++)
            root[i] = i;
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++) {//错误的写法 j = i+1;
                matrix[i][j] = scan.nextInt();

                if (matrix[i][j] > 0 && i<j ) {
                    merge(i,j);
                }
            }
        output();
    }

    @Override
    public void output() {
        List<Integer> ans = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (root[i] == i) {
                ans.add(xsd[i]);
            }
        }
        Collections.sort(ans,Comparator.reverseOrder());

        for (int i=0;i<ans.size();i++)
            System.out.print(ans.get(i)+" ");
    }
}

class AkP1031 extends Problem {
    int p;
    int[] root;
    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        else {
            root[x] = find(root[x]);
            return root[x];
        }
    }
    public void merge(int x,int y) {
        int px = find(root[x]),py = find(root[y]);
        if (px != py) {
            root[px] = py;
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        p = scan.nextInt();
        root = new int[n+1];
        for (int i=1;i<=n;i++)
            root[i] = i;
        for (int i=0;i<m;i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            merge(x,y);

        }
        for (int i = 0;i < p;i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            if (find(x) == find(y))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    @Override
    public void output() {

    }
}
class AkP1030 extends Problem {
    int[] p;
    public void merge(int x,int y) {
        int px = find(p[x]),py = find(p[y]);
        if (px != py) {
            p[px] = py;
        }
    }
    public int find(int x) {
        if (x == p[x]) {
            return x;
        }
        else {
            p[x] = find(p[x]);
            return p[x];
        }
    }
    @Override
    public void input() {
        n = scan.nextInt();
        p = new int[n+1];
        for (int i=1;i<=n;i++)
            p[i] = i;
        m = scan.nextInt();
        for (int i=0;i<m;i++) {
            int z = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            if (z == 1) {
                merge(x,y);
            }
            else {
                if (find(x) == find(y)) System.out.println("Y");
                else System.out.println("N");
            }
        }
    }

    @Override
    public void output() {

    }
}
class AkP1021 extends Problem {
    int C;
    public boolean bisection(int str,int target) {
        int l = str;
        int r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return nums[l] == target;
    }

    @Override
    public void input() {
        n = scan.nextInt();
        C = scan.nextInt();
        nums = new int[n];
        for (int i=0;i<n;i++)
            nums[i] = scan.nextInt();

        Arrays.sort(nums);
        output();
    }

    @Override
    public void output() {
        for (int i=0;i<n;i++) {
            int goal = nums[i] + C;
            if (bisection(i,goal)) ans++;
        }
        System.out.println(ans);
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

class Acw3771 extends Problem {
    @Override
    public void input() {
        n = scan.nextInt();
        nums = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
        }
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
//        System.out.println(list.getFirst().getKey());不该注释

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
class Acw1230 extends Problem {

    @Override
    public void output() {
        input();
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//和为0
        int sum = 0;
        for (int i=1;i<n;i++) {
            sum += nums[i];
            sum %=k;

            ans += map.getOrDefault(sum, 0);


            map.put(sum,map.getOrDefault(sum,0)+1);
        }
//        System.out.println(map);
        System.out.println(ans);
    }

    @Override
    public void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n];
        for (int i=0;i<n;i++)
            nums[i] = scan.nextInt();
    }
}
class AkP1007 extends Problem {
    int[][][] sum;
    @Override
    public void output() {
        input();
        int q = scan.nextInt();
        while(q-->0) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();
            int[] cnt = new int[3];
            for (int i=0;i<3;i++) {
                cnt[i] = sum[i][x2][y2] - sum[i][x2][y1-1]
                        - sum[i][x1-1][y2] + sum[i][x1-1][y1-1];
                if (cnt[i]>0) ans++;
            }
//            System.out.println(Arrays.deepToString(sum[0]));
//            System.out.println(Arrays.deepToString(sum[1]));
//            System.out.println(Arrays.deepToString(sum[2]));
            System.out.println(ans);
            ans = 0;
        }
    }

    @Override
    public void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        scan.nextLine();
        sum = new int[3][n+1][m+1];
        for (int i=1;i<=n;i++) {
            String[] strs = scan.nextLine().split(" ");

            for (int j=1;j<=m;j++) {
                char c = strs[j-1].charAt(0);
                for (int k=0;k<3;k++) {
                    if (c - 'x' == k) {//c == map.get(k)用哈希表映射也可以
                        sum[k][i][j] = sum[k][i - 1][j]
                                + sum[k][i][j - 1]
                                - sum[k][i - 1][j - 1] + 1;
                    }
                    else {
                        sum[k][i][j] = sum[k][i - 1][j]
                                + sum[k][i][j - 1]
                                - sum[k][i - 1][j - 1];
                    }
                }
            }
        }
    }
}
class AkP1006 extends Problem {
    int[][] sum;
    @Override
    public void output() {
        input();
        for (int k=2;k<=n;k+=2) {
            int cnt = 0;
            for (int x1=1;x1<=n-k+1;x1++)
                for (int y1=1;y1<=n-k+1;y1++) {
                    int x2 = x1 + k - 1;
                    int y2 = y1 + k - 1;
                    int temp = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
                    if (temp % 2 == 0)
                        cnt++;
                }
            System.out.println(0);
            System.out.println(cnt);
        }
    }

    @Override
    public void input() {
        n = scan.nextInt();
        scan.nextLine();
        sum = new int[n+1][n+1];
        for (int i=1;i<=n;i++) {
            String str = scan.nextLine();
            for (int j=1;j<=n;j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] -
                        sum[i-1][j-1] + str.charAt(j-1) - '0';

            }
        }
    }
}
class AkP1017 extends Problem {
    int c;
    int[][] sum;
    int[] ansXY;
    @Override
    public void output() {
        input();
        ansXY = new int[2];
        for (int x1=1;x1<=n-c+1;x1++)
            for (int y1=1;y1<=k-c+1;y1++) {
                int x2=x1+c-1;
                int y2=y1+c-1;
                int temp = sum[x2][y2] - sum[x1-1][y2] -sum[x2][y1-1] + sum[x1-1][y1-1];
                if (temp >= ans) {
                    ans = temp;
                    ansXY = new int[]{x1,y1};
                }
            }
        System.out.printf("%d %d",ansXY[0],ansXY[1]);
    }

    @Override
    public void input() {
        ans = Integer.MIN_VALUE;
        n = scan.nextInt();
        k = scan.nextInt();
        c = scan.nextInt();
        sum = new int[n+2][k+2];
        for (int i=1;i<=n;i++)
            for (int j=1;j<=k;j++) {
                int temp = scan.nextInt();
                sum[i][j] = sum[i-1][j] + sum[i][j-1] -sum[i-1][j-1] + temp;
            }

    }
}
class AkP1016 extends Problem {
    int[][] s;
    int[][] num;
    @Override
    public void output() {
        input();
        for (int l=1;l<=n;l++)
            for (int l1=l;l1<=n;l1++)
                for (int r=1;r<=n;r++)
//                for (int l1=l;l1<=n;l1++)//注意这里从l开始，否则会逆算
                    for (int r1=r;r1<=n;r1++) {
                        int temp = s[l1][r1] - s[l1][r-1] - s[l-1][r1] + s[l-1][r-1];
                        ans = Math.max(temp,ans);
                    }
        System.out.println(ans);
    }

    @Override
    public void input() {
        n = scan.nextInt();
        s = new int[n+1][n+1];
        num = new int[n+1][n+1];

        for (int i=1;i<=n;i++)
            for (int j=1;j<=n;j++) {
                num[i][j] = scan.nextInt();
                s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + num[i][j];
            }

    }
}
class Acw733 extends Problem {
    String str;
    int index = 1;
    int[][] sum;
    @Override
    public void output() {
//        System.out.println(str);
        int cntAns = 0;
        sum = new int[n+1][26];
        for (int i=1;i<=n;i++) {
            for (int j=0;j<26;j++) {
                if (str.charAt(i-1) - 'A' == j)
                    sum[i][j] = sum[i-1][j] + 1;
                else
                    sum[i][j] = sum[i-1][j];
            }
        }
        while (k-->0) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            int cnt = 0;

            for (int i=0;i<26;i++) {
                if ( (sum[r][i] - sum[l-1][i]) % 2 == 1) {
                    cnt++;
                }
            }
            if (cnt <= 1)
                cntAns++;
        }
        System.out.printf("Case #%d: %d\n",index,cntAns);
        index++;

    }

    @Override
    public void input() {
        scan = new Scanner(System.in);
        T = scan.nextInt();
        while (T-->0) {
            n = scan.nextInt();
            k = scan.nextInt();
            scan.nextLine();//读掉n,k后要换行的回车
            str = scan.nextLine();//会读取回车，下句多余
//            scan.nextLine();
            output();
        }
    }
}
class AkP1005 extends Problem {
    int[] sum;
    @Override
    public void output() {
        input();
        while(k-->0) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            if ((sum[r] - sum[l-1]) % 3 ==0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    @Override
    public void input() {
        scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();
        nums = new int[n+1];
        sum = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
            sum[i] = sum[i - 1] + sumOfEach(nums[i]);
        }

    }
    public int sumOfEach(int nums) {
        int res = 0;
        while (nums!=0) {
            res += nums%10;
            nums/=10;
        }
        return res;
    }
}
class AkP1014 extends Problem {
    int[] sum;
    @Override
    public void output() {
        input();
        while(k-->0) {
            int l,r;
            l = scan.nextInt();
            r = scan.nextInt();
            System.out.println(sum[r] - sum[l-1]);
        }
    }
    @Override
    public void input() {
        scan = new Scanner(System.in);
        n = scan.nextInt();
        nums = new int[n+1];
        sum = new int[n+1];
        for (int i=1;i<=n;i++) {
            nums[i] = scan.nextInt();
            sum[i] = sum[i - 1] + nums[i];
        }
        k = scan.nextInt();

    }
}