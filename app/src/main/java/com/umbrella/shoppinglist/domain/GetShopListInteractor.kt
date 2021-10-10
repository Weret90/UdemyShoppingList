package com.umbrella.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListInteractor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}