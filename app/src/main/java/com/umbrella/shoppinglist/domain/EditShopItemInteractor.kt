package com.umbrella.shoppinglist.domain

class EditShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}