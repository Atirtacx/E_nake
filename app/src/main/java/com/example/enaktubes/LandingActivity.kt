package com.example.enaktubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        btn_login.setOnClickListener{
            Intent(this@LandingActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        btn_register.setOnClickListener{
            Intent(this@LandingActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}