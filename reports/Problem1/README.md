# Problem 1 — Number of Occurrences

**Student:** Kuralbaev Erasyl  
**Group:** SE-2516  
**Course:** Design and Analysis of Algorithms

## Task
Count how many times a given key occurs in a sorted integer array. Both methods should return 0 when the key is absent.

## Brute Force Approach
The method `countFreqBrute(int key, int[] A)` goes through every element. When `A[i] == key`, it increases the counter. It returns the counter at the end.

**Time:** Θ(n), because the loop visits all n elements. **Extra space:** O(1).

## Smart Approach — Binary Search
The method `countFreqSmart(int key, int[] A)` uses two binary searches. `findFirst` locates the first position of the key; `findLast` locates its final position. When the first search returns -1, the answer is 0. Otherwise the count is `last - first + 1`.

**Time:** O(log n), using two binary searches. **Extra space:** O(1).

## Example
```text
A   = [1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 4, 4, 5, 5, 5, 5]
key = 2
first = 3, last = 8
count = 8 - 3 + 1 = 6
```

For key = 3, both methods return 0. For key = 5, both should return 4.

## Comparison and Conclusion
The brute method is easy to understand and works on unsorted arrays as well. Binary search is faster for large arrays, but it depends on the array being sorted. The implementation is in [Problem1.java](../../src/Problem1.java).
