package learn.search_part_3.tree;

import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class BinaryTreeDemo1 {

    private Node root;

    //二叉树的节点数据结构
    private class Node {
        //序号
        private int key;
        //值
        private String data;
        private boolean isVisted;

        private Node leftChild = null;
        private Node rightChild = null;

        public Node() {
        }

        public Node(int key, String data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    /**
     * 创建一棵二叉树
     * <pre>
     *           A
     *     B          C
     *  D     E         F
     */
    public void createBinaryTree() {
        root = new Node(1, "A");
        Node nodeB = new Node(2, "B");
        Node nodeC = new Node(3, "C");
        Node nodeD = new Node(4, "D");
        Node nodeE = new Node(5, "E");
        Node nodeF = new Node(6, "F");

        root.leftChild = nodeB;
        root.rightChild = nodeC;

        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;

        nodeC.rightChild = nodeF;

    }


    public boolean isEmpty() {
        return root == null;
    }

    //树的高度
    public int height() {
        return height(root);
    }

    public int size() {
        return size(root);
    }

    private int height(Node subTree) {
        if (subTree == null) {
            return 0;
        } else {
            int i = height(subTree.leftChild);
            int j = height(subTree.rightChild);
            return i < j ? j + 1 : i + 1;
        }
    }

    private int size(Node subTree) {
        if (subTree == null) {
            return 0;
        } else {
            return 1 + size(subTree.leftChild) + size(subTree.rightChild);
        }
    }

    //寻找父节点也叫双亲节点
    public Node parent(Node element) {
        return (root == null || root == element) ? null : parent(root, element);
    }

    public Node parent(Node subTree, Node element) {
        if (subTree == null) {
            return null;
        } else {
            if (subTree.leftChild == element || subTree.rightChild == element) {
                return subTree;
            } else {
                //1 现在左子树递归查找，如果没有在右子树递归查找
                Node parentNode = parent(subTree.leftChild, element);
                if (parentNode != null) {
                    return parentNode;
                } else {
                    //2 现在右子树递归查找
                    parentNode = parent(subTree.rightChild, element);
                    return parentNode;
                }
            }
        }
    }

    public Node getLeftChildNode(Node element) {
        return (element == null) ? null : element.leftChild;
    }

    public Node getRightChildNode(Node element) {
        return (element == null) ? null : element.rightChild;
    }

    //在释放某个结点时，该结点的左右子树都已经释放，
    //所以应该采用后续遍历，当访问某个结点时将该结点的存储空间释放
    public void destroy(Node subTree) {
        //删除 根为subTree 的子树
        if (subTree != null) {
            //删除左子树
            destroy(subTree.leftChild);
            //删除右子树
            destroy(subTree.rightChild);
            //删除根节点
            subTree = null;
        }
    }

    public void traverse(Node subTree) {
        System.out.println("key:" + subTree.key + "--name:" + subTree.data);
        traverse(subTree.leftChild);
        traverse(subTree.rightChild);
    }

    public void visted(Node subTree) {
        subTree.isVisted = true;
        System.out.println("key:" + subTree.key + "--name:" + subTree.data);
    }

    //前序遍历
    public void preOrder(Node subTree) {
        if (subTree != null) {
            visted(subTree);
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    //中序遍历
    public void inOrder(Node subTree) {
        if (subTree != null) {
            inOrder(subTree.leftChild);
            visted(subTree);
            inOrder(subTree.rightChild);
        }
    }

    //后序遍历
    public void postOrder(Node subTree) {
        if (subTree != null) {
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            visted(subTree);
        }
    }
    //层序遍历

    //非递归前序遍历
    public void nonRecPreOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || stack.size() > 0) {
            while (node != null) {
                visted(node);
                stack.push(node);
                node = node.leftChild;
            }
            while (stack.size() > 0) {
                node = stack.pop();
                node = node.rightChild;
            }
        }
    }

    //非递归中序遍历
    public void nonRecInOder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                visted(node);
                node = node.rightChild;
            }
        }
    }

    //非递归后序遍历
    public void nonRecPostOrder(Node p) {
        Stack<Node> stack = new Stack<>();
        Node node = p;
        while (p != null) {
            //左子树入栈
            for (; p.leftChild != null; p = p.leftChild) {
                stack.push(p);
            }
            //当前节点无右子树或右子树已经输出
            while (p != null && (p.rightChild == null || p.rightChild == node)) {
                visted(p);
                //记录上一个已输出结点
                node = p;
                if (stack.isEmpty()) {
                    return;
                }
                p = stack.pop();
            }
            //处理右子树
            stack.push(p);
            p = p.rightChild;

        }
    }


    /**
     * @param root 树根节点
     *             层序遍历二叉树，用队列实现，
     *             先将根节点入队列，只要队列不为空，然后出队列，并访问，接着讲访问节点的左右子树依次入队列
     */
    public void levelTravel(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            visted(temp);
            if (temp.leftChild != null) q.add(temp.leftChild);
            if (temp.rightChild != null) q.add(temp.rightChild);
        }
    }


    public static void main(String[] args) {
        BinaryTreeDemo1 bt = new BinaryTreeDemo1();
        bt.createBinaryTree();
        System.out.println("the size of the tree is " + bt.size());
        System.out.println("the height of the tree is " + bt.height());

        System.out.println("*******(前序遍历)[ABDECF]遍历*****************");
        bt.preOrder(bt.root);

        System.out.println("*******(中序遍历)[DBEACF]遍历*****************");
        bt.inOrder(bt.root);

        System.out.println("*******(后序遍历)[DEBFCA]遍历*****************");
        bt.postOrder(bt.root);

        System.out.println("***非递归实现****(前序遍历)[ABDECF]遍历*****************");
        bt.nonRecPreOrder(bt.root);

        System.out.println("***非递归实现****(中序遍历)[DBEACF]遍历*****************");
        bt.nonRecInOder(bt.root);

        System.out.println("***非递归实现****(后序遍历)[DEBFCA]遍历*****************");
        bt.nonRecPostOrder(bt.root);

        System.out.println("***非递归实现****(层序遍历)[DEBFCA]遍历*****************");
        bt.levelTravel(bt.root);

    }

}
