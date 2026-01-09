package com.github.ozgurdemirel

/*
 * ============================================================================
 * PROBLEM: Remove Nth Node From End of List (LeetCode #19)
 * ============================================================================
 *
 * TANIM:
 * Verilen linked list'in sondan n'inci node'unu sil ve head'i döndür.
 *
 * ÖRNEK:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * (Sondan 2. eleman olan 4 silindi)
 *
 * Input: head = [1], n = 1
 * Output: []
 *
 * ============================================================================
 * ÇÖZÜM YAKLAŞIMLARI:
 * ============================================================================
 *
 * 1. İKİ GEÇİŞ (Two Pass)
 *    - İlk geçiş: Liste uzunluğunu bul (length)
 *    - İkinci geçiş: (length - n)'inci node'a git, next'i atla
 *    - Time: O(n), Space: O(1)
 *
 *    pseudo:
 *    length = count nodes
 *    target = length - n
 *    traverse to (target - 1)
 *    node.next = node.next.next
 *
 * 2. TWO POINTERS - Fast & Slow (Optimal) ✓ [Mevcut implementasyon]
 *    - Dummy node kullan (edge case'ler için)
 *    - Fast pointer'ı n+1 adım ileri götür
 *    - Fast ve slow'u birlikte ilerlet (fast null olana kadar)
 *    - Slow şimdi silinecek node'un bir öncesinde
 *    - Time: O(n), Space: O(1)
 *
 *    pseudo:
 *    dummy.next = head
 *    fast = slow = dummy
 *    move fast (n + 1) steps ahead
 *    while fast != null:
 *        fast = fast.next
 *        slow = slow.next
 *    slow.next = slow.next.next  // skip the target node
 *    return dummy.next
 *
 * NOT: Dummy node kullanmak, head'in silinmesi durumunu ele almayı kolaylaştırır.
 *
 * ============================================================================
 */

import org.junit.jupiter.api.Test

class ListNode(
    var value: Int,
    var next: ListNode? = null
)


fun removeNthNodeFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0)
    dummy.next = head

    var fast: ListNode? = dummy
    var slow: ListNode? = dummy

    (0..n).forEach { i ->
        fast = fast?.next
    }

    while (fast != null) {
        fast = fast.next
        slow = slow?.next
    }

    slow?.next = slow.next?.next

    return dummy.next;
}

fun printList(head: ListNode?) {
    var current = head
    while (current != null) {
        print(current.value)
        current = current.next
    }
}

class NodeNthDeletionTest {

    @Test
    fun `should delete nth last node`() {
        val node7 = ListNode(7, null)
        val node6 = ListNode(6, node7)
        val node5 = ListNode(5, node6)
        val node4 = ListNode(4, node5)
        val node3 = ListNode(3, node4)
        val node2 = ListNode(2, node3)
        val node1 = ListNode(1, node2)

        removeNthNodeFromEnd(node1, 2)

        printList(node1)
    }

}


