package array

import java.util.*

object findCombination
//{

fun main() {
    findCombination(arrayOf(1, 2, 3), 6)
}

private fun findCombination(numbers: Array<Int>, target: Int) {
    println("start to process findCombination")
    val wipNumbers = mutableListOf<Int>()
    val finalResults = mutableListOf(listOf<Int>())
    doFind(numbers, 0, target, wipNumbers, finalResults)
    println("Try input ${numbers.contentToString()} with result: $finalResults")
}

fun doFind(
    numbers: Array<Int>,
    start: Int,
    target: Int,
    wipNumbers: MutableList<Int>,
    finalResults: MutableList<List<Int>>
) {
    // BackTrack#1: exit rule (either positive or negative)
    if (target == 0) {
        finalResults.add(listOf<Int>() + wipNumbers)
    } else if (target < 0) {
    } else {
        for (i in start until numbers.size) {
            // BackTrack#2: Add current 'try' to the temp list
            wipNumbers.add(numbers[start.toInt()])
            // BackTrack#3: Do backtrack with the latest try
            doFind(numbers, i + 1, target - numbers[start.toInt()], wipNumbers, finalResults)
            // BackTrack#3: remove the latest try
            wipNumbers.removeLast()
        }
    }
}