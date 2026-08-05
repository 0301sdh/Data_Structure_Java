package DisjointSet;

public class DisjointSet {
    private int[] parent; // 각 원소의 부모
    private int[] rank; // 각 트리의 랭크
    private int count; // 현재 그룹의 개수

    // 생성자 : 원소 0 ~ n-1 을 각각 크기 1인 집합으로 초기화
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);

        return parent[x];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
        count--;
    }

    public boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(6);
        System.out.println(ds.getCount());
        ds.union(1, 2);
        ds.union(3, 5);
        System.out.println(ds.getCount());
        ds.union(2, 5);
        System.out.println(ds.getCount());

        System.out.println(ds.isSameSet(1, 3));
        System.out.println(ds.isSameSet(1, 4));
    }
}
