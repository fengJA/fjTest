package com.jf.shop.datastructure;

import lombok.Data;

/**
 * @author fengj
 * @date 2019/10/13 -19:44
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new AVLTreeNode(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }
}

class AVLTree{
    private AVLTreeNode root;

    public AVLTreeNode getRoot() {
        return root;
    }

    // 查找要删除的结点
    public AVLTreeNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父结点
    public AVLTreeNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 编写方法:
    // 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
    // 2. 删除node 为根结点的二叉排序树的最小结点
    /**
     *
     * @param node
     *            传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(AVLTreeNode node) {
        AVLTreeNode target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        // 这时 target就指向了最小结点
        // 删除最小结点
        delNode(target.getValue());
        return target.getValue();
    }

    // 删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1.需求先去找到要删除的结点 targetNode
            AVLTreeNode targetNode = search(value);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉排序树只有一个结点
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }

            // 去找到targetNode的父结点
            AVLTreeNode parent = searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                // 判断targetNode 是父结点的左子结点，还是右子结点
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) { // 是左子结点
                    parent.setLeft(null);
                } else if (parent.getRight() != null && parent.getRight().getValue() == value) {// 是由子结点
                    parent.setRight(null); ;
                }
            } else if (targetNode.getLeft() != null && targetNode.getRight() != null) { // 删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.getRight());
                targetNode.setValue(minVal); ;

            } else { // 删除只有一颗子树的结点
                // 如果要删除的结点有左子结点
                if (targetNode.getLeft() != null) {
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子结点
                        if (parent.getLeft().getValue() == value) {
                            parent.setLeft(targetNode.getLeft());;
                        } else { // targetNode 是 parent 的右子结点
                            parent.setRight(targetNode.getLeft()); ;
                        }
                    } else {
                        root = targetNode.getLeft();
                    }
                } else { // 如果要删除的结点有右子结点
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

    // 添加结点的方法
    public void add(AVLTreeNode node) {
        if (root == null) {
            root = node;// 如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }



}

@Data
class AVLTreeNode{
    private int value;
    private AVLTreeNode left;
    private AVLTreeNode right;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    // 树的总的高度
    public int height(){
        return Math.max(left == null ? 0:left.height(),right == null ? 0:right.height()) + 1;
    }

    public int leftHeight(){ // 左子树高度
        if(left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 左旋转
    public void leftRotate(){
        //创建新的结点，以当前根结点的值
        AVLTreeNode newNode = new AVLTreeNode(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成带你过去结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        AVLTreeNode newNode = new AVLTreeNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    public void add(AVLTreeNode node){
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

        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if (rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                //先对右子结点进行右旋转
                right.rightRotate();
            }else {
                right.leftRotate(); // 右子树的右子树的高度大于他左子树的高度
            }
            //然后在对当前结点进行左旋转
            leftRotate(); //左旋转..
            return;
        }
        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
            }else {
                left.rightRotate();
            }
            //再对当前结点进行右旋转
            rightRotate();
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

    public AVLTreeNode search(int value){
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
    public AVLTreeNode searchParent(int value){
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
