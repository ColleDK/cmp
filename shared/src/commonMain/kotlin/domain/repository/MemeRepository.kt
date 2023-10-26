package domain.repository

import com.colledk.cmp.db.Meme
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    fun getMemes(): Flow<List<Meme>>

    suspend fun updateMemes()
}