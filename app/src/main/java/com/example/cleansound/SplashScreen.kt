package com.example.cleansound

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.cleansound.Auth.MyApplication
import com.example.cleansound.databinding.ActivitySplashScreenBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse


class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val REQUEST_CODE = 1337
    private val REDIRECT_URI =  "cleansound://callback"
    private val CLIENT_ID = "1a7f0f47d7cb45148c52b560d6459f78"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
        Handler(Looper.getMainLooper()).postDelayed({
            val builder = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
            builder.setScopes(arrayOf("streaming"))
            val request: AuthorizationRequest = builder.build()

//            AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
            AuthorizationClient.openLoginInBrowser(this, request)

        }, 3000)

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//
//        if (requestCode == REQUEST_CODE){
//            val response = AuthorizationClient.getResponse(resultCode, data)
//            when (response.type){
//                AuthorizationResponse.Type.TOKEN -> {
//                    val accessToken = response.accessToken
//                    val mainIntent = Intent(this, LoginActivity::class.java)
//                    (application as MyApplication).accessToken = accessToken
//                    startActivity(mainIntent)
//                    finish()
//                }
//                AuthorizationResponse.Type.CODE -> {
//                    val authorizationCode = response.code
//                    showToast("Authorization Code: $authorizationCode")
//                }
//                AuthorizationResponse.Type.ERROR -> {
//                    val error = response.error
//                    showToast("Authrization Errorr: $error")
//                }
//
//               else -> {
//                   //Tidak ada
//               }
//            }
//        }
//    }
override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)

    val uri: Uri? = intent.data
    if (uri != null) {
        val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

        when (response.type) {
            // Response was successful and contains auth token
            AuthorizationResponse.Type.TOKEN -> {
                val accessToken = response.accessToken
                val mainIntent = Intent(this, LoginActivity::class.java)
                   (application as MyApplication).accessToken = accessToken
                    startActivity(mainIntent)
                    finish()
            }

            // Auth flow returned an error
            AuthorizationResponse.Type.ERROR -> {
                val error = response.error
                showToast("Authrization Errorr: $error")
            }

            // Most likely auth flow was cancelled
            else -> {
                // Handle other cases
            }
        }
    }
}


    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}