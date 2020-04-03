package com.zy.utils;

import sun.reflect.generics.tree.Tree;

public class MyRedBlackTree<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    TreeNode<K, V> root;
    int size = 0;

    public String toMarkdownString() {
        return "```\n" + "graph TD\n" +
                markdownTravel(root) +
                "```\n";
    }

    private String markdownTravel(TreeNode<K, V> node) {
        StringBuilder sb = new StringBuilder();
        if (node.left != null) {
            sb.append(node.key);
            sb.append('-');
            sb.append(node.color == RED ? "R" : "B");
            sb.append("-->");
            sb.append(node.left.key);
            sb.append('-');
            sb.append(node.left.color == RED ? "R" : "B");
            sb.append('\n');
            sb.append(markdownTravel(node.left));
        }
        if (node.right != null) {
            sb.append(node.key);
            sb.append('-');
            sb.append(node.color == RED ? "R" : "B");
            sb.append("-->");
            sb.append(node.right.key);
            sb.append('-');
            sb.append(node.right.color == RED ? "R" : "B");
            sb.append('\n');
            sb.append(markdownTravel(node.right));
        }
        return sb.toString();
    }

//    public V remove(K key) {
//        TreeNode<K, V> p = getEntry(key);
//        if(p==null)
//            return null;
//
//        V oldValue = p.getValue();
//        deleteEntry(p);
//        return oldValue;
//    }

//    private void deleteEntry(Entry<K, V> p) {
//        Entry<K, V> s;
//        //p左右节点均不是null
//        if (p.left != null && p.right != null) {
//            //s是比p大的最小节点，为实际的删除点
//            s = p.right;
//            while (s.left != null) s = s.left;
//            //将p替换为s，同时p指向s
//            p.key = s.key;
//            p.value = s.value;
//            p = s;
//        }
//        //当p只有一个节点时，实际删除的节点是p
//        Entry<K, V> replacement = p.left != null ? p.left : p.right;
//        if (replacement != null) {
//            replacement.parent = p.parent;
//            if (p.parent == null) root = replacement;
//            else if (p == p.parent.left)
//                p.parent.left = replacement;
//            else
//                p.parent.right = replacement;
//
//            p.left = p.right = p.parent = null;
//            if (p.color == BLACK)
//                fixAfterDeletion(replacement);
//        } else if (p.parent == null) {
//            root = null;
//        } else {
//            if (p.color == BLACK)
//                fixAfterDeletion(p);
//            if (p.parent != null) {
//                if (p == p.parent.left)
//                    p.parent.left = null;
//                else
//                    p.parent.right = null;
//                p.parent = null;
//            }
//        }
//
//    }

//    private void fixAfterDeletion(Entry<K, V> x) {
//        if (x != null && x.color == RED) {
//            x.color = BLACK;
//            return;
//        }
//        while (x != root && x != null && x.color == BLACK) {
//            if (x == x.parent.left) {//p是左节点
//                Entry<K, V> s = x.parent.right;
//                //兄弟为红色（换色，左旋变为兄弟为黑色情况）
//                if (s != null && s.color == RED) {
//                    x.parent.color = RED;
//                    s.color = BLACK;
//                    rotateLeft(x.parent);
//                    s = x.parent.right;
//                }
//                //兄弟为黑色
//                if (s == null
//                        || (s.left == null || s.left.color == BLACK)
//                        && (s.right == null || s.right.color == BLACK)) {//黑左侄，黑右侄(兄弟换色)
//                    if (s != null) s.color = RED;
//                    if (x.parent.color == RED) {//红父则父换为黑色后平衡
//                        x.parent.color = BLACK;
//                        return;
//                    } else {//黑父需要继续向上平衡
//                        x = x.parent;
//                    }
//                } else {
//                    //红左侄，黑右侄（兄与左侄换色，右旋，变为下面的情况）
//                    if (s.right == null || s.right.color == BLACK) {
//                        s.color = RED;
//                        s.left.color = BLACK;
//                        rotateRight(s);
//                        s = x.parent.right;
//                    }
//                    //红右侄(父与兄换色，右侄由红变为黑，再左旋即平衡)
//                    s.color = x.parent.color;
//                    x.parent.color = BLACK;
//                    if (s.right != null) s.right.color = BLACK;
//                    rotateLeft(x.parent);
//                    return;
//                }
//            } else {//p是右节点
//                Entry<K, V> s = x.parent.left;
//                if (s != null && s.color == RED) {//红兄（兄父换色，右旋，变成下面的情况）
//                    x.parent.color = RED;
//                    s.color = BLACK;
//                    rotateRight(x);
//                    s = s.left;
//                }
//                //黑兄
//                if (s == null
//                        || (s.left == null || s.left.color == BLACK)
//                        && (s.right == null || s.right.color == BLACK)) {//黑左侄、黑右侄
//                    //兄换色
//                    if (s != null) s.color = RED;
//                    if (x.parent.color == RED) {//父为红色则父变色后平衡
//                        x.parent.color = BLACK;
//                        return;
//                    } else {//父为黑色则向上递归平衡
//                        x = x.parent;
//                    }
//                } else {
//                    //红右侄（右侄，兄换色，左旋，变为红左侄的情况）
//                    if (s.left == null || s.left.color == BLACK) {
//                        s.color = RED;
//                        s.right.color = BLACK;
//                        rotateLeft(s);
//                        s = x.parent.left;
//                    }
//                    //红左侄（左侄变色，兄与父换色，右旋，平衡）
//                    if(s==null){
//                        System.out.println(s.color);
//                    }
//                    s.left.color = BLACK;
//                    s.color = x.parent.color;
//                    x.parent.color = BLACK;
//                    rotateRight(x.parent);
//                    return;
//                }
//            }
//        }
//    }

    public TreeNode<K, V> getEntry(K key) {
        if (key == null) throw new NullPointerException();

        int cmp;
        Comparable<? super K> k = (Comparable<? super K>) key;
        TreeNode<K, V> x = root;

        while (x != null) {
            cmp = k.compareTo(x.getKey());
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }

        return null;
    }

    public V put(K key, V value) {
//        List<A> listA = new LinkedList<>();
//        List<? super A> list1 = listA;
//        List<? extends A> list2 = listA;
//        list1.add(new A());
////        list.add(new C());

//        new LinkedList<C>().add(new C());

        if (key == null) throw new NullPointerException("key is null");
        if (root == null) {
            root = new TreeNode<>(key, value, null);
            size = 1;
            return null;
        }
        TreeNode<K, V> t = root;
        TreeNode<K, V> parent;
        Comparable<? super K> k = (Comparable<? super K>) key;

        int cmp;
        do {
            parent = t;
            cmp = k.compareTo(t.key);
            if (cmp > 0)
                t = t.right;
            else if (cmp < 0)
                t = t.left;
            else
                return t.setValue(value);

        } while (t != null);
        TreeNode<K, V> e = new TreeNode<>(key, value, parent);
        if (cmp > 0) parent.right = e;
        else parent.left = e;
//        fixAfterInsertion(e);
        size++;

        return null;
    }

    private void fixAfterInsertion(TreeNode<K, V> x) {
//        x.color = RED;
//        //父节点为红色是需要修正，父节点为黑不影响红黑树结构
//        while (x != null && x != root && x.parent.color == RED) {
//            // 父节点为祖父节点的左枝
//            if (x.parent == x.parent.parent.left) {
//                // 叔叔为红色（红父红叔--换色，向上递归）
//                if (x.parent.parent.right != null && x.parent.parent.right.color == RED) {
//                    x.parent.color = BLACK;
//                    x.parent.parent.right.color = BLACK;
//                    x.parent.parent.color = RED;
//                    x = x.parent.parent;
//                }
//                // 叔叔为黑色（红父黑叔--若在内侧先向外旋转一次变为外侧情况；
//                // 外侧情况想将父亲和祖父位置的点换色，再向内旋转）
//                else {
//                    if (x.parent.right != null && x == x.parent.right) {
//                        x = x.parent;
//                        rotateLeft(x);
//                    }
//                    x.parent.color = BLACK;
//                    x.parent.parent.color = RED;
//                    x = x.parent;
//                    rotateRight(x.parent);
//                }
//            }
//            // 父节点为祖父节点的右枝
//            else {
//                // 叔叔为红色（红父红叔--换色，向上递归）
//                if (x.parent.parent.left != null && x.parent.parent.left.color == RED) {
//                    x.parent.color = BLACK;
//                    x.parent.parent.left.color = BLACK;
//                    x.parent.parent.color = RED;
//                    x = x.parent.parent;
//                }
//                // 叔叔为黑色（红父黑叔--若在内侧先向外旋转一次变为外侧情况；
//                // 外侧情况想将父亲和祖父位置的点换色，再向内旋转）
//                else {
//                    if (x.parent.left != null && x == x.parent.left) {
//                        x = x.parent;
//                        rotateRight(x);
//                    }
//                    x.parent.color = BLACK;
//                    x.parent.parent.color = RED;
//                    x = x.parent;
//                    rotateLeft(x.parent);
//                }
//            }
//        }
//
//        root.color = BLACK;
    }
    public void rotateLeft(TreeNode<K, V> p) {
        if (p != null) {
            TreeNode<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }

    }

    public void rotateRight(TreeNode<K, V> p) {
        if (p != null) {
            TreeNode<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    static final class TreeNode<K, V> {
        K key;
        V value;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> parent;
        boolean color = BLACK;

        public TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof TreeNode))
                return false;
            TreeNode<?, ?> e = (TreeNode<?, ?>) o;

            return (key != null && key.equals(e.getKey())) && (value != null && value.equals(e.getValue()));
        }

        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
}
