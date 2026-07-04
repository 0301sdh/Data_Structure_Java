package Tree;

public class MyRedBlackTreeTest {

    public static void main(String[] args) {
        MyRedBlackTree<Integer> tree = new MyRedBlackTree<>();
        int[] keys = { 10, 20, 30, 15, 25, 5, 1, 35, 40, 50 };

        // 1) 삽입
        System.out.println("=== 삽입 ===");
        for (int k : keys) {
            tree.insert(k);
            System.out.println("insert " + k + " -> balanced? " + tree.isBalanced());
        }

        System.out.println("\n=== 중위 순회 (오름차순이면 정상) ===");
        tree.inorder();

        // 2) 조회
        System.out.println("=== 조회 ===");
        System.out.println("contains 25 : " + tree.contains(25));
        System.out.println("contains 99 : " + tree.contains(99));

        // 3) 삭제 (리프/중간/루트 등 다양한 경우)
        System.out.println("\n=== 삭제 ===");
        int[] toDelete = { 1, 30, 10, 20 };
        for (int k : toDelete) {
            tree.delete(k);
            System.out.println("delete " + k + " -> balanced? " + tree.isBalanced());
        }

        System.out.println("\n=== 삭제 후 중위 순회 ===");
        tree.inorder();

        // 4) 대량 삽입/삭제 스트레스 테스트 (규칙이 계속 유지되는지)
        System.out.println("\n=== 스트레스 테스트 ===");
        MyRedBlackTree<Integer> big = new MyRedBlackTree<>();
        boolean ok = true;
        for (int i = 1; i <= 1000; i++) {
            big.insert(i);
            if (!big.isBalanced()) {
                ok = false;
                break;
            }
        }
        for (int i = 1; i <= 1000; i += 2) { // 홀수만 삭제
            big.delete(i);
            if (!big.isBalanced()) {
                ok = false;
                break;
            }
        }
        System.out.println("1000개 삽입 + 홀수 삭제 후 규칙 유지? " + ok);
    }

}
