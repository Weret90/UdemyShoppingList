package com.umbrella.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umbrella.shoppinglist.data.ShopListRepositoryImpl
import com.umbrella.shoppinglist.domain.DeleteShopItemInteractor
import com.umbrella.shoppinglist.domain.EditShopItemInteractor
import com.umbrella.shoppinglist.domain.GetShopListInteractor
import com.umbrella.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListIterator = GetShopListInteractor(repository)
    private val deleteShopItemInteractor = DeleteShopItemInteractor(repository)
    private val editShopItemInteractor = EditShopItemInteractor(repository)

    val shopList = repository.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemInteractor.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemInteractor.editShopItem(newShopItem)
    }
}