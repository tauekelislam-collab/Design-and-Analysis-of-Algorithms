# Problem 3 — Largest Subrange (Maximum Subarray Sum)

**Student:** Kuralbaev Erasyl  
**Group:** SE-2516  
**Course:** Design and Analysis of Algorithms

## Task
Find the maximum sum of a **contiguous, non-empty** subarray. A subarray must use neighboring elements; selecting unrelated positive values is not allowed.

## Brute Force Approach
`maxSumBrute(int[] A)` tries every possible starting index and extends the ending index. It keeps a running sum, so it does not recalculate the same sum from scratch. It updates the maximum whenever it finds a larger result.

**Time:** Θ(n²). **Extra space:** O(1).

## Smart Approach — Divide and Conquer
`maxSumSmart(int[] A)` recursively finds three candidates: (1) the best subarray in the left half; (2) the best in the right half; (3) the best crossing the middle. The crossing sum combines the best suffix of the left half with the best prefix of the right half. The base case is a single element, and the result is the maximum of the three candidates.

**Recurrence:** T(n) = 2T(n/2) + Θ(n). **Time:** Θ(n log n). **Extra space:** O(log n) recursion stack.

## Example from Main
```text
A = [-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4]
Best subarray = [6, 1, 4, -3, 8, 1]
Sum = 6 + 1 + 4 - 3 + 8 + 1 = 17
```

At the top-level split, the best left value is 8, the best right value is 10, and the crossing value is 17. Both implementations return 17.

## Comparison and Conclusion
The nested-loop approach is simple, whereas divide and conquer reduces the time to Θ(n log n). Starting the maximum with `Integer.MIN_VALUE` also allows all-negative input to return the largest negative element. The current code assumes a non-empty input array. See [Problem3.java](../../src/Problem3.java).
