package com.example.myshoesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoesapp.model.Product
import com.example.myshoesapp.repository.CategorieRepository
import com.example.myshoesapp.repository.ProdutoRepository
import com.example.myshoesapp.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val produtoRepository: ProdutoRepository,
    private val categorieRepository: CategorieRepository
) : ViewModel() {

    private val _homeUiSate = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiSate.asStateFlow()

    fun intents(intents: HomeIntent) {

        when (intents) {
            HomeIntent.LoadScreen -> {
                loadHomeScreen()
            }
        }

    }

    private fun loadHomeScreen() {

        viewModelScope.launch {
            delay(2000)
            _homeUiSate.update {
                try {
                    HomeUiState.Success(serarchUser(), searchProducts(), searchCategories())

                } catch (e: Exception) {
                    HomeUiState.Error("Algo deu errado, Tente novamente.")
                }
            }
        }
    }
    private fun searchProducts() = produtoRepository.searchProducts()

    private fun serarchUser() = userRepository.searchUser()

    private fun searchCategories() = categorieRepository.searchCategories()

}