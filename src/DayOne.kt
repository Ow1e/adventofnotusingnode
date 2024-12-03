import java.io.File

fun calculateTotalDistance(leftList: List<Int>, rightList: List<Int>): Int {
    val sortedLeft = leftList.sorted()
    val sortedRight = rightList.sorted()

    return sortedLeft.zip(sortedRight).sumOf { (left, right) -> kotlin.math.abs(left - right) }
}

fun calculateSimilarityScore(leftList: List<Int>, rightList: List<Int>): Int {
    val rightFrequency = rightList.groupingBy { it }.eachCount()
    return leftList.sumOf { left -> left * (rightFrequency[left] ?: 0) }
}

fun main() {
    // Java read junk cuz Kotlin ecosystem still dosent have a Multiplatform file lib :(
    val filePath = "resources/DayOne.txt"
    val lines = File(filePath).readLines()

    val leftList : MutableList<Int> = mutableListOf()
    val rightList : MutableList<Int> = mutableListOf()

    for (line in lines) {
        val parts = line.split("   ")
        if (parts.size == 2) {
            leftList.add(parts[0].toInt())
            rightList.add(parts[1].toInt())
        }
    }

    val totalDistance = calculateTotalDistance(leftList, rightList)
    val similarityScore = calculateSimilarityScore(leftList, rightList)

    println("Total Distance: $totalDistance")
    println("Similarity Score: $similarityScore")
}