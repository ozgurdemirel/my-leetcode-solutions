package com.github.ozgurdemirel

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


