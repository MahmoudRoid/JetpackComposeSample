package mahmoudroid.composebootcamp.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mahmoudroid.composebootcamp.data.ResponseState
import mahmoudroid.composebootcamp.model.Question
import mahmoudroid.composebootcamp.network.QuestionApi
import java.lang.Exception
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
): BaseRepository() {

    suspend fun getAllQuestions(): Flow<ResponseState<Question>> = flow {

        try {
            emit(ResponseState.Loading)
            emit( responseMapper(api.getAllQuestions()) )
        }
        catch (e: Exception){
            emit(ResponseState.Error(e))
        }

    }

}