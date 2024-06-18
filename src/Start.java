import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    static List<Integer>[] tree;
    static char[] colors;
    static int n;
    static int[] redCount, greenCount, blueCount;
    static int totalRed, totalGreen, totalBlue;
    static int result = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {
            int parent = scanner.nextInt();
            tree[parent].add(i);
            tree[i].add(parent);
        }

        colors = scanner.next().toCharArray();

        redCount = new int[n + 1];
        greenCount = new int[n + 1];
        blueCount = new int[n + 1];

        // Count total colors
        for (char color : colors) {
            if (color == 'R') totalRed++;
            else if (color == 'G') totalGreen++;
            else if (color == 'B') totalBlue++;
        }

        // Start DFS from the root (node 1)
        dfs(1, -1);

        System.out.println(result);
    }

    private static void dfs(int node, int parent) {
        // Initialize the current node's color count
        if (colors[node - 1] == 'R') redCount[node]++;
        else if (colors[node - 1] == 'G') greenCount[node]++;
        else if (colors[node - 1] == 'B') blueCount[node]++;

        // Traverse all children
        for (int child : tree[node]) {
            if (child == parent) continue;
            dfs(child, node);
            // Accumulate the color count from the child to the current node
            redCount[node] += redCount[child];
            greenCount[node] += greenCount[child];
            blueCount[node] += blueCount[child];
        }

        // Check if cutting the edge between node and parent makes both subtrees colorful
        if (parent != -1) {
            int parentRed = totalRed - redCount[node];
            int parentGreen = totalGreen - greenCount[node];
            int parentBlue = totalBlue - blueCount[node];

            if (redCount[node] > 0 && greenCount[node] > 0 && blueCount[node] > 0 &&
                    parentRed > 0 && parentGreen > 0 && parentBlue > 0) {
                result++;
            }
        }
    }
}