package com.orlik.crypt.ui.fragments.helpers

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.orlik.crypt.R
import com.orlik.crypt.data.profile.ProfileEntity
import com.orlik.crypt.databinding.ProfileItemBinding
import com.orlik.crypt.ui.synchronizer.Synchronizer

class ProfileAdapter(private val context: Context, private var profiles: ArrayList<ProfileEntity>?):
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ProfileItemBinding.bind(view)
        val tvName = binding.tvName
        val tvDescription = binding.tvDescription
        val ivProfileColor = binding.ivOwnProilfeColor
        val btnRemove = binding.btnRemoveProfile
        val btnChoose = binding.btnChooseProfile
    }

    override fun getItemCount() = profiles?.size ?: 0

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
        if (itemCount == 0) return
        val profile = profiles!![position]

        holder.tvName.text = profile.name
        holder.tvDescription.text = profile.desc
        holder.ivProfileColor.setColorFilter(Color.parseColor(profile.hex))

        holder.btnChoose.setOnClickListener {
            Synchronizer.setCurrentProfile(profile)
            Toast.makeText(context, "${profile.name} has been chosen", Toast.LENGTH_SHORT)
                .show()
        }
        holder.btnRemove.setOnClickListener {
            removeProfile(profile)
            Synchronizer.removeProfile(profile)
            Toast.makeText(context, "${profile.name} has been removed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun addProfile(profile: ProfileEntity) {
        if (profiles == null){
            profiles = ArrayList(0)
        }
        profiles!!.add(profile)
        notifyItemInserted(profiles!!.size)
    }

    private fun removeProfile(profile: ProfileEntity){
        if (profiles == null || profiles?.size == 0) return
        val index = profiles!!.indexOf(profile)
        profiles!!.remove(profile)
        notifyItemRemoved(index)
    }

}