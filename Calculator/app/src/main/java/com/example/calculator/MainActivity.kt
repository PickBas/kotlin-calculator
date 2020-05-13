package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

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

        operation_addition.setOnClickListener { appendText("+") }
        operation_minus.setOnClickListener { appendText("-") }
        operation_multiply.setOnClickListener { appendText("*") }
        operation_division.setOnClickListener { appendText("/") }

        operation_all_clear.setOnClickListener {
            calc_operations.text = ""
        }


        operation_equal.setOnClickListener {
            try {
                val expr_builder = ExpressionBuilder(calc_operations.text.toString()).build()
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
            result.text = ""
        }
        calc_operations.append(str)
    }
}
