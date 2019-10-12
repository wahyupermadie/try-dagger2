package com.wepe.trydagger.utils

data class ErrorHandler(
    val message: String?,
    val errorType: ErrorType
) {
    enum class ErrorType {
        SNACKBAR,
        DIALOG
    }
}