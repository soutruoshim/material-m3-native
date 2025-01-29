package com.example.material3navigationdrawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val data: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) HeaderHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_for_header, parent, false)
        )
        else ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_row_for_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = data[position]
        if (holder is HeaderHolder && dataItem is String) {
            holder.header.text = data[position] as String
        } else if (holder is ItemHolder && dataItem is DataItem) {
            holder.title.text = dataItem.title
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        // 1 for header and 2 for items
        return if (data[position] is String) 1 else 2
    }

    class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val header: TextView = itemView.findViewById(R.id.header)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }
}