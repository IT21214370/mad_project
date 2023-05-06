package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUpdateactivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Updateactivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateactivityBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referenceTopic = binding.referenceTopic.text.toString()
            val updateName = binding.updateName.text.toString()
            val updatePhone = binding.updatePhone.text.toString()
            val updateEmail = binding.updateEmail.text.toString()
            val updateQuentity = binding.updateQuentity.text.toString()
            val updateDiscription = binding.updateDiscription.text.toString()

            updateData(referenceTopic,updateName,updatePhone,updateEmail,updateQuentity,updateDiscription)

        }
    }

    private fun updateData( topic: String, name: String,phone: String, email: String, quentity: String, discription: String) {
        database = FirebaseDatabase.getInstance().getReference("User Directory")
        val user = mapOf(
            "name" to name,
            "phone" to phone,
            "email" to email,
            "quentity" to quentity,
            "discription" to discription

        )
        database.child(topic).updateChildren(user).addOnSuccessListener {
            binding.referenceTopic.text.clear()
            binding.updateName.text.clear()
            binding.updatePhone.text.clear()
            binding.updateEmail.text.clear()
            binding.updateQuentity.text.clear()
            binding.updateDiscription.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
    }



