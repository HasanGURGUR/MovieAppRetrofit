package hasan.gurgur.movieappexample.model

data class UpcomingResponseModel(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)