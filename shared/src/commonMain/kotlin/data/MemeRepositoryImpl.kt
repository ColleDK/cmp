package data

import com.colledk.cmp.db.Meme
import data.ktor.MemeApi
import data.response.mapToDomain
import domain.repository.MemeRepository
import kotlinx.coroutines.flow.Flow

class MemeRepositoryImpl(
    private val dbHelper: DatabaseHelper,
    private val memeApi: MemeApi
): MemeRepository {
    override fun getMemes(): Flow<List<Meme>> = dbHelper.getAllMemes()

    override suspend fun updateMemes() {
        val memeResult = memeApi.getJsonFromApi()
        if (memeResult.success) {
            val memes = memeResult.data.memes.map { it.mapToDomain() }
            if (memes.isNotEmpty()) {
                dbHelper.insertMemes(memes = memes)
            }
        }
    }
}