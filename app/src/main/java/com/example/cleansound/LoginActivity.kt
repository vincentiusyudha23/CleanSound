package com.example.cleansound

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.cleansound.Auth.MyApplication
import com.example.cleansound.databinding.ActivityLoginBinding
import com.example.cleansound.network.SpotifyApiConfig
import com.google.firebase.auth.FirebaseAuth
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var accessToken: String
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()



        binding.rgAkun.setOnClickListener{
            val mainIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        binding.btLogin.setOnClickListener{
            val email = binding.lgEmail.text.toString()
            val password = binding.lgPassword.text.toString()

            if(email.isEmpty()){
                binding.lgEmail.error = "Email Harus Diisi"
                binding.lgEmail.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.lgEmail.error = "Email tidak valid"
                binding.lgEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.lgPassword.error = "Password Harus diisi"
                binding.lgPassword.requestFocus()
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.lgPassword.error = "Password minimal 6 karakter"
                binding.lgPassword.requestFocus()
                return@setOnClickListener
            }
            fetchDataUser(email, password)
        }
    }

    private fun fetchDataUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user = auth.currentUser
                    val userEmail = user?.email
                    AuthenticateSpotify(userEmail)
                } else {
                    Toast.makeText(this, "Errorr: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun AuthenticateSpotify(email: String?){
        (application as MyApplication).accessToken.toString()
            .also { accessToken = it }
        GlobalScope.launch(Dispatchers.Main) {
            val response = SpotifyApiConfig.getSpotifyApiServices().getUserSpotifyData("Bearer $accessToken")

            if(response.isSuccessful){
                val data = response.body()?.email
                if(data != null && data == email){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Email berbeda dengan akun spotify anda", Toast.LENGTH_SHORT).show()
                }

            } else {
                Log.e("Email Gagal", response.errorBody().toString())
            }
        }
    }

}