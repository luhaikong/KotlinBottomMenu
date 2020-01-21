package com.lhk.kotlinbottommenu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lhk.kotlinbottommenu.R
import com.lhk.kotlinbottommenu.entity.Data
import kotlinx.android.synthetic.main.item_list_home.view.*
import kotlinx.android.synthetic.main.item_list_home.view.tv_title
import kotlinx.android.synthetic.main.item_list_type_tree_content.view.*

class TypeTreeContentRvAdapter(var list: MutableList<Data>, var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener:OnItemClickListener?=null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list_type_tree_content, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.tv_title.text = list[position].title
        viewHolder.itemView.tv_desc.text = list[position].desc
        Glide.with(context)
            .load(list[position].envelopePic)
            .into(viewHolder.itemView.iv_cover)
        viewHolder.itemView.tag = position
        viewHolder.itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener!!.onItemClick(list[position],position)
        })
        when (position % 2) {
            0 -> viewHolder.itemView.setBackgroundColor(context.resources.getColor(R.color.colorAccent))
            else -> viewHolder.itemView.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener{
        fun onItemClick(data: Data, position:Int)
    }
}