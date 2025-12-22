package collection;

import java.util.*;


public class CustomSingleList<T> extends AbstractList<T> {
    private Node head;
    private int size;

    private class Node {
        private Node next;
        private final T data;


        public Node(T data) {
            this.data = data;

        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    '}';
        }
    }


    public CustomSingleList() {
        size = 0;

    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean add(T t) {
        if (t == null){
            return false;
        }

        Node node = new Node(t);
        size++;
        if (head == null) {
            head = node;

        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("индекс отрицательный или больше размера списка " + index);
        }
        if (head == null) {
            throw new IllegalStateException("Список пуст");
        }
        Node current = head;
        if (index == 0) {
            return current.data;
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CustomSingleList<?> that)) return false;
        if (!super.equals(o)) return false;
        return size == that.size && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), head, size);
    }

    @Override
    public String toString() {
        return "CustomSingleList{" +
                "head=" + head +
                ", size=" + size +
                '}';
    }


}
