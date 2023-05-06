package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val topic = binding.deleteTopic.text.toString()
            if (topic.isNotEmpty())
                deleteData(topic)
            else
                Toast.makeText(this, "Please enter Topic", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(topic: String){
        database = FirebaseDatabase.getInstance().getReference("User Directory")
        database.child(topic).removeValue().addOnSuccessListener {
            binding.deleteTopic.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}





