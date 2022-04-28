package com.soten.tablepay.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseViewModel : ViewModel() {

    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): T? {
        return try {
            val myObject = viewModelScope.async { callFunction.invoke() }
            myObject.await()
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Log.e(TAG, "Exception: ", e)
                when (e) {
                    is HttpException -> {
                        if (e.code() == 401) {
                            Log.e(TAG, "Exception: ", e)
                        } else {
                            val body = e.response()?.errorBody()
                            Log.e(TAG, "errorBody: $body Exception: ", e)
                        }
                    }
                    is SocketTimeoutException -> Log.e(TAG, "Exception: ", e)
                    is IOException -> Log.e(TAG, "Exception: ", e)
                    else -> Log.e(TAG, "Exception: ", e)
                }
            }
            null
        }
    }

    companion object {
        const val TAG = "safeApiCall"
    }

}