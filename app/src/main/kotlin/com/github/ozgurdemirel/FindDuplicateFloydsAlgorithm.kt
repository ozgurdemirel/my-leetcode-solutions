package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Find the Duplicate Number (LeetCode #287)
 * ============================================================================
 *
 * TANIM:
 * n+1 elemanlı bir array verilir, elemanlar [1, n] aralığındadır.
 * Sadece BİR rakam tekrar eder. Bu tekrar eden rakamı bul.
 *
 * ÖRNEK:
 * Input: [1, 3, 4, 2, 2]
 * Output: 2
 *
 * Input: [3, 1, 3, 4, 2]
 * Output: 3
 *
 * KISITLAMALAR:
 * - Array'i değiştirme (modify etme)
 * - O(1) ekstra alan kullan
 * - Sadece bir duplicate var (birden fazla kez tekrar edebilir)
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
 *    for i in 0..n:
 *        for j in i+1..n:
 *            if nums[i] == nums[j]: return nums[i]
 *
 * 2. SORTING
 *    - Array'i sırala
 *    - Ardışık aynı elemanları bul
 *    - Time: O(n log n), Space: O(1) veya O(n)
 *    - NOT: Array'i modify eder (kısıtlamayı ihlal eder)
 *
 *    pseudo:
 *    sort(nums)
 *    for i in 1..n:
 *        if nums[i] == nums[i-1]: return nums[i]
 *
 * 3. HASHSET
 *    - Görülen elemanları set'te tut
 *    - Tekrar görünce return et
 *    - Time: O(n), Space: O(n)
 *    - NOT: O(1) space kısıtlamasını ihlal eder
 *
 *    pseudo:
 *    seen = HashSet()
 *    for num in nums:
 *        if num in seen: return num
 *        seen.add(num)
 *
 * 4. NEGATIVE MARKING
 *    - Index olarak kullan, ziyaret edileni negatif yap
 *    - Zaten negatifse duplicate bulundu
 *    - Time: O(n), Space: O(1)
 *    - NOT: Array'i modify eder (kısıtlamayı ihlal eder)
 *
 *    pseudo:
 *    for num in nums:
 *        idx = abs(num)
 *        if nums[idx] < 0: return idx
 *        nums[idx] = -nums[idx]
 *
 * 5. BINARY SEARCH (Count-Based)
 *    - mid değerinden küçük/eşit kaç eleman var say
 *    - Pigeonhole prensibine göre aralığı daralt
 *    - Time: O(n log n), Space: O(1)
 *
 *    pseudo:
 *    low = 1, high = n
 *    while low < high:
 *        mid = (low + high) / 2
 *        count = nums.count { it <= mid }
 *        if count > mid:
 *            high = mid      # duplicate sol tarafta
 *        else:
 *            low = mid + 1   # duplicate sağ tarafta
 *    return low
 *
 * 6. FLOYD'S CYCLE DETECTION (Optimal) ✓ [Mevcut implementasyon]
 *    - Array'i linked list gibi düşün: index -> value -> next index
 *    - Duplicate varsa cycle oluşur
 *    - Fast-slow pointer ile cycle'ın başlangıcını bul
 *    - Time: O(n), Space: O(1)
 *
 *    pseudo:
 *    # Phase 1: Kesişim noktasını bul
 *    slow = fast = nums[0]
 *    do:
 *        slow = nums[slow]        # 1 adım
 *        fast = nums[nums[fast]]  # 2 adım
 *    while slow != fast
 *
 *    # Phase 2: Cycle başlangıcını bul
 *    slow = nums[0]
 *    while slow != fast:
 *        slow = nums[slow]
 *        fast = nums[fast]
 *    return slow
 *
 * ============================================================================
 * FLOYD'S ALGORITHM - GÖRSEL AÇIKLAMA:
 * ============================================================================
 *
 * Array: [1, 3, 4, 2, 2]
 * Index:  0  1  2  3  4
 *
 * Linked list olarak düşün (index → value):
 * 0 → 1 → 3 → 2 → 4 → 2 → 4 → 2 → ... (cycle!)
 *              ↑_______↓
 *
 * Phase 1: Kesişim noktasını bul
 * ┌─────────────────────────────────────┐
 * │ slow: 0→1→3→2→4→2                   │
 * │ fast: 0→1→3→2→4→2→4→2→4→2           │
 * │ Kesişim: 2                          │
 * └─────────────────────────────────────┘
 *
 * Phase 2: Cycle başlangıcını bul
 * ┌─────────────────────────────────────┐
 * │ slow: 0→1→3→2                       │
 * │ fast: 2→4→2                         │
 * │ Buluşma noktası: 2 (duplicate!)     │
 * └─────────────────────────────────────┘
 *
 * ============================================================================
 * NEDEN ÇALIŞIYOR?
 * ============================================================================
 *
 * - Değerler [1, n] aralığında, array boyutu n+1
 * - Her değer geçerli bir index (0 hariç)
 * - Duplicate değer = iki farklı index aynı yere işaret eder = CYCLE
 * - Cycle'ın başladığı nokta = duplicate değer
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FindDuplicateFloydsAlgorithm {
    fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[0]

        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)


        slow = nums[0]

        while(slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }


        return slow

    }
}


class FindDuplicateFloydsAlgorithmTest {

    @Test
    fun shouldFindDuplicate(){
        val findDuplicate = FindDuplicateFloydsAlgorithm().findDuplicate(intArrayOf(1, 3, 4, 2, 2))
        assertEquals(2, findDuplicate)
    }

}





