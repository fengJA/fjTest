package com.jf.shop.datastructure;

import lombok.Data;

/**
 * @author fengj
 * @date 2019/10/12 -21:52
 */
public class TreeDemo {
    public static void main(String[] args) {

    }
}

//定义BinaryTree 二叉树
class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //删除结点
    public void delNode(int no) {
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }
    //前序遍历
    public void preOrder() {
        if(this.root != null) {
            this.root.preSort();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public TreeNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序遍历
    public TreeNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public TreeNode postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}


@Data
class TreeNode {
    private int no;
    private String name;
    private TreeNode left; //默认null
    private TreeNode right; //默认null

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {

        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if(this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if(this.left != null) {
            this.left.delNode(no);
        }
        //5.则应当向右子树进行递归删除
        if(this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历查找
    /**
     *
     * @param no 查找no
     * @return 如果找到就返回该Node ,如果没有找到返回 null
     */
    public TreeNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前结点是不是
        if(this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        TreeNode resNode = null;
        if(this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null) {//说明我们左子树找到
            return resNode;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if(this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public TreeNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        TreeNode resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if(this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    //后序遍历查找
    public TreeNode postOrderSearch(int no) {

        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        TreeNode resNode = null;
        if(this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) {//说明在左子树找到
            return resNode;
        }

        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        //如果左右子树都没有找到，就比较当前结点是不是
        if(this.no == no) {
            return this;
        }
        return resNode;
    }

    // 前序遍历
    public void preSort(){
        System.out.println(this);// 先输出根节点
        if (this.left != null){
            this.left.preSort();
        }
        //递归向右子树前序遍历
        if(this.right != null) {
            this.right.preSort();
        }
    }
    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if(this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.left != null) {
            this.left.postOrder();
        }
        if(this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
