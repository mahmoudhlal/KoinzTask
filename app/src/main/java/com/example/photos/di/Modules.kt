package com.example.photos.di

import com.example.data.api.NetworkModule
import com.example.data.db.PhotosDatabase
import com.example.data.mappers.PhotoApiResponseMapper
import com.example.data.mappers.PhotoEntityMapper
import com.example.data.repositories.photos.*
import com.example.domain.repositories.PhotosRepository
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase
import com.example.photos.ui.photos.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import com.example.photos.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
    viewModel {
        PhotosViewModel(getCashedPhotosUseCase = get(),
            getPhotosUseCase = get(),
            savePhotosUseCase = get()) }
}

val useCasesModule = module {

    single { GetLocalPhotosUseCase(get()) }

    single { GetRemotePhotosUseCase(get()) }

    single { SavePhotosUseCase(get()) }
}

val repositoryModule = module {
    factory<PhotosRepository> {
        PhotosRepositoryImpl(get(),get())
    }

    factory<PhotosRemoteDataSource> {
        PhotosRemoteDataSourceImpl(
            get(),
            get()
        )
    }

    factory<PhotosLocalDataSource> {
        PhotosLocalDataSourceImpl(get() , Dispatchers.IO , get())
    }

}

val mapperModule = module {
    single {
        PhotoApiResponseMapper()
    }


    single {
        PhotoEntityMapper()
    }
}

val networkModule = module {
    single {
        NetworkModule().createPhotosApi(BuildConfig.BASE_URL)
    }

}

val dbModule = module {
    single {
        PhotosDatabase.getDatabase(androidContext()).photoDao()
    }
}