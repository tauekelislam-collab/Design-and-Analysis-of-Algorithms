# Problem 2 — Median of Two Sorted Arrays

**Student:** Kuralbaev Erasyl  
**Group:** SE-2516  
**Course:** Design and Analysis of Algorithms

## Task
Find the median of two sorted integer arrays without changing their order. The median is the middle number for odd total length, or the average of the two middle numbers for even total length.

## Brute Force Approach
`getMedianBrute(int[] A, int[] B)` merges the arrays into one sorted array by comparing the next unused elements. It then reads the middle index or averages the two middle indices.

**Time:** Θ(n + m). **Extra space:** Θ(n + m), for the merged array.

## Smart Approach — Binary Search Partition
`getMedianSmart(int[] A, int[] B)` performs binary search on the shorter array. It selects a partition in each array so that the left sides contain half of all elements. A partition is valid when `maxLeftA <= minRightB` and `maxLeftB <= minRightA`. For odd length, the median is the larger left boundary. For even length, it is the average of the larger left boundary and the smaller right boundary.

**Time:** O(log(min(n,m) + 1)). **Extra space:** O(1), excluding the one possible method call that swaps the arrays.

## Examples from Main
```text
A = [2, 4], B = [3]
Merged = [2, 3, 4]
Median = 3.0

A = [2, 4], B = [3, 5]
Merged = [2, 3, 4, 5]
Median = (3 + 4) / 2.0 = 3.5
```

## Comparison and Conclusion
Merging is straightforward, but needs a new array. The partition-based solution avoids merging and is more efficient when the arrays are large and sorted. The current methods assume that the combined input has at least one element. See [Problem2.java](../../src/Problem2.java).
