package com.github.ozgurdemirel

import org.junit.jupiter.api.Test

/**
 * Factorial
 *
 * Given a non-negative integer n, compute and return n! (n factorial).
 *
 * The factorial of n is the product of all positive integers less than or equal to n:
 * n! = n × (n-1) × (n-2) × ... × 2 × 1
 *
 * By definition: 0! = 1 and 1! = 1
 *
 * Example 1:
 * Input: n = 5
 * Output: 120
 * Explanation: 5! = 5 × 4 × 3 × 2 × 1 = 120
 *
 * Example 2:
 * Input: n = 0
 * Output: 1
 *
 * Constraints:
 * - 0 <= n <= 12 (for Int, as 13! overflows)
 *
 * Follow-up: Can you implement it iteratively, recursively, and using tail recursion?
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) for iterative, O(n) for recursive, O(1) for tail-recursive
 */
fun faktoriyel(n: Int): Int {
    var toplam = 1;

    for (i in 1..n) {
        toplam *= i
    }
    return toplam;
}

fun faktoriyelRecursive(n: Int): Int {
    if (n <= 1) return 1
    return n * faktoriyelRecursive(n - 1)
}

fun faktoriyelRecursive2(n: Int): Int {
    return faktoriyelHelper(n, 1);
}

tailrec fun faktoriyelHelper(n: Int, acc: Int): Int {
    if (n <= 1) return acc;
    return faktoriyelHelper(n - 1, acc = n * acc)
}

class FaktoriyelTest {

    @Test
    fun faktoriyelTest() {
        println(faktoriyel(5))
    }

    @Test
    fun faktoriyelRecursiveTest() {
        faktoriyelRecursive(5).let(::println)
    }

    @Test
    fun faktoriyelRecursiveTest2() {
        faktoriyelRecursive2(5).let(::println)
    }

}