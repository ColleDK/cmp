package domain.models

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val scope: CoroutineScope
    protected open fun onCleared()
}