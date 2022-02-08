package com.example.sudokusolver

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val board = arrayOf(
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
        )

        btnSolve.setOnClickListener {
            tvSolvingError.text = ""

            for (i in 0 until gridLayout.rowCount) {
                for (j in 0 until gridLayout.columnCount) {
                    val et = gridLayout.getChildAt(i * SudokuBoard.N + j) as EditText
                    if (et.text.isNotEmpty()) {
                        board[i][j] = et.text.toString().toInt()
                        et.setTypeface(et.typeface, Typeface.BOLD)
                    }
                }
            }
            val sudokuBoard = SudokuBoard(board)



            if (sudokuBoard.solve(0, 0)) {
                for (i in 0 until SudokuBoard.N) {
                    for (j in 0 until SudokuBoard.N) {
                        val et = gridLayout.getChildAt(i * SudokuBoard.N + j) as EditText
                        et.setText(sudokuBoard.board[i][j].toString())
                    }
                }

            } else {
                tvSolvingError.text = "Sudoku configuration can't be solved."
            }

        }

        btnClear.setOnClickListener {
            for (i in 0 until gridLayout.rowCount) {
                for (j in 0 until gridLayout.columnCount) {
                    val et = gridLayout.getChildAt(i * SudokuBoard.N + j) as EditText
                    et.setText("")
                    et.typeface = Typeface.create(et.typeface, Typeface.NORMAL)
                    tvSolvingError.setText("")
                    for(i in 0 until SudokuBoard.N){
                        for(j in 0 until SudokuBoard.N){
                            board[i][j]=0
                        }
                    }
                }
            }
        }
    }
}