package com.example.photos.ui.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.common.Result
import com.example.photos.R
import com.example.photos.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val photosViewModel : PhotosViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()

        photosViewModel.getPhotos().observe(this, Observer {
            if (it != null)
                (binding.rvPhotos.adapter as PhotosAdapter).submitList(it)
        })

        photosViewModel.getProgressStatus().observe(this, Observer {
            when(it){
                is Result.Error -> Log.d("FFFF" , it.msg)
                is Result.Success -> Log.d("FFFF" , it.data.toString())
            }
        })
    }

    private fun init() {
        binding.rvPhotos.adapter = PhotosAdapter()
    }
}