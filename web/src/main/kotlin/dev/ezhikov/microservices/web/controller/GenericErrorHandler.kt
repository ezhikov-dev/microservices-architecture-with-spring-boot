package dev.ezhikov.microservices.web.controller

import dev.ezhikov.microservices.web.to.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GenericErrorHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleInvalidRequestData(e: Exception): ErrorResponse {
        return ErrorResponse("Error has occur.")
    }
}