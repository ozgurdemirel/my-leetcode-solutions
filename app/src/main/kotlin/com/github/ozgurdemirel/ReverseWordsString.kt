package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Reverse Words in a String (LeetCode #151)
 * ============================================================================
 *
 * TANIM:
 * Verilen bir string'deki kelimelerin sırasını tersine çevir.
 * Kelimeler arasında birden fazla boşluk olabilir, baş/son boşluklar temizlenmeli.
 * Sonuçta kelimeler tek boşlukla ayrılmalı.
 *
 * ÖRNEK:
 * Input: "  the sky is blue  "
 * Output: "blue is sky the"
 *
 * Input: "  hello world  "
 * Output: "world hello"
 *
 * Input: "a good   example"
 * Output: "example good a"
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. SPLIT + REVERSE (Built-in fonksiyonlar)
 *    - Trim, split, reverse, join
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    words = sentence.trim().split(regex: multiple spaces)
 *    return words.reversed().joinToString(" ")
 *
 * 2. SPLIT + FOLD (Mevcut implementasyon) ✓
 *    - Trim, split, fold ile ters sırada birleştir
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    words = sentence.trim().split(regex)
 *    return words.fold("") { acc, word ->
 *        if acc.isEmpty: word
 *        else: "$word $acc"  // yeni kelimeyi başa ekle
 *    }
 *
 * 3. TWO POINTERS - In-place (Optimal for char array)
 *    - Tüm string'i ters çevir
 *    - Her kelimeyi ayrı ayrı ters çevir
 *    - Fazla boşlukları temizle
 *    - Time: O(n), Space: O(1) - char array kullanırsan
 *
 *    pseudo:
 *    reverse(entire string)
 *    for each word:
 *        reverse(word)
 *    clean extra spaces
 *
 * 4. STACK KULLANIMI
 *    - Kelimeleri stack'e push et
 *    - Pop ederek ters sırada al
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    stack = Stack()
 *    for word in sentence.split():
 *        stack.push(word)
 *    result = ""
 *    while !stack.isEmpty:
 *        result += stack.pop() + " "
 *    return result.trim()
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionReverseWordsString {
    fun reversedWords(sentence: String): String {
        return sentence.trim()
            .split("\\s++".toRegex())
            .fold("") { acc, word ->
                if (acc.isEmpty()) word else "$word $acc"
            }
    }
}

class SolutionReverseWordsStringTest {
    @Test
    fun shouldReverStringSuccessfully() {
        var expected = SolutionReverseWordsString().reversedWords(" the sky is blue ")

        assertEquals("blue is sky the", expected)
    }
}


