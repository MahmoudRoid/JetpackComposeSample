package mahmoudroid.composebootcamp.repository

import android.util.Log
import mahmoudroid.composebootcamp.data.ResponseState
import retrofit2.Response
import java.lang.Exception
import javax.inject.Singleton

@Singleton
open class BaseRepository {

    protected fun <T> responseMapper(response: Response<T>): ResponseState<T> {
        return if (response.isSuccessful) {
            ResponseState.Success(response.body()!!)
        } else {
            ResponseState.Error(Exception("Error"))
        }
    }

}