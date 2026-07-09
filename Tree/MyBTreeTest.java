package Tree;

public class MyBTreeTest {

    public static void main(String[] args) {
        MyBTree<Integer> tree = new MyBTree<>(3);

        int[] data = { 10, 20, 5, 6, 12, 30, 7, 17, 3, 25, 1, 40, 22 };

        // 1) 삽입 후 정렬 확인
        System.out.println("=== 삽입 ===");
        for (int v : data) {
            tree.insert(v);
        }
        System.out.print("중위 순회 (오름차순이어야 함): ");
        tree.inorder(); // 1 3 5 6 7 10 12 17 20 22 25 30 40

        // 2) 탐색 확인
        System.out.println("\n=== 탐색 ===");
        System.out.println("contains(17) = " + tree.contains(17)); // true
        System.out.println("contains(6)  = " + tree.contains(6)); // true
        System.out.println("contains(99) = " + tree.contains(99)); // false
        System.out.println("contains(0)  = " + tree.contains(0)); // false

        // 3) 삭제 확인 (리프 / 내부 노드 / 병합·빌리기 유발)
        System.out.println("\n=== 삭제 ===");

        tree.delete(6); // 리프에 있는 키 삭제
        System.out.print("delete(6)  -> ");
        tree.inorder();

        tree.delete(20); // 내부 노드 키 삭제 (선행/후행키 교체 발생)
        System.out.print("delete(20) -> ");
        tree.inorder();

        tree.delete(1); // 최소치 노드 → borrow 또는 merge 유발
        System.out.print("delete(1)  -> ");
        tree.inorder();

        tree.delete(30);
        System.out.print("delete(30) -> ");
        tree.inorder();

        System.out.println("삭제 후 contains(20) = " + tree.contains(20)); // false
        System.out.println("삭제 후 contains(17) = " + tree.contains(17)); // true

        // 4) 없는 키 삭제 (아무 일도 안 일어나야 함)
        System.out.println("\n=== 없는 키 삭제 ===");
        tree.delete(999); // 트리에 없음 → 그냥 무시
        System.out.print("delete(999) -> ");
        tree.inorder();

        // 5) 전부 삭제 (빈 트리까지 잘 줄어드는지)
        System.out.println("\n=== 전부 삭제 ===");
        int[] remaining = { 10, 5, 7, 12, 17, 3, 25, 40, 22 };
        for (int v : remaining) {
            tree.delete(v);
        }
        System.out.print("전부 삭제 후 (빈 줄이어야 함): ");
        tree.inorder(); // (아무것도 안 나옴)
        System.out.println("contains(10) = " + tree.contains(10)); // false

        // 6) 문자열 타입도 되는지 (제네릭 확인)
        System.out.println("\n=== String 타입 ===");
        MyBTree<String> words = new MyBTree<>(2);
        String[] fruits = { "banana", "apple", "cherry", "date", "fig", "grape", "kiwi" };
        for (String w : fruits) {
            words.insert(w);
        }
        System.out.print("문자열 정렬: ");
        words.inorder(); // apple banana cherry date fig grape kiwi
    }

}
