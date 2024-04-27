public class BinaryTreeTest {
    public static void main(String[] args) {
        Tree<Integer, String> tree = new Tree<>();

        // Inserting elements
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");
        tree.put(6, "Six");
        tree.put(8, "Eight");

        // Testing get
        System.out.println("Value of key 3: " + tree.get(3));
        System.out.println("Value of key 6: " + tree.get(6));
        System.out.println("Value of key 9: " + tree.get(9)); // Should print null

        // Testing isEmpty
        System.out.println("Is tree empty? " + tree.isEmpty()); // Should print false

        // Testing size
        System.out.println("Size of tree: " + tree.size());

        // Testing height
        System.out.println("Height of tree: " + tree.height());

        // Testing height of subtree
        System.out.println("Height of subtree rooted at 3: " + tree.height(3));

        // Testing contains
        System.out.println("Does tree contain key 7? " + tree.contains(7)); // Should print true
        System.out.println("Does tree contain key 10? " + tree.contains(10)); // Should print false

        // Testing size of subtree
        System.out.println("Size of subtree rooted at 5: " + tree.size(5));

        // Inserting more elements
        tree.put(1, "One");
        tree.put(9, "Nine");

        // Testing size after insertion
        System.out.println("Size of tree after insertion: " + tree.size());
    }
}
