package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result: TextView = findViewById(R.id.result)
        val operation: TextView = findViewById(R.id.operation)

        // Numbers
        val numbers = listOf(
            R.id.b_0 to "0", R.id.b_1 to "1", R.id.b_2 to "2",
            R.id.b_3 to "3", R.id.b_4 to "4", R.id.b_5 to "5",
            R.id.b_6 to "6", R.id.b_7 to "7", R.id.b_8 to "8",
            R.id.b_9 to "9", R.id.b_dot to "."
        )

        numbers.forEach { (id, value) ->
            findViewById<TextView>(id).setOnClickListener { operation.append(value) }
        }

        // Operators
        val operators = listOf(
            R.id.b_plus to "+", R.id.b_minus to "-", R.id.b_mul to "*",
            R.id.b_div to "/", R.id.b_leftb to "(", R.id.b_rightb to ")"
        )

        operators.forEach { (id, value) ->
            findViewById<TextView>(id).setOnClickListener { operation.append(value) }
        }

        // Sci Operations
        val sciOps = listOf(
            R.id.b_sqrt to "sqrt(", R.id.b_log2 to "log2(", R.id.b_ln to "log(",
            R.id.b_sin to "sin(", R.id.b_cos to "cos(", R.id.b_tan to "tan(",
            R.id.b_log to "log10(", R.id.b_pow to "^",
            R.id.b_pi to "pi", R.id.b_e to "e"
        )

        sciOps.forEach { (id, value) ->
            findViewById<TextView>(id).setOnClickListener { operation.append(value) }
        }

        // Back button
        findViewById<TextView>(R.id.b_back).setOnClickListener {
            val s = operation.text.toString()
            if (s.isNotEmpty()) {
                operation.text = s.substring(0, s.length - 1)
            }
        }

        // Clear button
        findViewById<TextView>(R.id.b_clear).setOnClickListener {
            operation.text = ""
            result.text = ""
        }

        // Result field click
        result.setOnClickListener {
            val resText = result.text.toString()
            if (resText != "Error" && resText.isNotEmpty()) {
                operation.text = resText
                result.text = ""
            }
        }

        // Equals button
        findViewById<TextView>(R.id.b_eq).setOnClickListener {
            val opText = operation.text.toString()
            if (opText.isNotEmpty()) {
                try {
                    val expr = ExpressionBuilder(opText).build()
                    val res = expr.evaluate()
                    val longRes = res.toLong()
                    if (longRes.toDouble() == res) {
                        result.text = longRes.toString()
                    } else {
                        result.text = res.toString()
                    }
                } catch (e: Exception) {
                    result.text = "Error"
                }
            }
        }
    }
}
