package by.epam.linked_list;

public interface Linked<E> {

    void addLast(E e);

    void addFirst(E e);

    int size();

    E getElementByIndex(int index);

}
