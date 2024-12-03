import java.io.File

fun isSafeReport(report: List<Int>): Boolean {
    if (report.size < 2) return false

    val differences = report.zipWithNext { a, b -> b - a }

    val isIncreasing = differences.all { it in 1..3 }
    val isDecreasing = differences.all { it in -3..-1 }

    return isIncreasing || isDecreasing
}


fun canBeSafeWithDampener(report: List<Int>): Boolean {
    if (report.size < 3) return false

    for (i in report.indices) {
        val modifiedReport = report.toMutableList().apply { removeAt(i) } // This is some holy Kotlin code right here
        if (isSafeReport(modifiedReport)) return true
    }
    return false
}

fun countSafeReportsWithDampener(reports: List<List<Int>>): Int {
    return reports.count { isSafeReport(it) || canBeSafeWithDampener(it) }
}


//fun countSafeReports(reports: List<List<Int>>): Int {
//    return reports.count { isSafeReport(it) }
//}

fun main() {
    val filePath = "resources/DayTwo.txt"

    val input = File(filePath).readText()
    println(input.lines())

    val reports = input.lines().map { line ->
        line.split(" ").map {
            if (it != "") it.toInt() else 0
        }
    }

    val safeReportsCount = countSafeReportsWithDampener(reports)
    println("Number of safe reports: $safeReportsCount")
}