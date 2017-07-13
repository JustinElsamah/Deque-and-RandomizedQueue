import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by justinelsemah on 2017-07-12.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] itemArray;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        itemArray = (Item[]) new Object[1];
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        if (size == itemArray.length) {
            resize(2 * size);
        }
        itemArray[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }

        int randInt = StdRandom.uniform(size);
        Item item = itemArray[randInt];
        itemArray[randInt] = itemArray[size - 1];
        itemArray[size-1] = null;
        size--;
        if (size > 0 && size == itemArray.length / 4) resize(itemArray.length / 2);
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("no item to return (empty stack)");
        }
        return itemArray[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // resize and copy data to new array of size capacity
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            temp[i] = itemArray[i];
        }

        itemArray = temp;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current;
        private int[] randomArray;

        private RandomizedQueueIterator() {
            current = 0;
            randomArray = new int[size];

            for(int i = 0; i < size; i++){
                randomArray[i] = i;
            }
            StdRandom.shuffle(randomArray);
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return itemArray[randomArray[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
