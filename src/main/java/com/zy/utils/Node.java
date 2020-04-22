package com.zy.utils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Node<T> {
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T value;

    /**
     * 非递归实现中序遍历
     *
     * @return 中序遍历数组
     * @author Yi
     * @since 4/22/2020
     */
    public List<T> inorderTraversal() {
        List<T> inorderTraversalList = new ArrayList<>();

        Node<T> p = this;
        while (p.left != null) p = p.left;
        while (p != null) {
            inorderTraversalList.add(p.value);
            //若p有右子节点，则下一个是p的右枝的最左节点
            if (p.right != null) {
                p = p.right;
                while (p.left != null) p = p.left;
                continue;
            }
            //若p没有右子节点
            //若p是父节点的右枝，则向上寻找直到p是父节点的左枝
            while (p.parent != null && p == p.parent.right) p = p.parent;
            //若寻找到根节点则返回
            if (p.parent == null) return inorderTraversalList;
            p = p.parent;
        }

        return inorderTraversalList;
    }

    /**
     * 非递归实现前序遍历
     *
     * @return 前序遍历数组
     * @author Yi
     * @since 4/22/2020
     */
    public List<T> preorderTraversal() {
        List<T> preorderTraversalList = new ArrayList<>();
        Node<T> p = this;

        while (p != null) {
            preorderTraversalList.add(p.value);

            //若p有左子节点，则下一个是p的左子节点
            if (p.getLeft() != null) {
                p = p.getLeft();
                continue;
            }
            //若p没有左子节点
            //若p有右子节点，则下一个是p的右子节点
            if (p.getRight() != null) {
                p = p.getRight();
                continue;
            }

            //若p没有左子节点,也没有右子节点
            //若p是父节点的右枝，则向上寻找，直到p是父节点的左枝
            while (p != this) {
                while (p.getParent() != null && p == p.getParent().getRight()) p = p.getParent();
                //若p是根节点，遍历结束
                if (p.getParent() == null) return preorderTraversalList;
                //若p是父节点的左枝
                //若p的父节点有右枝，则下一节点为父节点右枝，否则向上寻找
                if (p.getParent().getRight() != null) {
                    p = p.getParent().getRight();
                    break;
                }
                p = p.getParent();
            }
        }
        return preorderTraversalList;
    }

    public boolean addLeft(Node<T> l) {
        if (l == null) return false;
        l.parent = this;
        this.left = l;
        return true;
    }

    public boolean addRight(Node<T> r) {
        if (r == null) return false;
        r.parent = this;
        this.right = r;
        return true;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node(T value) {
        this(null, value);
    }

    public Node(Node<T> parent, T value) {
        this(parent, null, null, value);
    }

    public Node(Node<T> parent, Node<T> left, Node<T> right, T value) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
