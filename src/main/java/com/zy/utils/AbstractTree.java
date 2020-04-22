package com.zy.utils;

public abstract class AbstractTree<T> {
    protected Node root;

    abstract boolean addLeft();

    abstract boolean addRight();

    public AbstractTree(T v) {
        this.root = new Node(null, null, null, v);
    }
}
