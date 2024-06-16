import java.util.*;

public class BuildMap {
    int n,m;
    Scanner scan = new Scanner(System.in);
    class Node {
        int next;
        int w;//边权
        public Node(int next,int w) {
            this.next = next;
            this.w = w;
        }
    }
    class AdjacencyListPoint {

        List<List<Integer>> g = new ArrayList<>();
        int[] w = new int[n];
        public void dfs(int u,int fa) {//有向图不需要father
            //do
            for (Integer num:g.get(u)) {
                if (num == fa) continue;
                dfs(num,u);//表示u已经访问过
            }
        }
        public void input() {
            n = scan.nextInt();
            m = scan.nextInt();
            for (int i=0;i<=n;i++)
                g.add(new ArrayList<Integer>());
            for (int i=0;i<m;i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                g.get(a).add(b);//建立a->b
            }
            for (int i=1;i<=n;i++) {
                w[i] = scan.nextInt();
            }
        }
    }
    class AdjacencyListLine {
        List<List<Node>> g = new ArrayList<>();
        public void dfs(int u,int fa) {
            for (Node t : g.get(u)) {//访问u节点的所有节点
                int x = t.next,w = t.w;
                if (x == fa) continue;//访问过，两点互通
                dfs(x,u);
                // do things
            }
        }
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
            dfs(1,-1);
        }


    }
    //离散化建图，字符串与字符串关系,利用哈希表;事实上我的负数
    class discrete {
        Map<Integer,List<Map.Entry<Integer,Integer>>> path = new HashMap<>();

        public void input() {
            int u = scan.nextInt();
            int v = scan.nextInt();
            int w = scan.nextInt();
            if (!path.containsKey(u))
                path.put(u,new ArrayList<>());
            path.get(u).add(new AbstractMap.SimpleEntry<>(v,w));

            int node = 1;
            if (path.containsKey(node)) {
                for (Map.Entry<Integer,Integer> entry : path.get(node)) {
                    int target = entry.getKey();
                    int weight = entry.getValue();
                }
            }
        }

    }

}
