package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        operation_addition.setOnClickListener { operationsProcess("+") }
        operation_minus.setOnClickListener { operationsProcess("-") }
        operation_multiply.setOnClickListener { operationsProcess("*") }
        operation_division.setOnClickListener { operationsProcess("/") }

        operation_all_clear.setOnClickListener {
            calc_operations.text = ""
            result.text = ""
            expression = ""
        }


        operation_equal.setOnClickListener {
            try {
                println(expression)
                val expr_builder = ExpressionBuilder(expression).build()
                val res = expr_builder.evaluate()

                if (res == res.toLong().toDouble()) {
                    result.text = res.toLong().toString()
                } else {
                    result.text = res.toString()
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

            result.text = ""
        }

    }

    private fun appendText(str: String) {
        if (result.text != "") {
            calc_operations.text = result.text
            expression = result.text.toString();
            result.text = ""
        }
        expression += str
        calc_operations.append(str)
    }

    private fun operationsProcess(str: String) {
        if (!expression[expression.length - 1].isDigit())
            return;

        if (result.text != "") {
            expression = result.text.toString();
            result.text = ""
        }

        calc_operations.text = ""

        expression += str
    }
}
