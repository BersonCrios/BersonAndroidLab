package br.com.bersoncrios.myandroidlab.features.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.bersoncrios.myandroidlab.core.service.ServiceResult
import br.com.bersoncrios.myandroidlab.core.viewmodel.BaseViewModel
import br.com.bersoncrios.myandroidlab.core.viewmodel.ResourceManager
import br.com.bersoncrios.myandroidlab.core.viewmodel.ViewState
import br.com.bersoncrios.myandroidlab.features.starwars.data.People
import br.com.bersoncrios.myandroidlab.features.starwars.repository.StarWarsRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StarWarsViewModel(
        private val starWarsRepository: StarWarsRepository,
        private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _peopleDetail = MutableLiveData<People>()
    private val _viewStateDetail = MutableLiveData<ViewState>()
    private val _viewState = MutableLiveData<ViewState>()

    private val _peopleList: MutableLiveData<People> by lazy {
        val liveData = MutableLiveData<People>()

        _viewState.value = ViewState.Loading(true)

        viewModelScope.launch {
            val result = withContext(contextPool.IO) {
                starWarsRepository.getStarWars()
            }

            _viewState.value = ViewState.Loading(false)

            when (result) {
                is ServiceResult.Success -> {
                    liveData.value = result.data
                }
                is ServiceResult.Error -> {
                    _viewState.value = ViewState.Error(result.exception.message!!)
                }
            }
        }
        return@lazy liveData
    }

    val peopleList: LiveData<People> = _peopleList
    val peopleDetail: LiveData<People> = _peopleDetail
    val viewStateDetail: LiveData<ViewState> = _viewStateDetail
    val viewState: LiveData<ViewState> = _viewState
}