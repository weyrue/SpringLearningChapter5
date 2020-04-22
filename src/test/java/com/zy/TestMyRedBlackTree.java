package com.zy;

import com.zy.utils.MyRedBlackTree;
import com.zy.utils.Node;
import org.junit.Test;

public class TestMyRedBlackTree {
    @Test
    public void testMyRedBlackTree() {
        MyRedBlackTree<Integer, Integer> tree = new MyRedBlackTree<>();
        tree.put(4, 1);
        tree.put(2, 1);
        tree.put(6, 1);
        tree.put(1, 1);
        tree.put(3, 1);
        tree.put(5, 1);
        tree.put(7, 1);

        tree.rotateRight(tree.getEntry(4));
        System.out.println(tree.toMarkdownString());
    }

    @Test
    public void testPreorderTraversal() {
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        Node<String> g = new Node<>("g");
        Node<String> h = new Node<>("h");
        Node<String> i = new Node<>("i");
        Node<String> j = new Node<>("j");
        Node<String> k = new Node<>("k");

        a.addLeft(b);
        a.addRight(c);
        b.addLeft(d);
        b.addRight(e);
        e.addLeft(f);
        e.addRight(g);
        c.addLeft(h);
        h.addLeft(j);
        h.addRight(k);
        c.addRight(i);

        System.out.println(a.inorderTraversal());
    }

}
