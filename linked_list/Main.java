package by.epam.linked_list;

public class Main{
    public static void main(String[] args) {

        LinkedContainer<String> list = new LinkedContainer<String>();
        list.addFirst("world");
        list.addFirst("Hello");
        list.addLast("!");

        for (String s : list) {
            System.out.print(s + " ");
        }

        System.out.println(list.size());
    }
}
