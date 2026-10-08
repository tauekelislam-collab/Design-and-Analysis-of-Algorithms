# Problem 4 — Closest Pair of Points

**Student:** Tauekel Islam  
**Group:** SE-2516  
**Course:** Design and Analysis of Algorithms

## Task
Find the smallest Euclidean distance between any two distinct 2D points. For points (x1,y1) and (x2,y2), the distance is `sqrt((x1-x2)^2 + (y1-y2)^2)`.

## Brute Force Approach
`minDistBrute(double[][] p)` checks every pair of points `i < j`, computes its distance, and keeps the smallest value.

**Time:** Θ(n²). **Extra space:** O(1).

## Smart Approach — Divide and Conquer
`minDistSmart(double[][] p)` makes a copy of the input and sorts the points by X coordinate. The `closest` method divides the sorted interval into left and right halves, recursively finds their minimum distances, and sets `minDist` to the smaller one. Then it builds a strip of points close to the middle X coordinate, sorts the strip by Y, and compares candidates while the Y difference is smaller than the current best distance. Intervals with at most three points use a brute-force helper.

**Time:** O(n log² n) for this actual implementation, because the strip is sorted by Y on each recursive level. This is different from the optimized O(n log n) version that maintains Y-sorted order during recursion. **Extra space:** O(n) peak auxiliary storage for copying and strip arrays, plus O(log n) recursion stack (not counting sorting implementation details).

## Example from Main
```text
P0 = (0, 0), P1 = (3, 4), P2 = (-5, -3)
d(P0, P1) = 5.0
d(P0, P2) = sqrt(34) ≈ 5.831
d(P1, P2) = sqrt(113) ≈ 10.630
Minimum = 5.0
```

With only three points, the smart method reaches its base case and uses the brute-force helper. A larger example is necessary to demonstrate the recursive strip check.

## Comparison and Conclusion
Brute force is suitable for a small set of points. Divide and conquer is more scalable and also checks pairs across the dividing line. The code returns positive infinity for fewer than two points because there is no pair to compare; the assignment's expected behavior for that case should be checked. See [Problem4.java](../../src/Problem4.java).
