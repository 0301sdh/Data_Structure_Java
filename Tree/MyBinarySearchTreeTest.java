package Tree;

public class MyBinarySearchTreeTest {
    public static void main(String[] args){
        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

        //        50
        //       /  \
        //     30    70
        //    /  \   /  \
        //  20   40 60   80
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("=== 중위 순회 (정렬 순서) ===");
        bst.inorder();    // 20 30 40 50 60 70 80

        System.out.println("=== 전위 순회 ===");
        bst.preorder();   // 50 30 20 40 70 60 80

        System.out.println("=== 후위 순회 ===");
        bst.postorder();  // 20 40 30 60 80 70 50

        System.out.println("=== 검색 ===");
        System.out.println("40 검색: " + bst.search(40));  // true
        System.out.println("99 검색: " + bst.search(99));  // false

        System.out.println("=== 최솟값 / 최댓값 ===");
        System.out.println("최솟값: " + bst.min());  // 20
        System.out.println("최댓값: " + bst.max());  // 80

        System.out.println("=== 삭제 ===");
        System.out.println("30 삭제 (자식 2개)");
        bst.delete(30);
        bst.inorder();    // 20 40 50 60 70 80

        System.out.println("20 삭제 (리프 노드)");
        bst.delete(20);
        bst.inorder();    // 40 50 60 70 80

        System.out.println("70 삭제 (자식 2개)");
        bst.delete(70);
        bst.inorder();    // 40 50 60 80
    }
}
