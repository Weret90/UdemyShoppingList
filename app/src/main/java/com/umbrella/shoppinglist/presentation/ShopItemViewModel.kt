package com.umbrella.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umbrella.shoppinglist.data.ShopListRepositoryImpl
import com.umbrella.shoppinglist.domain.AddShopItemInteractor
import com.umbrella.shoppinglist.domain.EditShopItemInteractor
import com.umbrella.shoppinglist.domain.GetShopItemInteractor
import com.umbrella.shoppinglist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val addShopItemInteractor = AddShopItemInteractor(repository)
    private val editShopItemInteractor = EditShopItemInteractor(repository)
    private val getShopItemInteractor = GetShopItemInteractor(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        val shopItem = getShopItemInteractor.getShopItem(shopItemId)
        _shopItem.value = shopItem
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemInteractor.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let { oldShopItem ->
                val newShopItem = oldShopItem.copy(name = name, count = count)
                editShopItemInteractor.editShopItem(newShopItem)
                finishWork()
            }
        }
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}