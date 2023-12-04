package com.example.cleansound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.cleansound.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        var btnRegister = binding.btRegister

        btnRegister.setOnClickListener{
            val email = binding.rgEmail.text.toString()
            val password = binding.rgPassword.text.toString()
            val userName = binding.rgUsername.text.toString()

            if(email.isEmpty()){
                binding.rgEmail.error = "Email Harus Diisi"
                binding.rgEmail.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.rgEmail.error = "Email tidak valid"
                binding.rgEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.rgPassword.error = "Password Harus diisi"
                binding.rgPassword.requestFocus()
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.rgPassword.error = "Password minimal 6 karakter"
                binding.rgPassword.requestFocus()
                return@setOnClickListener
            }
            if(userName.isEmpty()){
                binding.rgUsername.error = "Username harus diisi"
                binding.rgUsername.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,password)
        }
    }
    private fun RegisterFirebase(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Registered Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}