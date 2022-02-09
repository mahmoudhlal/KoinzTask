package com.example.photos.ui.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.domain.common.Result
import com.example.photos.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val photosViewModel : PhotosViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photosViewModel.getPhotos().observe(this, Observer {
            if (it != null)
                Log.d("FFFF" , it.toString())
        })

        photosViewModel.getProgressStatus().observe(this, Observer {
            when(it){
                is Result.Error -> Log.d("FFFF" , it.msg)
                is Result.Success -> Log.d("FFFF" , it.data.toString())
            }
        })
    }
}