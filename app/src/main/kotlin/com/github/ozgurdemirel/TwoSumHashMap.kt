package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Two Sum (LeetCode #1)
 * ============================================================================
 *
 * TANIM:
 * Bir integer array ve bir target değeri verilir.
 * Array'de toplamı target'a eşit olan iki elemanın index'lerini döndür.
 * Her input'un tam olarak bir çözümü vardır ve aynı elemanı iki kez kullanamazsın.
 *
 * ÖRNEK:
 * Input: nums = [2, 7, 11, 15], target = 9
 * Output: [0, 1]
 * Açıklama: nums[0] + nums[1] = 2 + 7 = 9
 *
 * Input: nums = [3, 2, 4], target = 6
 * Output: [1, 2]
 *
 * Input: nums = [3, 3], target = 6
 * Output: [0, 1]
 *
 * KISITLAMALAR:
 * - 2 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 * - Sadece bir geçerli cevap vardır
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. BRUTE FORCE - İç İçe Döngü
 *    - Her eleman için diğer tüm elemanları kontrol et
 *    - Time: O(n²), Space: O(1)
 *
 *    pseudo:
 *    for i in 0..n-1:
 *        for j in i+1..n:
 *            if nums[i] + nums[j] == target:
 *                return [i, j]
 *
 * 2. TWO-PASS HASHMAP
 *    - İlk geçiş: Tüm elemanları map'e ekle (value -> index)
 *    - İkinci geçiş: Her eleman için complement'i ara
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    map = {}
 *    # Pass 1: Build map
 *    for i in 0..n:
 *        map[nums[i]] = i
 *
 *    # Pass 2: Find complement
 *    for i in 0..n:
 *        complement = target - nums[i]
 *        if complement in map AND map[complement] != i:
 *            return [i, map[complement]]
 *
 * 3. ONE-PASS HASHMAP (Optimal) ✓ [Mevcut implementasyon]
 *    - Tek geçişte hem complement'i ara hem de map'e ekle
 *    - Eğer complement zaten map'te varsa, cevabı bulduk
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    map = {}
 *    for i in 0..n:
 *        complement = target - nums[i]
 *        if complement in map:
 *            return [map[complement], i]
 *        map[nums[i]] = i
 *
 * 4. TWO POINTERS (Sorted Array İçin)
 *    - Array'i index'leriyle birlikte sırala
 *    - Sol ve sağ pointer ile toplamı kontrol et
 *    - Time: O(n log n), Space: O(n)
 *    - NOT: Orijinal index'leri korumak için ekstra işlem gerekir
 *
 *    pseudo:
 *    sorted = nums.withIndex().sortedBy { it.value }
 *    left = 0, right = n - 1
 *    while left < right:
 *        sum = sorted[left].value + sorted[right].value
 *        if sum == target:
 *            return [sorted[left].index, sorted[right].index]
 *        else if sum < target:
 *            left++
 *        else:
 *            right--
 *
 * ============================================================================
 * ONE-PASS HASHMAP - GÖRSEL AÇIKLAMA:
 * ============================================================================
 *
 * nums = [2, 7, 11, 15], target = 9
 *
 * ┌────────────────────────────────────────────────────────────────┐
 * │ i=0: num=2, complement=9-2=7                                   │
 * │      map'te 7 var mı? HAYIR                                    │
 * │      map'e ekle: {2: 0}                                        │
 * ├────────────────────────────────────────────────────────────────┤
 * │ i=1: num=7, complement=9-7=2                                   │
 * │      map'te 2 var mı? EVET! index=0                            │
 * │      return [0, 1] ✓                                           │
 * └────────────────────────────────────────────────────────────────┘
 *
 * ============================================================================
 * NEDEN ONE-PASS ÇALIŞIYOR?
 * ============================================================================
 *
 * - a + b = target ise, a'yı gördüğümüzde b'yi arıyoruz
 * - b'yi daha önce görmemişsek, a'yı map'e ekliyoruz
 * - b'yi gördüğümüzde, a zaten map'te olacak
 * - Sıralama: Hangi elemanı önce görürsek görelim, çift bulunacak
 *
 * ============================================================================
 * EDGE CASES:
 * ============================================================================
 * - Aynı değerli elemanlar: [3, 3], target=6 → farklı index'ler döner
 * - Negatif sayılar: [-1, -2, -3, -4, -5], target=-8 → çalışır
 * - Sıfır içeren: [0, 4, 3, 0], target=0 → [0, 3]
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class TwoSumHashMap {

    fun twoSum(nums: IntArray, target: Int): IntArray{
        val map = mutableMapOf<Int, Int>()

        nums.forEachIndexed {index, num->
            val complement = target - num

            map[complement]?.let {
                return  intArrayOf(it, index)
            }

            map[num] = index

        }
        return intArrayOf();
    }

}


class TwoSumHashMapTest {

    @Test
    fun shouldSuccessfullyFind() {
        val twoSum = TwoSumHashMap().twoSum(intArrayOf(2, 7, 11, 15), 9)
        assertArrayEquals(intArrayOf(0,1),twoSum)
    }

}