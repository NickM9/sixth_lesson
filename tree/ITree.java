package by.epam.tree;

import java.util.List;

public interface ITree<E> extends Iterable<E> {

    boolean add(E e);
    List<E> get();
    int size();
    Tree.Leaf find(E e);

}
