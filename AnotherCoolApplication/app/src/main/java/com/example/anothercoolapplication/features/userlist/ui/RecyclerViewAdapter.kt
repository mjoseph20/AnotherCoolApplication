package com.example.anothercoolapplication.features.userlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anothercoolapplication.core.data.db.UserEntity
import com.example.anothercoolapplication.databinding.RecyclerviewRowBinding

class RecyclerViewAdapter( val listener: RowClickListener?): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            listener?.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(private val binding: RecyclerviewRowBinding, val listener: RowClickListener?) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserEntity) {
            binding.tvName.text = data.name
            binding.tvEmail.text = data.email
            binding.tvPhone.text = data.phone

            binding.deleteUserId.setOnClickListener {
                listener?.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }
}