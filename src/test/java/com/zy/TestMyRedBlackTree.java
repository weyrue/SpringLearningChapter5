package com.zy;

import com.zy.utils.MyRedBlackTree;
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
}
