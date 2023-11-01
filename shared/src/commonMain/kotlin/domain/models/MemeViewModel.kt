package domain.models

import com.colledk.cmp.db.Meme
import domain.repository.MemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MemeViewModel(
    private val memeRepository: MemeRepository
): BaseViewModel() {
    private val _memes = MutableStateFlow<List<Meme>>(emptyList())
    val memes: StateFlow<List<Meme>> = _memes

    fun loadMemes() {
        scope.launch {
            memeRepository.updateMemes()
            observeMemes()
        }
    }

    private fun observeMemes() {
        scope.launch {
            memeRepository.getMemes().collectLatest {
                _memes.value = it
            }
        }
    }
}