package dataStructures.orderedDictionaries;

public class BSTTreeWithParent<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    BSTNodeWithParent<K, V> root;

    protected void createTreeWithParent(BinarySearchTree<K, V> tree){
        this.currentSize = tree.currentSize;
        root = buildTree(tree.root, null);
    }

    private BSTNodeWithParent<K, V> buildTree(BSTNode<K, V> node, BSTNodeWithParent<K, V> parent){
        if(node == null) return null;
        BSTNodeWithParent<K, V> nodeWithParent = new BSTNodeWithParent<>(node.getKey(), node.getValue(), null, null, parent);

        BSTNodeWithParent<K, V> L = buildTree(node.getLeft(), nodeWithParent);
        BSTNodeWithParent<K, V> R = buildTree(node.getRight(), nodeWithParent);

        nodeWithParent.setRight(R);
        nodeWithParent.setLeft(L);
        return nodeWithParent;
    }
}
