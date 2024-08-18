package com.example.calculatorsapp3

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculatorsapp3.databinding.ActivityMainBinding

data class Operation(val operator: String, val number: Double)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentNumber = ""
    private var operator = ""
    private var isNewOp = true
    private val operations = mutableListOf<Operation>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn0.setOnClickListener { numberAction(it) }
        binding.btn1.setOnClickListener { numberAction(it) }
        binding.btn2.setOnClickListener { numberAction(it) }
        binding.btn3.setOnClickListener { numberAction(it) }
        binding.btn4.setOnClickListener { numberAction(it) }
        binding.btn5.setOnClickListener { numberAction(it) }
        binding.btn6.setOnClickListener { numberAction(it) }
        binding.btn7.setOnClickListener { numberAction(it) }
        binding.btn8.setOnClickListener { numberAction(it) }
        binding.btn9.setOnClickListener { numberAction(it) }
        binding.btnhan.setOnClickListener { operationAction(it) }
        binding.btnTru.setOnClickListener { operationAction(it) }
        binding.btnCong.setOnClickListener { operationAction(it) }
        binding.btnChia.setOnClickListener { operationAction(it) }
        binding.btnEquals.setOnClickListener { equalsAction(it) }
        binding.buttonAC.setOnClickListener { allClearAction(it) }
        binding.buttonPercent.setOnClickListener { percentAction(it) }
        binding.btnComma.setOnClickListener { numberAction(it) }
        binding.buttonAmDuong.setOnClickListener { AmduongAction(it) }
    }

    private fun percentAction(view: View?) {
        val number = binding.textResult.text.toString().toDouble() / 100
        binding.textResult.text = number.toString()
        isNewOp = true
    }

    private fun AmduongAction(view: View?) {
        val currentText = binding.textResult.text.toString()
        if (currentText.isNotEmpty()) {
            if (currentText.startsWith("-")) {
                binding.textResult.text = currentText.substring(1)
            } else {
                binding.textResult.text = "-$currentText"
            }
        }


    }

    private fun allClearAction(view: View) {
        binding.textResult.text = "0"
        isNewOp = true
        operations.clear()
    }

    private fun equalsAction(view: View) {
        val expression = binding.textResult.text.toString()

        try {
            val result = evaluateExpression(expression)
            binding.textResult.text = result.toString()
        } catch (e: Exception) {
            binding.textResult.text = "Error"
        }

        isNewOp = true
    }
    // private fun equalsAction(view: View) {
//        val newNumber = binding.textResult.text.toString().toDouble()
//        operations.add(Operation(operator, newNumber))
//
//        var result = operations[0].number
//        for (i in 1 until operations.size) {
//            val op = operations[i]
//            result = when (op.operator) {
//                "+" -> result + op.number
//                "-" -> result - op.number
//                "*" -> result * op.number
//                "/" -> result / op.number
//                else -> result
//            }
//        }
//
//        binding.textResult.text = result.toString()
//        isNewOp = true
//        operations.clear()
//
//
//
//    }


//    private fun operationAction(view: View) {
//        val selectedButton = view as Button
//        val currentText = binding.textResult.text.toString()
//        if (currentText.isNotEmpty()) {
//            val number = currentText.toDouble()
//            operations.add(Operation(operator, number))
//        }
//
//        operator = when (selectedButton.id) {
//            binding.btnCong.id -> "+"
//            binding.btnTru.id -> "-"
//            binding.btnhan.id -> "*"
//            binding.btnChia.id -> "/"
//            else -> ""
//        }
//        isNewOp = true
//
//
//
//
//    }

    private fun operationAction(view: View) {
        val selectedButton = view as Button
        if (isNewOp) binding.textResult.text = ""
        isNewOp = false

        val buttonClickValue: String = selectedButton.text.toString()
        binding.textResult.append(buttonClickValue)
    }


    private fun numberAction(view: View) {
        if (isNewOp) binding.textResult.text = ""
        isNewOp = false

        val selectedButton = view as Button
        var buttonClickValue: String = binding.textResult.text.toString()
        buttonClickValue += selectedButton.text
        binding.textResult.text = buttonClickValue

    }

    private fun evaluateExpression(expression: String): Double {
        var result = 0.0
        var currentNumber = ""
        var lastOperator = '+'

        for (char in expression) {
            if (char.isDigit() || char == '.') {
                currentNumber += char
            } else {
                result = performOperation(result, currentNumber.toDouble(), lastOperator)
                currentNumber = ""
                lastOperator = char
            }
        }


        if (currentNumber.isNotEmpty()) {
            result = performOperation(result, currentNumber.toDouble(), lastOperator)
        }

        return result
    }

    private fun performOperation(acc: Double, num: Double, operator: Char): Double {
        return when (operator) {
            '+' -> acc + num
            '-' -> acc - num
            '*' -> acc * num
            '/' -> acc / num
            else -> acc
        }


    }
}