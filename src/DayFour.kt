import java.io.File

// So I could make this a lot faster, but idrc
// I would only check at every other cell, and then shift the cell check by 1 every time I would pass a row (for the first part)
// Also checking grid so much is expensive asf

fun countXMASPatterns(grid: List<String>): Int {
    val numRows = grid.size
    val numCols = grid[0].length
    var count = 0

    fun isMAS(c1: Char, c2: Char, c3: Char): Boolean {
        return (c1 == 'M' && c2 == 'A' && c3 == 'S') || (c1 == 'S' && c2 == 'A' && c3 == 'M')
    }

    for (row in 1..<numRows - 1) { // Avoid top and bottom edges (OOPSIE)
        for (col in 1..<numCols - 1) { // Avoid left and right edges (OOPSIE)
            if (grid[row][col] == 'A') { // Center must be 'A' or I geeking
                if (isMAS(grid[row - 1][col - 1], grid[row][col], grid[row + 1][col + 1]) && // Top-left to bottom-right
                    isMAS(grid[row - 1][col + 1], grid[row][col], grid[row + 1][col - 1])) { // Top-right to bottom-left
                    count++
                }
            }
        }
    }

    return count
}

fun main() {
    val filePath = "resources/DayFour.txt"

    val input = File(filePath).readLines()

    val result = countXMASPatterns(input)

    println("Number of X-MAS patterns: $result")
}
