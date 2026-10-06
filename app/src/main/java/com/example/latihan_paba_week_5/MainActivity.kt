package com.example.latihan_paba_week_5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var tvRole: TextView

    private val selectRoleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedRole = result.data?.getStringExtra("EXTRA_SELECTED_ROLE")
            selectedRole?.let {
                tvRole.text = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,0)
            insets
        }

        val itemEmail = findViewById<LinearLayout>(R.id.account_information_item1)
        val itemPhone = findViewById<LinearLayout>(R.id.account_information_item2)
        val itemRole = findViewById<LinearLayout>(R.id.account_information_item4)

        val textEmail = findViewById<TextView>(R.id.email_text)
        val textPhone = findViewById<TextView>(R.id.phone_text)
        tvRole = findViewById(R.id.role_text)

        itemEmail.setOnClickListener {
            val email = textEmail.text.toString()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            startActivity(intent)
        }

        itemPhone.setOnClickListener {
            val phone = textPhone.text.toString()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            startActivity(intent)
        }

        itemRole.setOnClickListener {
            val intent = Intent(this, SelectRoleActivity::class.java)
            selectRoleLauncher.launch(intent)
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNav.selectedItemId = R.id.nav_profile
    }
}