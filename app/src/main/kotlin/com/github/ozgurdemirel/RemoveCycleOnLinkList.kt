package com.github.ozgurdemirel

import org.junit.jupiter.api.Test


/**
 * PROBLEM: Linked List Cycle IV
 * TIME: 30 min
 *
 * Given the head of a singly linked list, implement a function to detect and remove
 * any cycle present in the list. A cycle occurs when a node's next pointer links back
 * to a previous node, forming a loop within the list.
 *
 * The function must modify the linked list in place, ensuring it remains acyclic while
 * preserving the original node order. If no cycle is found, return the linked list as is.
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4]
 * - -10^5 <= Node.value <= 10^5
 *
 * İlgili LeetCode Problemleri:
 * | LeetCode   | Problem              | Ne Yapar                       |
 * |------------|----------------------|--------------------------------|
 * | 141        | Linked List Cycle    | Döngü var mı? (Easy)           |
 * | 142        | Linked List Cycle II | Döngü nerede başlıyor? (Medium)|
 * | Bu Problem | Cycle IV             | Döngüyü tespit et + kaldır     |
 */
class RemoveCycleOnLinkList {

    fun removeCycle(head: ListNode?): ListNode? {

        // edge case
        if (head?.next == null) return head

        var slow = head
        var fast = head

        var cycleFound = false


        while (fast?.next != null) {
            slow = slow?.next // kamlumbag her seferinde bir adım atar
            fast = fast.next?.next // tavsan her seferinde iki adım atar

            if (slow == fast) {
                cycleFound = true
                break
            }
        }

        if (!cycleFound) return head

        // dongu başlangıcını buş ve kır
        slow = head

        if (slow == fast) {
            // özel durum: Döngü head'den başlıyor
            while (fast?.next != slow) {
                fast = fast?.next
            }
        } else {
            // normal durum
            while (slow?.next != fast?.next) {
                slow = slow?.next
                fast = fast?.next
            }

        }

        // döngüyü kır
        fast?.next = null


        return head
    }


}


class RemoveCycleOnLinkListTest {

    private val solution = RemoveCycleOnLinkList()

    @Test
    fun `should remove cycle from middle of linked list`() {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 3 (cycle back to node 3)
        val node1 = ListNode(1)
        val node2 = ListNode(2)
        val node3 = ListNode(3)
        val node4 = ListNode(4)
        val node5 = ListNode(5)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node3 // cycle

        val result = solution.removeCycle(node1)

        // Verify cycle is removed - list should be: 1 -> 2 -> 3 -> 4 -> 5 -> null
        assert(result?.value == 1)
        assert(result?.next?.value == 2)
        assert(result?.next?.next?.value == 3)
        assert(result?.next?.next?.next?.value == 4)
        assert(result?.next?.next?.next?.next?.value == 5)
        assert(result?.next?.next?.next?.next?.next == null)
    }

    @Test
    fun `should remove cycle when cycle starts at head`() {
        // 1 -> 2 -> 3 -> 1 (cycle back to head)
        val node1 = ListNode(1)
        val node2 = ListNode(2)
        val node3 = ListNode(3)

        node1.next = node2
        node2.next = node3
        node3.next = node1 // cycle back to head

        val result = solution.removeCycle(node1)

        // Verify cycle is removed - list should be: 1 -> 2 -> 3 -> null
        assert(result?.value == 1)
        assert(result?.next?.value == 2)
        assert(result?.next?.next?.value == 3)
        assert(result?.next?.next?.next == null)
    }

    @Test
    fun `should return same list when no cycle exists`() {
        // 1 -> 2 -> 3 -> null (no cycle)
        val node1 = ListNode(1)
        val node2 = ListNode(2)
        val node3 = ListNode(3)

        node1.next = node2
        node2.next = node3

        val result = solution.removeCycle(node1)

        // List should remain unchanged
        assert(result?.value == 1)
        assert(result?.next?.value == 2)
        assert(result?.next?.next?.value == 3)
        assert(result?.next?.next?.next == null)
    }

    @Test
    fun `should handle single node without cycle`() {
        val node1 = ListNode(1)

        val result = solution.removeCycle(node1)

        assert(result?.value == 1)
        assert(result?.next == null)
    }

    @Test
    fun `should handle single node with self cycle`() {
        // 1 -> 1 (points to itself)
        val node1 = ListNode(1)
        node1.next = node1 // self cycle

        val result = solution.removeCycle(node1)

        assert(result?.value == 1)
        assert(result?.next == null)
    }
}