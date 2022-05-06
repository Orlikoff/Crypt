package com.orlik.crypt.ui.fragments.helpers

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.orlik.crypt.R
import com.orlik.crypt.data.Profile
import com.orlik.crypt.databinding.ProfileItemBinding

class ProfileAdapter(private val context: Context, private val profiles: List<Profile>):
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ProfileItemBinding.bind(view)
        val tvName = binding.tvName
        val tvDescription = binding.tvDescription
        val ivProfileColor = binding.ivOwnProilfeColor
        val btnRemove = binding.btnRemoveProfile
        val btnChoose = binding.btnChooseProfile
    }

    override fun getItemCount() = profiles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.profile_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profiles[position]

        holder.tvName.text = profile.name
        holder.tvDescription.text = profile.desc
        holder.ivProfileColor.setColorFilter(Color.parseColor(profile.hex))
        holder.btnChoose.setOnClickListener {
            Toast.makeText(context, "${profile.id} chosen", Toast.LENGTH_SHORT).show()
        }
        holder.btnRemove.setOnClickListener {
            Toast.makeText(context, "${profile.id} removed", Toast.LENGTH_SHORT).show()
        }
    }

}