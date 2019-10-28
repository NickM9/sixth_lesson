package by.epam.tree;

public class Main {
    public static void main(String[] args) {

        ITree<Integer> tree = new Tree<Integer>();

        tree.add(1);
        tree.add(15);
        tree.add(11);
        tree.add(56);
        tree.add(10);
        tree.add(-13);
        tree.add(8);

        for (Integer i : tree) {
            System.out.print(i+" ");
        }
        System.out.println();

        System.out.println(tree.find(-13));

    }
}
