package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Palindrome Linked List (LeetCode #234)
 * ============================================================================
 *
 * TANIM:
 * Verilen bir singly linked list'in palindrome olup olmadığını kontrol et.
 * Palindrome: Baştan ve sondan okunduğunda aynı olan yapı.
 *
 * ÖRNEK:
 * Input: 1 -> 2 -> 2 -> 1
 * Output: true
 *
 * Input: 1 -> 2 -> 3 -> 2 -> 1
 * Output: true
 *
 * Input: 1 -> 2
 * Output: false
 *
 * KISITLAMALAR:
 * - Node sayısı: [1, 10^5]
 * - Node değeri: [0, 9]
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. BRUTE FORCE - Array'e Kopyala
 *    - Tüm değerleri bir array'e kopyala
 *    - Array üzerinde two-pointer ile palindrome kontrolü yap
 *    - Time: O(n), Space: O(n)
 *
 *    pseudo:
 *    values = []
 *    while node != null:
 *        values.add(node.val)
 *        node = node.next
 *    return values == values.reversed()
 *
 * 2. RECURSIVE (Stack Kullanarak)
 *    - Recursion ile listenin sonuna git (implicit stack)
 *    - Geri dönerken baştan ilerleyen pointer ile karşılaştır
 *    - Time: O(n), Space: O(n) - call stack
 *
 *    pseudo:
 *    frontPointer = head
 *    function check(node):
 *        if node == null: return true
 *        if !check(node.next): return false
 *        if node.val != frontPointer.val: return false
 *        frontPointer = frontPointer.next
 *        return true
 *
 * 3. FAST-SLOW POINTER (Optimal) ✓ [Mevcut implementasyon]
 *    - Fast-slow pointer ile ortayı bul
 *    - İkinci yarıyı yerinde (in-place) ters çevir
 *    - İki yarıyı karşılaştır
 *    - (Opsiyonel) Listeyi eski haline getir
 *    - Time: O(n), Space: O(1)
 *
 *    pseudo:
 *    # Adım 1: Ortayı bul
 *    slow = fast = head
 *    while fast?.next != null:
 *        slow = slow.next
 *        fast = fast.next.next
 *
 *    # Adım 2: İkinci yarıyı ters çevir
 *    prev = null, curr = slow
 *    while curr != null:
 *        next = curr.next
 *        curr.next = prev
 *        prev = curr
 *        curr = next
 *
 *    # Adım 3: Karşılaştır
 *    left = head, right = prev
 *    while right != null:
 *        if left.val != right.val: return false
 *        left = left.next
 *        right = right.next
 *    return true
 *
 * ============================================================================
 * GÖRSEL AÇIKLAMA (Fast-Slow Pointer):
 * ============================================================================
 *
 * Liste: 1 -> 2 -> 3 -> 2 -> 1
 *
 * ADIM 1 - Ortayı Bul:
 * ┌─────────────────────────────────────┐
 * │ Başlangıç:  1 -> 2 -> 3 -> 2 -> 1   │
 * │             s,f                      │
 * │                                      │
 * │ İterasyon 1: 1 -> 2 -> 3 -> 2 -> 1  │
 * │                   s    f             │
 * │                                      │
 * │ İterasyon 2: 1 -> 2 -> 3 -> 2 -> 1  │
 * │                        s         f   │
 * │                                      │
 * │ fast.next = null → DUR              │
 * │ slow = 3 (orta nokta)               │
 * └─────────────────────────────────────┘
 *
 * ADIM 2 - İkinci Yarıyı Ters Çevir:
 * ┌─────────────────────────────────────┐
 * │ Önce:  3 -> 2 -> 1 -> null          │
 * │ Sonra: null <- 3 <- 2 <- 1          │
 * │        prev = 1 (ters listenin başı)│
 * └─────────────────────────────────────┘
 *
 * ADIM 3 - Karşılaştır:
 * ┌─────────────────────────────────────┐
 * │ Sol yarı:  1 -> 2 -> 3              │
 * │ Sağ yarı:  1 -> 2 -> 3              │
 * │                                      │
 * │ left=1, right=1 ✓                   │
 * │ left=2, right=2 ✓                   │
 * │ left=3, right=3 ✓                   │
 * │ right=null → Palindrome!            │
 * └─────────────────────────────────────┘
 *
 * ============================================================================
 * EDGE CASES:
 * ============================================================================
 * - Tek elemanlı liste: 1 -> null → true
 * - İki elemanlı liste: 1 -> 1 → true, 1 -> 2 → false
 * - Çift uzunluklu: 1 -> 2 -> 2 -> 1 → orta = ilk 2
 * - Tek uzunluklu: 1 -> 2 -> 3 -> 2 -> 1 → orta = 3
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
class IsPalindrome2 {

    fun isPalindrome(head: ListNode?): Boolean {

        if (head?.next == null) return true

        // 1. Fast-Slow pointer ile orta noktayı bulalım
        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // 2 yarıyı ters çevir
        var prev: ListNode? = null
        var curr = slow
        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }

        // 3 yarıyı ter çevir
        var left = head;
        var right = prev;

        while (right != null) {
            if (left?.value != right.value) return false;
            left = left.next
            right = right.next
        }

        return true
    }

}


class IsPalindrome2Test {

    @Test
    fun shouldSuccessfullyPass() {
        val node5 = ListNode(1, null)
        val node4 = ListNode(2, node5)
        val node3 = ListNode(3, node4)
        val node2 = ListNode(2, node3)
        val node1 = ListNode(1, node2)

        val isPal = IsPalindrome2().isPalindrome(node1)
        assertTrue(isPal)
    }


}