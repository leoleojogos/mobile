package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SimpleCalculator()
                }
            }
        }
    }
}

@Composable
fun SimpleCalculator() {
    var display by remember { mutableStateOf("0") }
    var previousNumber by remember { mutableStateOf<Double?>(null) }
    var currentOp by remember { mutableStateOf<String?>(null) }
    var clearOnNextDigit by remember { mutableStateOf(false) }

    fun handleDigit(digit: String) {
        if (display == "0" || clearOnNextDigit) {
            display = digit
            clearOnNextDigit = false
        } else {
            display += digit
        }
    }

    fun handleOp(op: String) {
        previousNumber = display.toDoubleOrNull()
        currentOp = op
        clearOnNextDigit = true
    }

    fun calculate() {
        val num1 = previousNumber
        val num2 = display.toDoubleOrNull()

        if (num1 != null && num2 != null && currentOp != null) {
            val result = when (currentOp) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
                else -> num2
            }

            display = if (result.isNaN()) {
                "Erro"
            } else if (result % 1.0 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }

            previousNumber = null
            currentOp = null
            clearOnNextDigit = true
        }
    }

    fun clear() {
        display = "0"
        previousNumber = null
        currentOp = null
        clearOnNextDigit = false
    }

    val buttonRows = listOf(
        listOf("1", "2", "3", "+"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "*"),
        listOf("C", "0", "=", "/")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = display,
            fontSize = 44.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 12.dp)
        )

        buttonRows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        onClick = {
                            when (label) {
                                "C" -> clear()
                                "=" -> calculate()
                                "+", "-", "*", "/" -> handleOp(label)
                                else -> handleDigit(label)
                            }
                        }
                    ) {
                        Text(text = label, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleCalculator() {
    SimpleCalculator()
}