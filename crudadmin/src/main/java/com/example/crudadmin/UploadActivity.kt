package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val phone = binding.uploadPhone.text.toString()
            val email = binding.uploadEmail.text.toString()
            val topic = binding.uploadTopic.text.toString()
            val quentity = binding.uploadQuentity.text.toString()
            val discription = binding.uploadDiscription.text.toString()


            database = FirebaseDatabase.getInstance().getReference("User Directory")
            val users = UserData(name, phone, email, topic, quentity, discription)
            database.child(topic).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadPhone.text.clear()
                binding.uploadEmail.text.clear()
                binding.uploadTopic.text.clear()
                binding.uploadQuentity.text.clear()
                binding.uploadDiscription.text.clear()

                Toast.makeText(this,"Posted",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}








