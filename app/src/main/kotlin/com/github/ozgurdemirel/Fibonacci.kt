package com.github.ozgurdemirel

import org.junit.jupiter.api.Test


/**
 * Fibonacci Sequence
 *
 * Given an integer n, print the first n numbers of the Fibonacci sequence.
 *
 * The Fibonacci sequence is defined as:
 * F(0) = 0, F(1) = 1
 * F(n) = F(n-1) + F(n-2) for n > 1
 *
 * Example:
 * Input: n = 8
 * Output: 0, 1, 1, 2, 3, 5, 8, 13
 *
 * Constraints:
 * - 0 <= n <= 30
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
fun fibonacci(n: Int) {
    var prev1 = 1
    var prev2 = 0

    var curr = 0

    for (i in 2 until n) {
        println(curr)
        curr = prev1 + prev2
        prev2 = prev1
        prev1 = curr
    }

}

fun fibRecursive(n: Int) {
    if (n <= 1) return;
    fibHelper(n, 2, 0, 1)
}

tailrec fun fibHelper(n: Int, i: Int, prev2: Int, prev1: Int) {
    if (i > n) return;
    println(prev2)
    fibHelper(n, i + 1, prev1, prev1 + prev2)
}

class FibonacciTest {

    @Test
    fun shouldSuccessfullyRan() {
        fibonacci(8)
    }

    @Test
    fun shouldSuccessfullyRanRecursive() {
        fibRecursive(8)
    }

}