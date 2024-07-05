package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ViewBinding

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardInfo = data[position]
        when (cardInfo.priority) {
            "high" -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        holder.binding.title.text = cardInfo.title
        holder.binding.priority.text = cardInfo.priority
        holder.binding.checkBox.isChecked = cardInfo.isDone

        holder.binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            cardInfo.isDone = isChecked
            
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
