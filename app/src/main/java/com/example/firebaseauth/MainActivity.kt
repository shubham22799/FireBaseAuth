package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val TAG = "MAinActivity:"
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            login()
        }

        binding.loginToRegister.setOnClickListener {
            binding.loginScreen.visibility = View.GONE
            binding.registerScreen.visibility = View.VISIBLE
        }
        binding.register.setOnClickListener {
            register()
        }
    }

    private fun login() {
        val emailLogin = binding.loginEmail.text.toString()
        val passwordLogin = binding.loginPassword.text.toString()
        if (emailLogin.isNotEmpty() && passwordLogin.isNotEmpty()) {

            Log.v(TAG, "$emailLogin/$passwordLogin")
            loginUserWithFireBase(emailLogin, passwordLogin)

        } else {
            Toast.makeText(this, "Empty Fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUserWithFireBase(emailLogin: String, passwordLogin: String) {
        auth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener{ task ->
            if (task.isSuccessful){

                Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LandingActivity::class.java))

            }
        }.addOnFailureListener{exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()

        }

    }

    private fun register() {
        val emailRegister = binding.registerEmail.text.toString()
        val passwordRegister = binding.registerPassword.text.toString()

        if (emailRegister.isNotEmpty() && passwordRegister.isNotEmpty()) {
            Log.v(TAG, "$emailRegister/$passwordRegister")

            registerUserOnFireBase(emailRegister, passwordRegister)

        } else {
            Toast.makeText(this, "Empty Fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUserOnFireBase(emailRegister: String, passwordRegister: String) {
        auth.createUserWithEmailAndPassword(emailRegister, passwordRegister).addOnCompleteListener{ task ->
        if (task.isSuccessful){

            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()

            binding.registerScreen.visibility = View.GONE
            binding.loginScreen.visibility = View.VISIBLE
        }
        }.addOnFailureListener{exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()

        }
    }
}

