package com.example.photos.di

import com.example.data.api.NetworkModule
import com.example.data.db.PhotosDatabase
import com.example.data.mappers.PhotoApiResponseMapper
import com.example.data.mappers.PhotoEntityMapper
import com.example.data.repositories.photos.PhotosLocalDataSource
import com.example.data.repositories.photos.PhotosLocalDataSourceImpl
import com.example.data.repositories.photos.PhotosRemoteDataSourceImpl
import com.example.data.repositories.photos.PhotosRepositoryImpl
import com.example.domain.repositories.PhotosRepository
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase
import com.example.photos.ui.photos.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val appModule = module {

    viewModel { PhotosViewModel(get(),get(),get()) }

    factory { GetLocalPhotosUseCase(get()) }
    factory { GetRemotePhotosUseCase(get()) }
    factory { SavePhotosUseCase(get()) }

    factory<PhotosRepository> {
        PhotosRepositoryImpl(get(),get())
    }

    factory {
        PhotosRemoteDataSourceImpl(
            get(),
            get()
        )
    }

    single {
        PhotoApiResponseMapper()
    }

    single {
        NetworkModule().createPhotosApi(BuildConfig.BASE_URL)
    }

    factory<PhotosLocalDataSource> {
        PhotosLocalDataSourceImpl(get() , Dispatchers.IO , get())
    }

    single {
        PhotosDatabase.getDatabase(androidContext()).photoDao()
    }

    single {
        PhotoEntityMapper()
    }
}