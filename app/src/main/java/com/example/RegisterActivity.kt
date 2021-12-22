
package com.example.enaktubes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        tmbl_register.setOnClickListener{
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
            registerUser(email, password)
        }

    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful)
                Intent(this@RegisterActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

}


