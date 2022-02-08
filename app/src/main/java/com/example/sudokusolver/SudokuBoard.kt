package com.example.sudokusolver

class SudokuBoard(val board: Array<Array<Int>>) {

    companion object {
        //empty cell represents the unassigned constant
        private const val UNASSIGNED = 0

        //number of rows and cols in a box
        const val BOX_SIZE = 3

        //the number of rows and cols
        const val N = 9
    }

    fun solve(row: Int, col: Int): Boolean {
        var x = row
        var y = col
        if (x == N - 1 && y == N) {
            return true
        }
        if (y == N) {
            x += 1
            y = 0
        }
        if (board[x][y] != UNASSIGNED) {
            return solve(x, y + 1)
        }
        for (num in 1..9) {
            if (isSafe(x, y, num)) {
                board[x][y] = num

                if (solve(x, y + 1))
                    return true
            }
            board[x][y] = UNASSIGNED
        }
        return false
    }

    private fun isSafe(row: Int, col: Int, num: Int): Boolean {
        for (i in 0 until N) {
            if (board[row][i] == num) {
                return false
            }
        }
        for (i in 0 until N) {
            if (board[i][col] == num) {
                return false
            }
        }
        val startRow = row - row % 3
        val startCol = col - col % 3

        for (i in 0 until BOX_SIZE) {
            for (j in 0 until BOX_SIZE) {
                if (board[i + startRow][j + startCol] == num) {
                    return false
                }
            }
        }
        return true
    }

    private fun usedInCol(col: Int, num: Int): Boolean {
        for (i in 0 until N) {
            if (board[i][col] == num) {
                return true
            }
        }
        return false
    }

}