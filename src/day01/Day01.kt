package day01

import println
import readInput

fun main() {

    // test if implementation meets criteria from the description, like:
    val testInputP1 = readInput("Day01_testp1")
    check(part1(testInputP1) == 142)

    val testInputP2 = readInput("Day01_testp2")
    check(part2(testInputP2) == 281)

    val input = readInput("day01")
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    return input.sumOf { getCalibrationValue(it) }
}

fun part2(input: List<String>): Int {
    return input.sumOf { getCalibrationValueWithWords(it) }
}

fun getCalibrationValue(line: String): Int {
    val numbers = line.filter { it.isDigit() }
    return "${numbers.first()} ${numbers.last()}".toInt()
}

fun getCalibrationValueWithWords(line: String): Int {
    var output = ""

    val matches = Regex(lookupPattern).findAll(line)
    for (match in matches) {
        if (match.value.isBlank()) {
            val startOfWord = line.substring(match.range.first)
            val foundWord = Regex(replacementPattern).find(startOfWord)
            if (foundWord != null) {
                output += numbersMap[foundWord.value].toString()
            }
        }
        else {
            output += match.value
        }
    }

    return getCalibrationValue(output.replace(" ", ""))
}

const val replacementPattern = "one|two|three|four|five|six|seven|eight|nine"
const val lookupPattern = "(?=$replacementPattern)|\\d"

val numbersMap = mapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
    "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")
