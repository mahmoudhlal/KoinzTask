package com.example.photos.ui.photos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.common.Result
import com.example.domain.entities.Photo
import com.example.photos.R
import com.example.photos.databinding.ActivityMainBinding
import com.example.photos.ui.photos.imageviewer.ImageViewerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val photosViewModel : PhotosViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        viewModelObserver()
    }

    private fun viewModelObserver(){
        photosViewModel.getPhotos().observe(this, Observer {
            if (it != null)
                (binding.rvPhotos.adapter as PhotosAdapter).submitList(it)
        })

        photosViewModel.getProgressStatus().observe(this, Observer {
            when(it){
                is Result.Error -> displayError(it.msg)
                is Result.Success -> hideLoading()
                is Result.Loading -> displayLoading()
            }
        })
    }

    private fun init() {
        binding.rvPhotos.adapter = PhotosAdapter {openImageViewer(it)}
    }

    private fun displayLoading() {
        binding.progressID.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressID.visibility = View.GONE
    }

    private fun displayError(errorMessage: String) {
        hideLoading()
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun openImageViewer(photo : Photo){
        val intent = Intent(this , ImageViewerActivity::class.java)
        intent.putExtra("photo" , photo.imgUrl)
        startActivity(intent)
    }
}