package com.eu.swipely.viewmodel.action

sealed class UiAction {
    data class Like(val itemPosition: Int)

    data class DisLike(val itemPosition: Int)
}
