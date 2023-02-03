package com.devlee.lunchpicker.presenter.fragment.shake

sealed interface ShakeEvent {
    data class ShakeStart(
        val choiceList: List<String>
    ): ShakeEvent
    object ShakeEnd: ShakeEvent

}