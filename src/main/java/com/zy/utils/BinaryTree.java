package com.zy.utils;

public class BinaryTree<T> extends AbstractTree<T> {

    public BinaryTree(T v) {
        super(v);
    }

    @Override
    boolean addLeft() {
        return false;
    }

    @Override
    boolean addRight() {
        return false;
    }
}
