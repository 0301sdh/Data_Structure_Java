package Tree;

public class MyAVLTreeTest {

    public static void main(String[] args) {

        MyAVLTree<Integer> tree = new MyAVLTree<>();

        int[] data = { 10, 20, 30, 40, 50, 25 };
        System.out.println("insert");
        for (int x : data) {
            tree.insert(x);
            tree.inorder();
            System.out.println(tree.isBalanced());
        }

        System.out.println("contains(25): " + tree.contains(25)); // true
        System.out.println("contains(99): " + tree.contains(99)); // false
        System.out.println("contains(40): " + tree.contains(40)); // true

        tree.delete(30);
        tree.inorder();
        System.out.println(tree.isBalanced());

        tree.delete(10);
        tree.inorder();
        System.out.println(tree.isBalanced());

        MyAVLTree<Integer> tree2 = new MyAVLTree<>();

        for (int i = 0; i < 10; i++) {
            tree2.insert(i);
        }
        tree2.inorder();
        System.out.println(tree2.isBalanced());
    }
}
