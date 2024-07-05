package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.todo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).fallbackToDestructiveMigration().build()

        // Set up the add button click listener
        binding.add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }

        // Set up the deleteAll button click listener
        binding.deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        // Set up the RecyclerView
        setRecycler()
    }

    private fun setRecycler() {
        binding.recyclerView.adapter = Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
