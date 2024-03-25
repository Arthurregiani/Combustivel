package br.edu.ifsp.dmo.qualcombustivel

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.qualcombustivel.R.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var gasolineEditText: EditText
    private lateinit var ethanolEditText: EditText
    private lateinit var mButton: Button
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        findById()
        setClick()
    }

    override fun onClick(v: View) {
        if (v == mButton) {
            calculate()
        }
    }

    private fun calculate() {
        if (gasolineEditText.text.toString().isEmpty() || ethanolEditText.text.toString()
                .isEmpty()
        ) {
            Toast.makeText(this, getString(string.toast_informe), Toast.LENGTH_SHORT)
                .show();
            mTextView.text = ""
        } else {
            val gas = retriveValue(gasolineEditText)
            val etha = retriveValue(ethanolEditText)

            val result = etha / gas
            if (result < 0.7) {
                mTextView.text = getString(string.answer_ethanol)
                mTextView.setTextColor(resources.getColor(color.ethanol, this.theme))
            } else {
                mTextView.text = getString(string.answer_gas)
                mTextView.setTextColor(resources.getColor(color.gasoline, this.theme))
            }
        }
    }


    private fun retriveValue(input: EditText): Double {
        return try {
            input.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, getString(string.toast_invalid), Toast.LENGTH_SHORT).show()
            0.0
        }
    }


    private fun findById() {
        gasolineEditText = findViewById(id.edittext_gasoline)
        ethanolEditText = findViewById(id.edittext_ethanol)
        mButton = findViewById(id.button_calculate)
        mTextView = findViewById(id.textview_response)
    }

    private fun setClick() {
        mButton.setOnClickListener(this)
    }

}

