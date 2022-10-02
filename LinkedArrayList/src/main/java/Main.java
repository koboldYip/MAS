import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new LinkedArrayList();
//        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            list.remove(iterator.next());
        }
        System.out.println("list.size = " + list.size());
        System.out.println("list.toArray() = " + Arrays.toString(list.toArray()));
        System.out.println("list.get(0) = " + list.get(0));
    }

}
