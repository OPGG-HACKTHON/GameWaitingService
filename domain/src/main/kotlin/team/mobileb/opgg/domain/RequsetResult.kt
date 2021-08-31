package team.mobileb.opgg.domain

sealed class RequestResult<out T> {
    data class Success<out T>(val response: T) : RequestResult<T>()
    data class Fail(val exception: Exception) : RequestResult<Nothing>()
}

suspend fun <T> RequestResult<T>.doWhen(
    onSuccess: suspend (T) -> Unit,
    onFail: suspend (Exception) -> Unit,
) {
    when (this) {
        is RequestResult.Success -> onSuccess(response)
        is RequestResult.Fail -> onFail(exception)
    }
}
