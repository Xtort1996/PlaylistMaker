package com.example.playlistmaker

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.settings_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBackToMain = findViewById<Button>(R.id.btnBack)
        val btnThemeLight = findViewById<Button>(R.id.btnThemeD)
        val btnThemeDark = findViewById<Button>(R.id.btnThemeN)
        val btnShare = findViewById<Button>(R.id.btnShare)
        val btnFeedback = findViewById<Button>(R.id.btnFeedback)
        val btnUAС = findViewById<Button>(R.id.btnUAС)

        btnBackToMain.setOnClickListener{
            val backToMainIntent = Intent(this, MainActivity::class.java)
            startActivity(backToMainIntent)
        }

        btnThemeLight.setOnClickListener{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        btnThemeDark.setOnClickListener{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareMessage))
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        btnFeedback.setOnClickListener{
            val feedbackIntent = Intent(Intent.ACTION_SENDTO)
            feedbackIntent.data = Uri.parse("mailto")
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.supportEmail)))
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.supportMessageSubject))
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.supportMessageBody))
            startActivity(feedbackIntent)
        }

        btnUAС.setOnClickListener {
            val displayUAGIntent = Intent(Intent.ACTION_WEB_SEARCH)
            displayUAGIntent.putExtra(SearchManager.QUERY, getString(R.string.userAgreementContractLink))
            startActivity(displayUAGIntent)
        }
    }
}