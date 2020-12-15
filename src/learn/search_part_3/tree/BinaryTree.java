package learn.search_part_3.tree;

import java.util.LinkedList;

/**
 *
 */
public class BinaryTree {
    //1 创建节点类
    public class TreeNode {
        public int data;
        public TreeNode leftNode;
        public TreeNode rightNode;

        public TreeNode(int data) {
            this.data = data;
        }
    }
    /**********************非递归***************************/


    //3-1 前序遍历
    //3-2 中序遍历
    //3-3 后序遍历
    //3-4 层序遍历

    /**********************递归***************************/
    //2 构建二叉树
    public static TreeNode creatBinaryTree(LinkedList<Integer> list) {
        TreeNode node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null) {
            node = new BinaryTree().new TreeNode(data);
            node.leftNode = creatBinaryTree(list);
            node.rightNode = creatBinaryTree(list);
        }
        return node;
    }

    //4-1 递归前序遍历
    public static void preOderTraversal(TreeNode node) {


    }
    //4-2 递归中序遍历
    //4-3 递归后序遍历
    //4-4 递归层序遍历

    public static void main(String[] args) {
        LinkedList dataList = new LinkedList();
        dataList.add(1);
        dataList.add(2);
        dataList.add(3);
        dataList.add(4);
        dataList.add(5);
        dataList.add(6);
        TreeNode binaryTree = creatBinaryTree(dataList);

    }

}
