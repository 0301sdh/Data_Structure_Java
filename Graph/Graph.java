package Graph;

import java.util.*;

public class Graph<T> {

    private Map<T, List<T>> adj;

    private boolean directed; // 방향 그래프 여부

    public Graph(boolean directed) {
        this.directed = directed;
        this.adj = new HashMap<>();
    }

    public void addVertex(T v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(T u, T v) {
        addVertex(u);
        addVertex(v);

        adj.get(u).add(v);
        if (!directed) { // 무방향이면 반대 방향도 연결
            adj.get(v).add(u);
        }
    }

    // u의 이웃 목록 반환
    public List<T> getNeighbors(T u) {
        return adj.getOrDefault(u, new ArrayList<>());
    }

    // 차수 (무방향 기준)
    public int degree(T u) {
        return adj.getOrDefault(u, new ArrayList<>()).size();
    }

    // u와 v가 인접한지 확인
    public boolean isAdjacent(T u, T v) {
        return adj.containsKey(u) && adj.get(u).contains(v);
    }

    // 그래프 출력
    public void print() {
        for (T x : adj.keySet()) {
            System.out.println(x + " -> " + adj.get(x));
        }
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>(false);
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "E");
        g.addEdge("D", "E");
        g.addEdge("E", "F");

        g.print();

        System.out.println("정점 E의 차수 : " + g.degree("E"));
        System.out.println("A와 B는 인접? : " + g.isAdjacent("A", "B"));

    }
}
