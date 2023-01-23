package com.devlee.launchpicker.presenter.fragment.shake

sealed class ShakeUiState {
    object Init : ShakeUiState()

    sealed class Shake : ShakeUiState() {
        object Start : Shake()
        object Ing : Shake()
        object End : Shake()
        data class Complete(
            val storeText: String
        ): Shake()
    }

    object EmptyChoiceList : Shake()
    data class OnceChoiceList(
        val title: String
    ) : Shake()
}