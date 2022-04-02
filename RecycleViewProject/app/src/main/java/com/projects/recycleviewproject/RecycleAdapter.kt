package com.projects.recycleviewproject

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.recycleviewproject.R
import com.projects.recycleviewproject.Data
import android.util.Log

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            Log.i("ViewHolder", "inside ViewHolder init")
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        Log.i("RecycleAdapter", "inside onCreateViewHolder, i: " + i)
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.i("RecycleAdapter", "inside onBindViewHolder, i: " + i)
        Log.i("RecycleAdapter", Data.randomDetails.toString())
        Log.i("RecycleAdapter", Data.randomImages.toString())
        Log.i("RecycleAdapter", Data.randomTitles.toString())
        viewHolder.itemTitle.text = Data.titles[Data.randomTitles[i]]
        viewHolder.itemDetail.text = Data.details[Data.randomDetails[i]]
        viewHolder.itemImage.setImageResource(Data.images[Data.randomImages[i]])
    }

    override fun getItemCount(): Int {
        return Data.titles.size
    }
}