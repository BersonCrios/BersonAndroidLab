package br.com.bersoncrios.myandroidlab.features.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.bersoncrios.myandroidlab.R
import br.com.bersoncrios.myandroidlab.core.service.ServiceResult
import br.com.bersoncrios.myandroidlab.core.viewmodel.BaseViewModel
import br.com.bersoncrios.myandroidlab.core.viewmodel.ResourceManager
import br.com.bersoncrios.myandroidlab.core.viewmodel.ViewState
import br.com.bersoncrios.myandroidlab.features.starwars.data.People
import br.com.bersoncrios.myandroidlab.features.starwars.data.Result
import br.com.bersoncrios.myandroidlab.features.starwars.repository.StarWarsRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StarWarsViewModel(
        private val starWarsRepository: StarWarsRepository,
        private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _peopleDetail = MutableLiveData<Result>()
    private val _viewStateDetail = MutableLiveData<ViewState>()
    private val _viewState = MutableLiveData<ViewState>()

    private val _peopleList: MutableLiveData<People> by lazy {
        val liveData = MutableLiveData<People>()

        _viewState.value = ViewState.Loading(true)

        viewModelScope.launch {
            val result = withContext(contextPool.IO) {
                starWarsRepository.getPeople()
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
    val viewState: LiveData<ViewState> = _viewState
    val peopleDetail: LiveData<Result> = _peopleDetail
    val viewStateDetail: LiveData<ViewState> = _viewStateDetail


    fun fetchPeople(id: String) {
        _viewStateDetail.value = ViewState.Loading(true)

        viewModelScope.launch {
            val result = withContext(contextPool.Default) {
                findPeople(id)
            }

            _viewStateDetail.value = ViewState.Loading(false)

            if (result != null) {
                _peopleDetail.value = result
            } else {
                _viewStateDetail.value =
                    ViewState.Error(resourceManager.getString(R.string.erro_people_not_found))
            }
        }
    }

    private fun findPeople(id: String): Result? {

        peopleList.value?.results?.forEach{ people ->
            var peopleId: String = people.url.substring(28).replace("/", "")

            if (peopleId == id) {
                return people
            }
        }
        return null
    }
}