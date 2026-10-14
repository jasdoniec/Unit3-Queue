# Unit 3 - Assignment 1: Queues (ArrayQueue & LinkedQueue)

## Overview
In this assignment, you will implement the **Queue Abstract Data Type (ADT)** using two distinct underlying data structures:
1. `ArrayQueue.java` – A generic, dynamically resizing, circular array queue.
2. `LinkedQueue.java` – A generic, singly-linked list queue using `LLNode` nodes.

Both classes implement the provided `QueueInterface<T>` interface.

---

## How to Compile and Run

Because this project uses standard Java without external third-party JARs, standard compile commands apply.

### Option 1: VS Code GUI (Recommended)
1. Open `QueueTest.java`.
2. Click the **Play Icon** in the top-right corner, or press `F5`.

### Option 2: Integrated Terminal
Open the integrated terminal in VS Code (`Ctrl + ~`) and run:

```cmd
javac *.java
java QueueTest
```

---

## Your Tasks

### Task 1: `ArrayQueue.java`
Implement a circular, floating-front array queue that dynamically resizes as elements are added or removed.

* **Empty Indicator:** Set `rear = -1` in the constructor to signify an empty queue.
* **Dynamic `size()` Calculation:** Try **not** to use a `size` instance variable in `ArrayQueue`. Instead, compute the logical size on demand using `front`, `rear`, and `queue.length`

* **Pointer Advancements:**
  * When removing the last remaining element ($\text{size()} == 1$), reset `front = 0` and `rear = -1`. Otherwise, advance `front`: `front = (front + 1) % queue.length`.
* **Resizing (`checkSize()`):**
  * Call checkSize() before you add and after you remove.
  * Double array capacity when `size() == queue.length`.
  * Halve array capacity when `size() < queue.length / 4`.
  * Re-align elements starting at index 0 of the new array and reassign `front = 0` and `rear = size() - 1` (or `rear = -1` if empty).

---

### Task 2: `LinkedQueue.java`
Implement a linked queue using `LLNode<T>` nodes.

* Maintain `front` and `rear` references to `LLNode<T>` objects, as well as an explicit `size` instance variable.  (Otherwise calculating size would be O(n)).


---

## Interface Methods to Implement

Both classes must implement all methods declared in `QueueInterface<T>`:
* `int size()` – Returns logical size.
* `boolean empty()` – Returns `true` if queue has no elements.
* `T add(T item)` – Inserts item at rear and returns item.
* `T peek()` – Returns front item without removing; throws `QueueUnderflowException` if empty.
* `T remove()` – Removes and returns front item; throws `QueueUnderflowException` if empty.
* `void clear()` – Resets queue to empty state.
