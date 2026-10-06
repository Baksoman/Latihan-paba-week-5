package com.example.latihan_paba_week_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectRoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)

        val btnAdmin = findViewById<Button>(R.id.btnAdmin)
        val btnUser = findViewById<Button>(R.id.btnUser)
        val btnGuest = findViewById<Button>(R.id.btnGuest)

        btnAdmin.setOnClickListener { sendRoleResult("Admin") }
        btnUser.setOnClickListener { sendRoleResult("User") }
        btnGuest.setOnClickListener { sendRoleResult("Guest") }
    }

    private fun sendRoleResult(role: String) {
        val intent = Intent().apply {
            putExtra("EXTRA_SELECTED_ROLE", role)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}