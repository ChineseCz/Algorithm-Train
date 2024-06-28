import java.util.*;

public class Start {
    static final int N = (int)1e5+10;
    static ArrayList<Integer>[] g = new ArrayList[N];
    static long n, k;
    static long[] dist = new long[N];
    static int[] w = new int[N];

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int x : g[node]) {
                if (dist[x] > dist[node] + 1) {
                    dist[x] = dist[node] + 1;
                    q.add(x);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextLong();
        k = sc.nextLong();
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            g[i].add(w[i]);
        }
        bfs();
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= k) {
                System.out.print(i + " ");
            }
        }
    }
}