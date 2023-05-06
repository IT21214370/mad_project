package com.example.cocozone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cocozone.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchTopic : String = binding.searchTopic.text.toString()
            if  (searchTopic.isNotEmpty()){
                readData(searchTopic)
            }else{
                Toast.makeText(this,"PLease enter your post topic",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(topic: String) {
        database = FirebaseDatabase.getInstance().getReference("User Directory")
        database.child(topic).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("name").value
                val phone = it.child("phone").value
                val email = it.child("email").value
                val quentity = it.child("quentity").value
                val discription = it.child("discription").value
                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.readName.text = name.toString()
                binding.readPhone.text = phone.toString()
                binding.readEmail.text = email.toString()
                binding.searchTopic.text.clear()
                binding.readQuentity.text = quentity.toString()
                binding.readDiscription.text = discription.toString()
            }else{
                Toast.makeText(this,"Topic does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}
