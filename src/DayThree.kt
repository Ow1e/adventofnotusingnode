import java.io.File

fun main() {
    val filePath = "resources/DayThree.txt"

    val input = File(filePath).readText()

    val mulRegex = Regex("""mul\((\d+),(\d+)\)""")

    var isMulEnabled = true
    var totalSum = 0

    val instructions = Regex("""mul\(\d+,\d+\)|do\(\)|don't\(\)""").findAll(input)

    for (instruction in instructions) {
        when (instruction.value) {
            "do()" -> isMulEnabled = true
            "don't()" -> isMulEnabled = false
            else -> if (isMulEnabled && mulRegex.matches(instruction.value)) {
                val (x, y) = mulRegex.find(instruction.value)!!.destructured
                totalSum += x.toInt() * y.toInt()
            }
        }
    }

    println("Total sum of enabled mul instructions: $totalSum")

    println("Total sum of valid mul instructions: ${mulRegex.findAll(input).sumOf { matchResult ->
        val (x, y) = matchResult.destructured
        x.toInt() * y.toInt()
    }}")
}
