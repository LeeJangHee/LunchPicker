package com.devlee.launchpicker.presenter.fragment.shake

sealed interface ShakeEvent {
    data class ShakeStart(
        val choiceList: List<String>
    ): ShakeEvent
    object ShakeEnd: ShakeEvent

}