package com.github.ozgurdemirel

/**
 * Tower of Hanoi
 *
 * You are given three rods (labeled 'A', 'B', and 'C') and n disks of different sizes
 * which can slide onto any rod. Initially, all disks are stacked on rod 'A' in
 * decreasing order of size (largest at the bottom, smallest at the top).
 *
 * The objective is to move the entire stack to rod 'C', obeying the following rules:
 *   1. Only one disk can be moved at a time.
 *   2. Each move consists of taking the upper disk from one of the stacks and
 *      placing it on top of another stack or on an empty rod.
 *   3. No disk may be placed on top of a smaller disk.
 *
 * Print all the moves required to solve the puzzle.
 *
 * Example:
 *   Input: n = 2
 *   Output:
 *     Move disk from A to B
 *     Move disk from A to C
 *     Move disk from B to C
 *
 * Example:
 *   Input: n = 3
 *   Output:
 *     Move disk from A to C
 *     Move disk from A to B
 *     Move disk from C to B
 *     Move disk from A to C
 *     Move disk from B to A
 *     Move disk from B to C
 *     Move disk from A to C
 *
 * Constraints:
 *   - 1 <= n <= 20
 *
 * Note: The minimum number of moves required to solve the puzzle is 2^n - 1.
 */

fun move(kaynak: Char, yardimci: Char, hedef: Char, diskSayisi: Int) {

    // en son kalan diski ozel durum taşıyorum...
    if (diskSayisi == 1) {
        println("En üstteki diski $kaynak cubugundan $hedef ye taşı")
    } else {
        move(kaynak, hedef, yardimci, diskSayisi - 1)

        move(kaynak, yardimci, hedef, 1)

        move(yardimci, kaynak, hedef, diskSayisi - 1)
    }
}

fun main(args: Array<String>) {
    move('a', 'b', 'c', diskSayisi = 3)
}