package com.jingtian.springrain.ui.home

import androidx.lifecycle.*// ktlint-disable
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.data.source.OperationRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val operationRepository: OperationRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>(false)
    private val _dataLoading = MutableLiveData<Boolean>()
    private val _items: LiveData<List<Operation>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                operationRepository.refresh()
                _dataLoading.value = false
            }
        }
        operationRepository.observeTasks()
    }

    init {
        loadOperations(true)
    }

    fun refresh() {
        _forceUpdate.value = true
    }
    fun loadOperations(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    fun insert(operation: Operation) {
        viewModelScope.launch {
            operationRepository.insert(operation)
        }
    }
    val dataLoading: LiveData<Boolean> = _dataLoading
    val items: LiveData<List<Operation>> = _items
}
