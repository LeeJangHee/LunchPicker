package com.devlee.launchpicker.presenter.fragment.shake

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devlee.launchpicker.util.Consts.STORE_LIST
import com.devlee.launchpicker.util.Consts.TAG
import com.devlee.launchpicker.util.PreferenceUtil
import com.devlee.launchpicker.util.toPrettyJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ShakeViewModel @Inject constructor(
    @Named(STORE_LIST)
    private val storeList: List<String>,
    private val pref: PreferenceUtil
) : ViewModel() {

    private var _shakeUiState: MutableStateFlow<ShakeUiState> = MutableStateFlow(ShakeUiState.Init)
    val shakeUiState get() = _shakeUiState.asStateFlow()

    private var _shakeRecycleStoreArray: MutableSharedFlow<String> = MutableSharedFlow(extraBufferCapacity = 3)
    val sakeRecycleStoreArray get() = _shakeRecycleStoreArray.asSharedFlow()


    /**
     * Only if you're shaking it true
     * else = false (ex. Shake none or Shake end etc..)
     */
    val isShaking: ObservableBoolean = ObservableBoolean(false)

    private var choiceStoreList: MutableList<String> = mutableListOf()

    init {
        if (choiceStoreList.isNotEmpty()) {
            choiceStoreList.clear()
        }
        choiceStoreList.addAll(storeList)
        choiceStoreList.removeAll(pref.getIgnoreStore())
    }

    fun setShakeCount(count: Int) = viewModelScope.launch {
        try {
            callShaking()
            var index: Int
            repeat(30) {
                index = choiceStoreList.indices.random()
                _shakeRecycleStoreArray.emit(choiceStoreList[index])
                when (it) {
                    in 0..13 -> {
                        delay(40)
                    }
                    in 14..25 -> {
                        delay(100)
                    }
                    else -> {
                        delay(300)
                    }
                }
            }
            _shakeRecycleStoreArray.emit(choiceStoreList[count])
        } finally {
            callShakeComplete(count)
        }
    }

    private fun callShakeStart() = viewModelScope.launch {
        _shakeUiState.emit(ShakeUiState.Shake.Start)
    }

    private fun callShakeEnd() = viewModelScope.launch {
        _shakeUiState.emit(ShakeUiState.Shake.End)
    }

    private fun callShaking() = viewModelScope.launch {
        _shakeUiState.emit(ShakeUiState.Shake.Ing)
    }

    private fun callShakeComplete(count: Int) = viewModelScope.launch {
        Log.d(TAG, "callShakeComplete: ${choiceStoreList[count]}")
        _shakeUiState.emit(ShakeUiState.Shake.Complete(choiceStoreList[count]))
    }

    private fun callShakeIsEmpty() = viewModelScope.launch {
        _shakeUiState.emit(ShakeUiState.EmptyChoiceList)
    }

    private fun callShakeOnceItem(item: String) = viewModelScope.launch {
        _shakeUiState.emit(ShakeUiState.OnceChoiceList(item))
    }

    fun onEvent(event: ShakeEvent) {
        when (event) {
            is ShakeEvent.ShakeStart -> {
                Log.i(TAG, "onEvent: \n${event.choiceList.toPrettyJson()}")
                choiceStoreList = event.choiceList.toMutableList()
                if (choiceStoreList.isEmpty()) {
                    callShakeIsEmpty()
                } else if (choiceStoreList.size == 1) {
                    callShakeOnceItem(choiceStoreList[0])
                } else {
                    callShakeStart()
                }
            }
            is ShakeEvent.ShakeEnd -> {
                callShakeEnd()
            }
        }
    }
}