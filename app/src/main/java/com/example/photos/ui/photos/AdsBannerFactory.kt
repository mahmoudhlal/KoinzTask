package com.example.photos.ui.photos

import androidx.paging.PagedList
import com.example.domain.entities.Photo

object AdsBannerFactory {
    fun generateAds(pagedList : PagedList<Photo>):PagedList<Photo>{
        val newList = pagedList
        pagedList.forEachIndexed { index, photo ->
            if ((index + 1) % 5 == 0)
                newList[index + 1] = Photo("-1", "", "", "", "", "", "")
        }
        return pagedList
    }
}