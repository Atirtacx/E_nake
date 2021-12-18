package com.example.enaktubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener{
            Intent(this@MainActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        btn_register.setOnClickListener{
            Intent(this@MainActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}