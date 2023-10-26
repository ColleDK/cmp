package data.response

import com.colledk.cmp.db.Meme

fun MemeResponse.mapToDomain(): Meme {
    return Meme(
        id = id.toLong(),
        name = name,
        url = url,
        width = width.toLong(),
        height = height.toLong(),
        boxCount = box_count.toLong()
    )
}