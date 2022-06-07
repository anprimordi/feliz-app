package com.feliz.apps.domain.model.common

sealed class Result<T>(
    open val message: String? = null
) {

    fun <S> map(mapper: (t: T) -> S): Result<S> {
        return when (this) {
            is Success -> Success(mapper(data))
            is AuthError -> AuthError()
            is NotFoundError -> NotFoundError()
            is NetworkError -> NetworkError()
            is UnsupportedError -> UnsupportedError(message)
            is Error -> Error(message)
            else -> Loading()
        }
    }

    fun <S> mapTo(mapper: (t: T) -> Result<S>): Result<S> {
        return when (this) {
            is Success -> mapper(data)
            is AuthError -> AuthError()
            is NotFoundError -> NotFoundError()
            is NetworkError -> NetworkError()
            is UnsupportedError -> UnsupportedError(message)
            is Error -> Error(message)
            else -> Loading()
        }
    }

}

class Loading<T> : Result<T>()
class Success<T>(val data: T, message: String? = null) : Result<T>(message)

open class Error<T>(override val message: String) : Result<T>(message) {
    companion object {
        const val GENERAL_ERROR = "Terjadi kesalahan, mohon coba lagi."
        const val GENERAL_SERVER_ERROR = "Server tidak merespon. Mohon coba beberapa saat lagi."

        fun <T> general(t: Throwable): Error<T> = Error(generalMsg(t))
        fun <T> general(message: String): Error<T> = Error(generalMsg(message))
        /*fun <T> http(ex: HttpException): Error<T> =
            if (ex.code() == 401) AuthError() else general(ex)*/

        private fun generalMsg(t: Throwable): String =
            "$GENERAL_ERROR\n(${t.javaClass.simpleName})."
        private fun generalMsg(message: String): String =
            "$GENERAL_ERROR\n($message)."
    }
}

class AuthError<T>(message: String = "Token telah kadaluarsa, mohon login kembali untuk melanjutkan.") : Error<T>(message)
class NotFoundError<T>(message: String = "Tidak ada data yang ditemukan.") : Error<T>(message)
class NetworkError<T> : Error<T>("Tidak ada koneksi internet, mohon coba kembali.")
class UnsupportedError<T>(source: Any) : Error<T>("${source.javaClass.simpleName} doesn't support this feature.")