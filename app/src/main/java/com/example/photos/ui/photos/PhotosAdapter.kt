package com.example.photos.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Photo
import com.example.photos.databinding.LayoutImageBinding

class PhotosAdapter(private val onItemClick : (Photo) -> Unit) : PagedListAdapter<Photo, PhotosAdapter.ImageViewHolder>(DiffUtilCallBack()) {

    inner class ImageViewHolder(private val binding: LayoutImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            binding.root.setOnClickListener {onItemClick(photo)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemBinding: LayoutImageBinding =
            LayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title == newItem.title
                && oldItem.imgUrl == newItem.imgUrl
    }
}