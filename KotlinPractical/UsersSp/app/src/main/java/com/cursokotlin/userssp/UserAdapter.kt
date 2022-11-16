package com.cursokotlin.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursokotlin.userssp.databinding.ItemsUserAltBinding


class UserAdapter(private val users:List<User>, private val listener:OnClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context= parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.items_user_alt, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user= users.get(position)
        val humanPosition=position+1

        with(holder){
            setListener(user, humanPosition)
            binding.tvOrder.text= humanPosition.toString()
            binding.tvName.text= user.getfullName()
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val binding = ItemsUserAltBinding.bind(view)

        fun setListener(user:User, position: Int){
            binding.root.setOnClickListener{listener.onClick(user, position)}
        }
    }
}