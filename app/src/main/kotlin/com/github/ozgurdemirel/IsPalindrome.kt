package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Valid Palindrome (LeetCode #125)
 * ============================================================================
 *
 * TANIM:
 * Verilen bir string'in palindrome olup olmadığını kontrol et.
 * Sadece alfanümerik karakterler dikkate alınır, büyük/küçük harf farkı yok sayılır.
 *
 * ÖRNEK:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true (temizlenmiş hali: "amanaplanacanalpanama")
 *
 * Input: "race a car"
 * Output: false
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. BRUTE FORCE - String Ters Çevirme
 *    - String'i temizle (sadece alfanümerik, küçük harf)
 *    - Ters çevir ve orijinalle karşılaştır
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    cleaned = s.filter { alphanumeric }.lowercase()
 *    return cleaned == cleaned.reversed()
 *
 * 2. TWO POINTERS (Optimal) ✓ [Mevcut implementasyon]
 *    - Sol ve sağ pointer kullan
 *    - Alfanümerik olmayan karakterleri atla
 *    - Karşılaştır, eşleşmezse false
 *    - Time: O(n), Space: O(1)
 *
 *    pseudo:
 *    left = 0, right = lastIndex
 *    while left < right:
 *        skip non-alphanumeric from left
 *        skip non-alphanumeric from right
 *        if lowercase(s[left]) != lowercase(s[right]): return false
 *        left++, right--
 *    return true
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Palindrome {

    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex
        while (left < right) {

            while (left < right && !s[left].isLetterOrDigit()) left++

            while (left < right && !s[right].isLetterOrDigit()) right--

            if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
                return false
            }

            left++
            right--
        }
        return true;
    }

}

class PalindromeTest {

    @Test
    fun `should be palindrome`() {
        val expected = Palindrome().isPalindrome("A man, a plan, a canal: Panama")
        assertTrue(expected)
    }

    @Test
    fun `should not be palindrome`() {
        val expected = Palindrome().isPalindrome("race a car")
        assertFalse(expected)
    }

}