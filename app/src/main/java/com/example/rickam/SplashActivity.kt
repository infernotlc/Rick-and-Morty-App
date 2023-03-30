package com.example.rickam
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.os.Handler
import android.os.Looper
import android.content.Intent
class SplashActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Karşılama metnini ayarla
        welcomeText = findViewById(R.id.welcome_text)
        if (isFirstLaunch()) {
            welcomeText.setText(R.string.welcome_message)
        } else {
            welcomeText.setText(R.string.hello_message)
        }

        // 2 saniye bekleyin ve uygulama ana ekranına geçin
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }

    private fun isFirstLaunch(): Boolean {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)
        if (isFirstLaunch) {
            sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
        }
        return isFirstLaunch
    }
}
