package models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual abstract class BaseViewModel: ViewModel() {
    actual val scope = viewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}