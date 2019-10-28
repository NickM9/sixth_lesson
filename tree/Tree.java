package by.epam.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<E> implements ITree<E> {

    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public Tree() {
        list = new LinkedList<>();
        root = new Leaf<>(null);
    }

    class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        private Leaf(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        @Override
        public int compareTo(Object o) {
            Leaf<E> node = (Leaf<E>) o;
            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((element == null) ? 0 : element.hashCode());
            return result;
        }

    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int count = 0;
            Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {

                return iterator.hasNext();
            }

            @Override
            public E next() {
                count++;
                return iterator.next().element;
            }

        };
    }

    private class TreeIterator<E> implements Iterator<Leaf<E>>{
        private Leaf<E> next;
        private TreeIterator(Leaf<E> root) {
            next = root;
            goToLeftmost();
        }
        private void  goToLeftmost() {
            while(next.left!=null) {
                next = next.left;
            }
        }
        @Override
        public boolean hasNext() {

            return next!=null&&next.element!=null;
        }
        @Override
        public Leaf<E> next() {
            Leaf<E> r = next;

            if(next.right!=null) {
                return goRight(r);
            }
            return goUp(r);
        }

        private Leaf<E> goRight(Leaf<E> r){
            next = next.right;
            while(next.left!=null) {
                next = next.left;
            }
            return r;
        }

        private Leaf<E> goUp (Leaf<E> r){
            while(true) {
                if(next.parent == null) {
                    next = null;
                    return r;
                }
                if(next.parent.left == next) {
                    next = next.parent;
                    return r;
                }
                next = next.parent;
            }

        }
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            return initRootLeaf(e);
        }

        Leaf<E> newNode = new Leaf<>(e);
        Leaf<E> lastNode = findLastLeaf(root, newNode);

        if (lastNode == null) {
            return false;
        }
        size++;
        newNode.parent = lastNode;
        if (lastNode.compareTo(newNode) < 0) {
            lastNode.right = newNode;
            return true;
        } else {
            lastNode.left = newNode;
            return true;
        }

    }

    private Leaf<E> findLastLeaf(final Leaf<E> oldLeaf, final Leaf<E> newLeaf) {
        int compare = oldLeaf.compareTo(newLeaf);
        Leaf<E> lastLeaf = oldLeaf;
        if (compare < 0 && oldLeaf.right != null) {
            lastLeaf = findLastLeaf(oldLeaf.right, newLeaf);
            return lastLeaf;
        }
        if (compare > 0 && oldLeaf.left != null) {
            lastLeaf = findLastLeaf(oldLeaf.left, newLeaf);
            return lastLeaf;
        }
        if (compare == 0)
            return null;
        return lastLeaf;
    }

    private boolean initRootLeaf(final E e) {
        root.element = e;
        size++;
        return true;
    }

    @Override
    public List<E> get() {
        for (E e : this) {
            list.add(e);
        }
        return list;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Leaf find(E e) {
        Leaf<E> eLeaf = new Leaf<>(e);
        return search(root, eLeaf);
    }

    private Leaf<E> search(Leaf<E> leaf, Leaf<E> eLeaf) {
        int compare = leaf.compareTo(eLeaf);

        if (compare < 0 && leaf.right != null) {
            return search(leaf.right, eLeaf);
        }
        if (compare > 0 && leaf.left != null) {
            return search(leaf.left, eLeaf);
        }
        if (compare == 0) {
            return leaf;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                ", list=" + list +
                ", size=" + size +
                '}';
    }
}
