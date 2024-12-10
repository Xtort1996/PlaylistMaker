package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBackToMain = findViewById<Button>(R.id.btnBack)
        val inputTextBar = findViewById<EditText>(R.id.search_bar)
        val clearButton = findViewById<ImageView>(R.id.clearIcon)

        val simpleTextWatcher = object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //empty
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = clearButtonVisibility(s)
            }

            override fun afterTextChanged(s: Editable?) {
                //empty
            }

            private fun clearButtonVisibility(s: CharSequence?): Int{
                return if(s.isNullOrEmpty()){
                    View.GONE
                }else{
                    View.VISIBLE
                }
            }
        }
        inputTextBar.addTextChangedListener(simpleTextWatcher)

        fun hideKeyboard(){
            val kbr = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            kbr.hideSoftInputFromWindow(inputTextBar.windowToken,0)
        }

        btnBackToMain.setOnClickListener{
            val backToMainIntent = Intent(this, MainActivity::class.java)
            startActivity(backToMainIntent)
        }

        clearButton.setOnClickListener{
            inputTextBar.setText("")
            hideKeyboard()
        }
    }
}