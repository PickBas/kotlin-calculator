package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var expression = ""

    private var result = ""

    private var cleared = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calc_operations.text = "0"


        btn_0.setOnClickListener { appendText("0") }
        btn_1.setOnClickListener { appendText("1") }
        btn_2.setOnClickListener { appendText("2") }
        btn_3.setOnClickListener { appendText("3") }
        btn_4.setOnClickListener { appendText("4") }
        btn_5.setOnClickListener { appendText("5") }
        btn_6.setOnClickListener { appendText("6") }
        btn_7.setOnClickListener { appendText("7") }
        btn_8.setOnClickListener { appendText("8") }
        btn_9.setOnClickListener { appendText("9") }

        btn_dot_sign.setOnClickListener {
            if (expression[expression.length - 1].isDigit()) {
                expression += "."
                calc_operations.append(".")
            }
        }

        operation_sign_number.setOnClickListener {
            val number = calc_operations.text.toString()
            if (number.isNotEmpty()) {
                if (number[0].isDigit()) {
                    calc_operations.text = "-$number";
                } else {
                    calc_operations.text = number.substring(1, number.length)
                }
                expression += "*-1"
            }
        }

        operation_procent.setOnClickListener { operationsProcess("%") }
        operation_addition.setOnClickListener { operationsProcess("+") }
        operation_minus.setOnClickListener { operationsProcess("-") }
        operation_multiply.setOnClickListener { operationsProcess("*") }
        operation_division.setOnClickListener { operationsProcess("/") }

        operation_all_clear.setOnClickListener {
            cleared = true
            calc_operations.text = "0"
            result = ""
            expression = ""
        }


        operation_equal.setOnClickListener {
            try {
                val expr_builder = ExpressionBuilder(expression).build()
                val res = expr_builder.evaluate()

                if (res == res.toLong().toDouble()) {
                    result = res.toLong().toString()
                    expression = res.toLong().toString()
                    calc_operations.text = res.toLong().toString()
                } else {
                    result = res.toString()
                    expression = res.toString()
                    calc_operations.text = res.toString()
                }

            } catch (e: Exception) {
                Log.wtf("ERROR", "Message: ${e.message}")
            }

        }

        operation_del.setOnClickListener {
            val nm = calc_operations.text.toString()
            if (nm.isNotEmpty()) {
                calc_operations.text = nm.substring(0, nm.length - 1)
            }

            result = ""
        }

    }

    private fun appendText(str: String) {
        if (result != "") {
            calc_operations.text = result
            result = ""
        }
        if (cleared) {
            calc_operations.text = ""
            cleared = false
        }
        expression += str
        calc_operations.append(str)
    }

    private fun operationsProcess(str: String) {
        if (expression.isEmpty())
            return;

        if (!expression[expression.length - 1].isDigit())
            return;

        if (result != "") {
            result = ""
        }

        calc_operations.text = ""

        expression += str
    }

}
