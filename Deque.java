import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by justinelsemah on 2017-07-12.
 */

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty()) {
            addToEmpty(item);
        } else {
            Node tempHead = head;

            head = new Node(item);
            head.next = tempHead;
            tempHead.prev = head;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty()) {
            addToEmpty(item);
        } else {
            Node tempTail = tail;

            tail = new Node(item);
            tail.prev = tempTail;
            tempTail.next = tail;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item;

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.size() == 1) {
            item = head.item;
            head = null;
        } else {
            item = head.item;
            head = head.next;
            head.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item item;

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.size() == 1) {
            item = tail.item;
            tail = null;
        } else {
            item = tail.item;
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return item;
    }

    // add new item to empty list
    private void addToEmpty(Item item) {
        head = new Node(item);
        tail = head;
        return;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // inner node class
    private class Node {
        private Node next;
        private Node prev;
        private Item item;

        private Node(Item item) {
            this.item = item;
        }
    }

    // inner iterator class
    private class DequeIterator implements Iterator<Item> {

        private Node current;

        private DequeIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
