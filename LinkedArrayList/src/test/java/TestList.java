import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestList {

    LinkedArrayList<Integer> list = new LinkedArrayList();

    @Test
    public void sizeTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void addTest() {
        list.addAll(List.of(5, 6, 7));
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(0);
        list.add(8);
        list.add(9);
        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, list.toArray());
    }

    @Test
    public void IsEmptyTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.removeAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void containsTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assertions.assertFalse(list.contains(13));
    }

    @Test
    public void containsAll() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        Assertions.assertTrue(list.containsAll(List.of(0, 1, 2, 3, 4, 12)));
    }

    @Test
    public void IteratorTest() {
        int i = 0;
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (Integer integer : list) {
            Assertions.assertEquals(i, integer);
            i++;
        }
    }

    @Test
    public void clearTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void getTest() {
        list.addAll(List.of(6, 5, 5, 1, 3, 8, 9, 7, 8, 2));
        int k = list.get(4);
        Assertions.assertEquals(3, k);
    }

    @Test
    public void removeByIndexTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.remove(0);
        list.remove(2);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 4, 5, 6, 7, 8, 9}, list.toArray());
    }

    @Test
    public void addByIndexTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.add(0, 7);
        list.add(2, 3);
        Assertions.assertArrayEquals(new Integer[]{7, 0, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9}, list.toArray());
    }

    @Test
    public void removeByValueTest() {
        list.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.remove(2);
        Assertions.assertArrayEquals(new Integer[]{0, 1, 3, 4, 5, 6, 7, 8, 9}, list.toArray());
    }

    @Test
    public void indexOfTest() {
        list.addAll(List.of(22, 33, 42, 55, 46, 524, 6, 77, 89, 9));
        Assertions.assertEquals(4, list.indexOf(46));
    }

    @Test
    public void retainTest() {
        list.addAll(List.of(22, 33, 42, 55, 46, 524, 6, 77, 89, 9));
        list.retainAll(List.of(11, 44, 22, 33, 42, 55));
        System.out.println("list.toArray() = " + Arrays.toString(list.toArray()));
        Assertions.assertArrayEquals(new Integer[]{22, 33, 42, 55}, list.toArray());
    }

}
