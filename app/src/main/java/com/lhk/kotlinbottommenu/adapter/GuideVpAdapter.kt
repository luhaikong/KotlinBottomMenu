package com.lhk.kotlinbottommenu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lhk.kotlinbottommenu.R

class GuideVpAdapter(var urls: MutableList<String>, var context: Context): RecyclerView.Adapter<GuideVpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list_guide, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(urls[position])
            .into(holder.iv_guide)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        public var iv_guide:ImageView

        init {
            this.iv_guide = itemView.findViewById(R.id.iv_guide)
        }

    }
}