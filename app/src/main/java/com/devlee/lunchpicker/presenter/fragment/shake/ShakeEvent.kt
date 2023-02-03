package com.devlee.lunchpicker.presenter.fragment.shake

sealed interface ShakeEvent {
    object ShakeStart: ShakeEvent
    object ShakeEnd: ShakeEvent

}