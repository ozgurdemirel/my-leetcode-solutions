package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Sort Colors (LeetCode #75) - Dutch National Flag
 * ============================================================================
 *
 * TANIM:
 * 0, 1 ve 2 değerlerini içeren bir array'i yerinde (in-place) sırala.
 * 0 = kırmızı, 1 = beyaz, 2 = mavi (Hollanda bayrağı renkleri)
 *
 * ÖRNEK:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Input: [2,0,1]
 * Output: [0,1,2]
 *
 * KISITLAMALAR:
 * - Yerinde sıralama yapılmalı (ek array kullanılmamalı)
 * - Tek geçişte çözüm bekleniyor
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. COUNTING SORT
 *    - 0, 1, 2'lerin sayısını say
 *    - Array'i bu sayılara göre yeniden doldur
 *    - Time: O(n), Space: O(1), ama 2 geçiş gerektirir
 *
 *    pseudo:
 *    count0, count1, count2 = count occurrences
 *    fill array: count0 zeros, then count1 ones, then count2 twos
 *
 * 2. DUTCH NATIONAL FLAG - Three Pointers (Optimal) ✓ [Mevcut implementasyon]
 *    - 3 pointer: sol (0'ların sonu), orta (mevcut), sağ (2'lerin başı)
 *    - orta pointer'ı tara:
 *      - 0 görürsen: sol ile swap, sol++, orta++
 *      - 1 görürsen: orta++ (1'ler ortada kalır)
 *      - 2 görürsen: sağ ile swap, sağ-- (orta artmaz çünkü swap edilen değer kontrol edilmeli)
 *    - Time: O(n), Space: O(1), tek geçiş
 *
 *    pseudo:
 *    sol = 0, orta = 0, sag = lastIndex
 *    while orta <= sag:
 *        if arr[orta] == 0:
 *            swap(sol, orta), sol++, orta++
 *        else if arr[orta] == 1:
 *            orta++
 *        else: // arr[orta] == 2
 *            swap(orta, sag), sag--
 *
 * NOT: 2 ile swap yapınca orta artmaz çünkü sağdan gelen değer henüz kontrol edilmedi.
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Solution {

    fun sortColors(colors: IntArray) {
        var sol = 0
        var orta = 0
        var sag = colors.lastIndex

        while (orta <= sag) {
            when (colors[orta]) {
                0 -> {
                    colors.swap(sol, orta)
                    sol++
                    orta++
                }

                1 -> {
                    orta++
                }

                2 -> {
                    colors.swap(orta, sag)
                    sag--
                }
            }
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i];
        this[i] = this[j];
        this[j] = temp;
    }

}


class SortColorsTest {

    @Test
    fun shouldSuccessfullyOrder() {
        val input = intArrayOf(2, 0, 2, 1, 1, 0)
        Solution().sortColors(input)
        assertArrayEquals(intArrayOf(0, 0, 1, 1, 2, 2), input)
    }

}