class TreeNode<K, V> {
    K key;
    V value;
    TreeNode<K, V> left, right;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        left = right = null;
    }
}

public class Tree <K extends Comparable<K>, V> {
    private TreeNode<K, V> root;

    public Tree() {
        root = null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private TreeNode<K, V> put(TreeNode<K, V> node, K key, V value) {
        if (node == null) return new TreeNode<>(key, value);
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        
        return node;
    }

    public V get(K key) {
        TreeNode<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private TreeNode<K, V> getNode(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);
        } else if (cmp > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode<K, V> node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode<K, V> node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    public int height(K key) {
        TreeNode<K, V> node = getNode(root, key);
        return node == null ? -1 : height(node);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public int size(K key) {
        TreeNode<K, V> node = getNode(root, key);
        return node == null ? -1 : size(node);
    }
}
