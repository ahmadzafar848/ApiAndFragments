package com.example.apiandfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val usersList: ArrayList<UserModelItem>,
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(userModelItem: UserModelItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_rv_layout, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userId.text = usersList[position].id.toString()
        holder.userName.text = usersList[position].name.toString()
        holder.itemView.setOnClickListener {
            onItemClick.onItemClicked(usersList[position])
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId = itemView.findViewById<TextView>(R.id.tvUserId)
        val userName = itemView.findViewById<TextView>(R.id.tvUserName)

    }
}