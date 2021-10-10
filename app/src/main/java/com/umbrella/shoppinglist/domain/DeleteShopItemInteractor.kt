package com.umbrella.shoppinglist.domain

class DeleteShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}