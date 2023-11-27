package com.example.cleansound

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cleansound.databinding.ActivityLoginBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val REQUEST_CODE = 1337
    private val REDIRECT_URI =  "yourcustomprotocol://callback"
    private val CLIENT_ID = "1a7f0f47d7cb45148c52b560d6459f78"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener{
            val builder = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("Streaming"))
            val request = builder.build()

            AuthorizationClient.openLoginInBrowser(this, request)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE){
//            val response = AuthorizationClient.getResponse(resultCode, data)
//
//            when {
//                response.type == AuthorizationResponse.Type.TOKEN -> {
//                    val accessToken = response.accessToken
//                    Toast.makeText(this,"Access Token : ${accessToken}", Toast.LENGTH_SHORT).show()
//                }
//                response.type == AuthorizationResponse.Type.ERROR -> {
//                    val error = response.error
//                    Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri: Uri? = intent?.data
        if (uri != null){
            val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

            when (response.type){
                AuthorizationResponse.Type.TOKEN -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                AuthorizationResponse.Type.ERROR -> {
                    val error = response.error
                    Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }
    }
}