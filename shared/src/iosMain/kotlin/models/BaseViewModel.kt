package models

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {
    actual val scope = MainScope()

    protected actual open fun onCleared() {}

    fun clear() {
        onCleared()
        scope.cancel()
    }
}