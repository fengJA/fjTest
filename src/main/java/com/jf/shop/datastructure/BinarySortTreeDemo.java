package com.jf.shop.datastructure;

import lombok.Data;

/**
 * @author fengj
 * @date 2019/10/13 -15:27
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new BinarySortTreeNode(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12
    }

}

class BinarySortTree{
    private BinarySortTreeNode root;

    public void add(BinarySortTreeNode node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    public BinarySortTreeNode search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    public BinarySortTreeNode searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public int delRightTreeMin(BinarySortTreeNode node){ // 从右子树找最小的，或者从左子树找最大的
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        del(node.getValue());
        return node.getValue();
    }

    public void del(int value){
        if (root == null){
            return;
        }
        BinarySortTreeNode targetNode = search(value);// 要删除的节点
        BinarySortTreeNode parent = searchParent(value);// 父节点
        if (targetNode == null ){
            return;
        }
        if (root.getLeft() == null && root.getRight() == null){
            root = null;
            return;
        }
        if (targetNode.getLeft() == null && targetNode.getRight() == null){
            if (parent.getLeft() != null && parent.getLeft().getValue() == targetNode.getValue()){
                parent.setLeft(null);
            }else if (parent.getRight() != null && parent.getRight().getValue() == value) {//是由子结点
                parent.setRight(null);
            }
        }else if (targetNode.getLeft() != null && targetNode.getRight() != null){
            int minValue = delRightTreeMin(targetNode.getRight());
            targetNode.setValue(minValue);
        }else {
            if (targetNode.getLeft() != null){ // 删除的节点有左子树
                if (parent != null){ // 就是只有一个根节点和一个左子树的时候，根节点的parent为空
                    if (parent.getLeft().getValue() == value){
                        parent.setLeft(targetNode.getLeft());
                    }else {
                        parent.setRight(targetNode.getRight());
                    }
                }else {
                    root = targetNode.getLeft();
                }
            }else {
                if (parent != null) {
                    if (parent.getLeft().getValue() == value) {
                        parent.setLeft(targetNode.getLeft());
                    } else {
                        parent.setRight(targetNode.getRight());
                    }
                }else {
                    root = targetNode.getRight();
                }
            }
        }
    }

}

@Data
class BinarySortTreeNode{
    private int value;
    private BinarySortTreeNode left;
    private BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }

    public void add(BinarySortTreeNode node){
        if (node == null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println("头结点是：" + this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public BinarySortTreeNode search(int value){
        if (value == this.value){
            return this;
        }else if (value < this.value){
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            if(this.right == null) {
                return null;
            }
            return this.right.search(value);

        }
    }

    // 查找父节点
    public BinarySortTreeNode searchParent(int value){
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }
    }
}
