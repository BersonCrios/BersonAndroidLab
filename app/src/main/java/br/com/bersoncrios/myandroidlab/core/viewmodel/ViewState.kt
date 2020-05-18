package br.com.bersoncrios.myandroidlab.core.viewmodel

sealed class ViewState {
    class Error(val error: String) : ViewState()
    class Loading(val loading: Boolean) : ViewState()
}