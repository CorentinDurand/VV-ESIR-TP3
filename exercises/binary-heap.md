# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer
### 1.
To create a comprehensive test suite for the BinaryHeap class, we can use Input Space Partitioning to identify characteristics and create input blocks for each method.
#### Characteristics and Blocks for pop :
##### Heap Size:
* Empty heap (throws `NoSuchElementException`)
* Non-empty heap (returns the minimum object)
##### Object Type:
* Comparable objects (basic comparison functionality)
* Non-comparable objects (should fail at insertion if object is not comparable, though this might be handled externally)

#### Characteristics and Blocks for peek :
##### Heap Size:
* Empty heap (throws `NoSuchElementException`)
* Non-empty heap (returns but does not remove the minimum object)
##### Object Type:
* Comparable objects (handles comparison between objects)
#### Characteristics and Blocks for push :
##### Heap Size:
* Empty heap (inserts first element)
* Non-empty heap (inserts and maintains heap order)
##### Object Size:
* Small object (single push)
* Large object (push multiple objects)
##### Ordering:
* Inserting element larger than current minimum (heap structure preserved)
* Inserting element smaller than current minimum (heap reorders)
#### Characteristics and Blocks for count:
##### Heap Size:
* Empty heap (returns 0)
* Non-empty heap (returns correct count after multiple operations)
#### Common Characteristics Across Methods:
* Heap Size (affects `pop`, `peek`, `push`, `count`)
*Object Type (affects `pop`, `peek`, `push`)
##### Additional Testing Methods:
* Comparator Verification: Test the ordering of objects in the heap when using different `Comparator` implementations.
* Integrity Checks: After multiple operations (`push`, `pop`), ensure the heap maintains the correct structure.

### 2.
After assessing the previous test cases, we need to add new ones to enhance coverage.
* `pop()` on heap with one element.
* `siftDown` covering more cases.

### 3.
To assess *Base Choice Coverage*, we need to identify predicates containing more than two boolean operators. We then added new test cases.
```java @Test
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
```
### 4.
By running the `mvn clean test pitest:mutationCoverage` command, we can easily evaluate our test suite :
![image](https://github.com/user-attachments/assets/1390cc5d-9c60-43c8-8daa-ae54221e402c)
The mutation score is 82%, with 27 out of 33 mutations killed.
