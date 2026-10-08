# Problem 5 — Integer Multiplication

**Student:** Tauekel Islam  
**Group:** SE-2516  
**Course:** Design and Analysis of Algorithms

## Task
Multiply two non-negative integers represented by decimal strings. Strings are needed because inputs can be much larger than Java's `long` range. Neither multiplication implementation uses `BigInteger` to compute the answer.

## Brute Force Approach
`multBrute(String A, String B)` simulates multiplication by hand. It loops through digit pairs from right to left, adds their products to an array of length `n+m`, handles carries, skips leading zeros, and returns the decimal digits as a string.

**Time:** Θ(nm) for input lengths n and m. **Extra space:** O(n+m).

## Smart Approach — Karatsuba
`multSmart(String A, String B)` calls `karatsuba`. After zero checks and padding, each number is split into high and low halves: `A = a·10^k + b`, `B = c·10^k + d`. The algorithm computes:

- `z2 = a*c`
- `z0 = b*d`
- `z1 = (a+b)*(c+d) - z2 - z0`

It then returns `z2*10^(2k) + z1*10^k + z0`, using string addition, subtraction and shifts. The base case multiplies with `long` only when both operands contain four digits or fewer.

**Standard recurrence:** T(N) = 3T(N/2) + O(N). **Standard Karatsuba time:** O(N^log₂3), approximately O(N^1.585), where N is the larger input length. This is the algorithmic target; exact runtime of Java string operations depends on allocation and representation. **Auxiliary memory:** additional strings and recursion; not constant.

## Small Worked Example
```text
1234 = 12*100 + 34
5678 = 56*100 + 78
z2 = 12*56 = 672
z0 = 34*78 = 2652
z1 = (12+34)*(56+78) - 672 - 2652
   = 46*134 - 672 - 2652 = 2840
Product = 672*10000 + 2840*100 + 2652
        = 7006652
```

This explains the Karatsuba formula. In the actual Java implementation, this four-digit example is handled directly by the base case rather than being recursively split.

## Validation and Conclusion
`Problem5.main` compares the output from brute force and Karatsuba with a `BigInteger` multiplication result as a demonstration check. `BigInteger` is **not** used inside either required multiplication method. Karatsuba reduces the number of large recursive multiplications from four to three. See [Problem5.java](../../src/Problem5.java).
