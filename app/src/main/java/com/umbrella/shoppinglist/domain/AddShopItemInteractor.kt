package com.umbrella.shoppinglist.domain

class AddShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}