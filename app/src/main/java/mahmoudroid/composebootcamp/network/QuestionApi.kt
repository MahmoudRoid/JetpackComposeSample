package mahmoudroid.composebootcamp.network

import mahmoudroid.composebootcamp.model.Question
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions(): Response<Question>
}