package at.fh.mappdev.loggingviewsandactivity

class Lesson(
    val id: String,
    val name:String,
    val date:String,
    val topic:String,
    val type:LessonType,
    val ratings: List<LessonRating>,
    val lecturers: List<Lecturer>) {
    fun ratingAverage(): Double {
        return 0.0;
    }
}