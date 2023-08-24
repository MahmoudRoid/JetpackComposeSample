package mahmoudroid.composebootcamp.data


sealed class ResponseState<out T>(
    val data: T? = null,
    val exception: Exception? = null
){
    class Success<T>(data: T? = null): ResponseState<T>(data = data)
    class Error(exception: Exception): ResponseState<Nothing>(exception = exception)
    object Loading: ResponseState<Nothing>()
}