// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.advice;

import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpringwolfWebResponseAdvice {

    @ExceptionHandler(AsyncApiGroupNotFoundException.class)
    public ProblemDetail handleAsyncApiGroupNotFound(AsyncApiGroupNotFoundException exception) {
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    }
}
