package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.todo.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityCreateCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).fallbackToDestructiveMigration().build()

        // Set up the save button click listener
        binding.saveButton.setOnClickListener {
            val title = binding.createTitle.text.toString().trim()
            val priority = binding.createPriority.text.toString().trim()
            if (title.isNotEmpty() && priority.isNotEmpty()) {
                DataObject.setData(title, priority , false)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, isDone=false))
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
