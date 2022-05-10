package com.example.firebaseauth

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivityLandingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.landingSubmit.setOnClickListener {
            val userName = binding.userName.text.toString()
            val userPhone = binding.userPhone.text.toString()
            if (userName.isNotEmpty() && userPhone.isNotEmpty()){
                createUserDatabase()
            }

        }

    }

    private fun createUserDatabase() {

    }
}