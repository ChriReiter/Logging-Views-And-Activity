package at.fh.mappdev.loggingviewsandactivity

import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import java.math.RoundingMode

@JsonClass(generateAdapter = true)
class Lesson(
    val id: String,
    val name:String,
    val date:String,
    val topic:String,
    val type:LessonType,
    val lecturers: List<Lecturer>,
    var ratings: MutableList<LessonRating>,
    val imageUrl: String = "") {

    fun ratingAverage(): Double {
        val avg = ratings?.map { it.ratingValue }?.average()?.takeIf { !it.isNaN() } ?: 0.0
        return BigDecimal(avg).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}