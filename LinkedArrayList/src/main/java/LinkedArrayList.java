import java.util.*;

public class LinkedArrayList<E> implements Collection<E> {

    int size = 0;
    public Triplet<E> first;
    public Triplet<E> last;

    public LinkedArrayList() {
        updateTriplet();
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        int i = 0;
        for (E e : c) {
            add(index + i, e);
            i++;
        }
        return true;
    }


    public boolean removeAll(Collection<?> c) {
        c.stream()
                .filter(o -> indexOf(o) > -1)
                .forEach(o -> remove(indexOf(o)));
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        arrayToTriplets(c.stream().filter(this::contains).toArray());
        return true;
    }

    public void clear() {
        updateTriplet();
    }

    public E get(int index) {
        checkElementIndex(index);
        return this.stream().limit(index + 1).reduce((f, s) -> s).get();
    }

    public void add(int index, E element) {
        Object[] array = this.toArray();
        Object[] result = new Object[array.length + 1];
        System.arraycopy(array, 0, result, 0, index);
        result[index] = element;
        System.arraycopy(array, index, result, index + 1, array.length - index);

        arrayToTriplets(result);
    }

    public E remove(int index) {

        Object[] array = this.toArray();
        Object OldValue = array[index];
        Object[] result = new Object[array.length - 1];

        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        arrayToTriplets(result);
        return (E) OldValue;
    }

    private void arrayToTriplets(Object[] array) {
        updateTriplet();
        Arrays.stream(array).forEach(o -> addLast((E) o));
    }

    public int indexOf(Object o) {
        return (int) this.stream().takeWhile(e -> e != o).count();
    }

    void linkFirst(E e) {
        if (first == null && last == null) updateTriplet();
        if (first.item[0] != null) {
            final Triplet<E> f = first;
            final Triplet<E> triplet = new Triplet<>();
            triplet.item[4] = e;
            f.prev = triplet;
            triplet.next = first;
            first = triplet;
            size++;
        } else {
            for (int i = first.item.length - 1; i >= 0; i--) {
                if (first.item[i] == null) {
                    first.item[i] = e;
                    size++;
                    break;
                }
            }
        }
    }

    void linkLast(E e) {
        if (first == null && last == null) updateTriplet();
        if (last.item[4] != null) {
            final Triplet<E> l = last;
            final Triplet<E> triplet = new Triplet<>();
            triplet.item[0] = e;
            l.next = triplet;
            triplet.prev = last;
            last = triplet;
            size++;
        } else {
            for (int i = 0; i < last.item.length; i++) {
                if (last.item[i] == null) {
                    last.item[i] = e;
                    size++;
                    break;
                }
            }
        }
    }

    public E getLast() {
        for (int i = last.item.length - 1; i >= 0; i--) {
            if (last.item[i] != null) {
                return last.item[i];
            }
        }
        throw new NoSuchElementException();
    }

    public E getFirst() {
        for (int i = 0; i < first.item.length; i++) {
            if (first.item[i] != null) {
                return first.item[i];
            }
        }
        throw new NoSuchElementException();
    }

    void unlinkFirst() {
        for (int i = 0; i < first.item.length; i++) {
            if (first.item[i] != null) {
                if (i == first.item.length - 1) {
                    first.item = null;
                    if (first.next == null)
                        last = null;
                    else {
                        first.next.prev = null;
                        first = first.next;
                    }
                } else {
                    first.item[i] = null;
                }
                size--;
                break;
            }
        }
    }


    void unlinkLast() {
        for (int i = last.item.length - 1; i >= 0; i--) {
            if (last.item[i] != null) {
                if (i == 0) {
                    last.item = null;
                    if (last.prev == null)
                        first = null;
                    else last.prev.next = null;
                    last = last.prev;
                } else {
                    last.item[i] = null;
                }
                size--;
                break;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return this.stream().anyMatch(e -> e == (E) o);
    }

    void updateTriplet() {
        first = new Triplet<>();
        last = first;
        size = 0;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    public int size() {
        return size;
    }

    public void removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        else unlinkLast();
    }

    public void removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        else unlinkFirst();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedArrayListIterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return (T[]) array;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean remove(Object o) {
        remove(indexOf(o));
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    class LinkedArrayListIterator implements Iterator<E> {

        private Triplet<E> currentTriplet = first;
        private int lastReturned;
        private int count = 1;

        public LinkedArrayListIterator() {
            for (int i = 0; i < currentTriplet.item.length; i++) {
                if (currentTriplet.item[i] != null) {
                    lastReturned = i;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return count <= size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            count++;
            if (lastReturned == 5) {
                if (currentTriplet.next != null) currentTriplet = currentTriplet.next;
                lastReturned = 0;
            }
            return currentTriplet.item[lastReturned++];
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException();
    }


    private static class Triplet<E> {
        E[] item;
        Triplet<E> prev;
        Triplet<E> next;

        public Triplet() {
            this.item = (E[]) new Object[5];
            this.prev = null;
            this.next = null;
        }
    }
}
