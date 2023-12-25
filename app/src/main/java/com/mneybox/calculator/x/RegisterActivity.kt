package com.mneybox.calculator.x

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if(getSharedPreferences("prefs",Context.MODE_PRIVATE).getString("name",null)!=null) {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}