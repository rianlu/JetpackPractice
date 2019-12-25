package com.example.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(isCardView: Boolean) : ListAdapter<User, RecyclerViewAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var isCardView = false

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                    oldUser: User, newUser: User): Boolean {
                return oldUser.id == newUser.id
            }

            override fun areContentsTheSame(
                    oldUser: User, newUser: User): Boolean {
                return oldUser.name.equals(newUser.name)
            }
        }
    }
    init {
        this.isCardView = isCardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        var view: View? = null
        if (!isCardView) {
            view = layoutInflater.inflate(R.layout.cell_normal, parent, false)
        } else {
            view = layoutInflater.inflate(R.layout.cell_card, parent, false)
        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = getItem(position)
        holder.numberView?.text = (position + 1).toString()
        holder.nameView?.text = user.name
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var numberView: TextView? = null;
        var nameView: TextView? = null
        var switch: Switch? = null
        init {
            numberView = itemView.findViewById(R.id.numView)
            nameView = itemView.findViewById(R.id.nameView)
            switch = itemView.findViewById(R.id.switch1)
        }
    }
}
