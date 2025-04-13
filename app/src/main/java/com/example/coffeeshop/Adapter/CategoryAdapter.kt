package com.example.coffeeshop.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.Activities.ItemListActivity
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ViewholderCategoryBinding


class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context
    private var selectionPosition = -1
    private var lastSelectionPosition = -1

    inner class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener {
            lastSelectionPosition = selectionPosition
            selectionPosition = position
            notifyItemChanged(lastSelectionPosition)
            notifyItemChanged(selectionPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent =Intent(context,ItemListActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(context,intent,null)
            },500)
        }
        if (selectionPosition == position) {
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(context.getColor(R.color.white))
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(context.getColor(R.color.darkBrow))
        }

    }

    override fun getItemCount(): Int = items.size
}