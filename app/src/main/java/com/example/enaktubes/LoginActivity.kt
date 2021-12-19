package com.example.enaktubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.inputEmail
import kotlinx.android.synthetic.main.activity_login.inputPassword
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        tmbl_newAcc.setOnClickListener{
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        tmbl_login.setOnClickListener{
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if (email.isEmpty()){
                inputEmail.error = "Email harus diisi"
                inputEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                inputEmail.error = "Email tidak valid"
                inputEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                inputPassword.error = "Password harus lebih dari 6 karakter"
                inputPassword.requestFocus()
                return@setOnClickListener
            }
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@LoginActivity,RegisterActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@LoginActivity, MainActivity::class.java).also {
                intent -> intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}