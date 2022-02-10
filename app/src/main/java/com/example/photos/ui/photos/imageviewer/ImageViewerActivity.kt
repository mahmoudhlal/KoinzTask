package com.example.photos.ui.photos.imageviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.photos.databinding.ActivityImageViewerBinding

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding
    private val imVViewModel: ImageViewerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundledData = intent.getStringExtra("photo")

        bundledData?.let { imVViewModel.setUrl(it) }
        imVViewModel.getPhoto().observe(this, Observer {
            binding.photo = it
        })
    }
}