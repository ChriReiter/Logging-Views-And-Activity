package at.fh.mappdev.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lesson(
    val id: String,
    val name:String,
    val date:String,
    val topic:String,
    val type:LessonType,
    val lecturers: List<Lecturer>,
    val ratings: MutableList<LessonRating>,
    val imageUrl: String = "") {

    fun ratingAverage(): Double {
        var sum = 0.0;

        ratings.forEach {
            sum += it.ratingValue
        }

        return String.format("%.2f", sum / ratings.count()).toDouble()
    }
}