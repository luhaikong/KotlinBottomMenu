package com.lhk.kotlinbottommenu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lhk.kotlinbottommenu.R
import com.lhk.kotlinbottommenu.entity.TypeTree
import kotlinx.android.synthetic.main.item_list_home.view.*

class HomeRvAdapter(var list: MutableList<TypeTree>, var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener:OnItemClickListener?=null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list_home, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.tv_title.text = list[position].name
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
        fun onItemClick(typeTree: TypeTree, position:Int)
    }
}