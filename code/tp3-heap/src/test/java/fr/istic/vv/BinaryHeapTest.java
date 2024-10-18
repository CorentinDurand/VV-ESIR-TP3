package fr.istic.vv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeapTest {
    private BinaryHeap<Integer> binaryHeap;

    @BeforeEach
    public void setUp() {
        binaryHeap = new BinaryHeap<>(Comparator.naturalOrder());
    }

    @Test
    public void testPushSingleElement() {
        binaryHeap.push(10);
        assertEquals(1, binaryHeap.count());
        assertEquals(10, binaryHeap.peek());
    }

    @Test
    public void testPushMultipleElements() {
        binaryHeap.push(20);
        binaryHeap.push(10);
        binaryHeap.push(15);
        assertEquals(3, binaryHeap.count());
        assertEquals(10, binaryHeap.peek());
    }

    @Test
    public void testPopEmptyHeapThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.pop();
        });
    }

    @Test
    public void testPopNonEmptyHeap() {
        binaryHeap.push(20);
        binaryHeap.push(10);
        binaryHeap.push(15);
        assertEquals(10, binaryHeap.pop());
        assertEquals(2, binaryHeap.count());
        assertEquals(15, binaryHeap.peek());
    }

    @Test
    public void testPeekEmptyHeapThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.peek();
        });
    }

    @Test
    public void testPeekNonEmptyHeap() {
        binaryHeap.push(30);
        binaryHeap.push(10);
        assertEquals(10, binaryHeap.peek());
        assertEquals(2, binaryHeap.count());  // peek does not remove
    }

    @Test
    public void testHeapOrderingWithComparator() {
        BinaryHeap<String> stringHeap = new BinaryHeap<>(Comparator.reverseOrder());
        stringHeap.push("apple");
        stringHeap.push("banana");
        stringHeap.push("cherry");
        assertEquals("cherry", stringHeap.pop());  // reverse order, "cherry" should come first
    }

    @Test
    public void testCountEmptyHeap() {
        assertEquals(0, binaryHeap.count());
    }

    @Test
    public void testCountNonEmptyHeap() {
        binaryHeap.push(1);
        binaryHeap.push(2);
        assertEquals(2, binaryHeap.count());
        binaryHeap.pop();
        assertEquals(1, binaryHeap.count());
    }

    @Test
    public void testPopSingleElement() {
        binaryHeap.push(5);
        assertEquals(5, binaryHeap.pop());
        assertEquals(0, binaryHeap.count());  // heap is now empty
    }

    @Test
    public void testPopWithSiftDownRightChild() {
        binaryHeap.push(20);
        binaryHeap.push(10);
        binaryHeap.push(15);
        binaryHeap.push(25);  // this will trigger siftDown on the right child
        assertEquals(10, binaryHeap.pop());  // pop the smallest element
        assertEquals(3, binaryHeap.count());  // heap should reorder
        assertEquals(15, binaryHeap.peek());  // verify the new root
    }

    @Test
    public void testPopWithSiftDownLeftChildOnly() {
        binaryHeap.push(20);
        binaryHeap.push(10);  // Only left child exists, and it's smaller
        assertEquals(10, binaryHeap.pop());  // pop the smallest element
        assertEquals(1, binaryHeap.count());  // verify remaining size
        assertEquals(20, binaryHeap.peek());  // verify the new root
    }
    
    @Test
    public void testPopWithNoChildren() {
        binaryHeap.push(30);
        assertEquals(30, binaryHeap.pop());  // heap has no children, pop the only element
        assertEquals(0, binaryHeap.count());  // verify heap is now empty
    }
    

}
