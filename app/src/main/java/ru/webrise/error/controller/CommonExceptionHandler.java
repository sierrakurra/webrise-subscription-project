package ru.webrise.error.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.webrise.error.config.CustomErrorStorageConfig;
import ru.webrise.error.controller.dto.CustomErrorResponse;
import ru.webrise.error.exception.CommonException;
import ru.webrise.error.model.CustomError;
import ru.webrise.error.storage.CustomErrorStorage;
import ru.webrise.error.utils.ErrorMessageUtils;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private final CustomErrorStorage customErrorStorage;
    private final CustomErrorStorageConfig customErrorStorageConfig;

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CustomErrorResponse> handleCommonException(CommonException e,
                                                                     final ServletWebRequest request) {
        CustomError error = customErrorStorage.getCustomErrorByCode(e.getCode());

        logError(error);
        String message = getMessage(e, error);

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(error.getStatus())
                .message(message)
                .uri(request.getRequest().getRequestURI())
                .build();

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleUnexpectedException(Exception e,
                                                                     final ServletWebRequest request) {
        CustomError error = customErrorStorage.getDefaultError();

        log.error("Unexpected error occurred: {}", error, e);

        CustomErrorResponse response = CustomErrorResponse.builder()
                .code(error.getCode())
                .status(error.getStatus())
                .message(error.getMessage())
                .uri(request.getRequest().getRequestURI())
                .build();

        return ResponseEntity.status(error.getStatus()).body(response);
    }

    private void logError(CustomError customError) {
        log.error("A custom error occurred and resolved: {}", customError);
    }

    private String getMessage(CommonException exception, CustomError error) {
        String message = exception.getMessage() != null ? exception.getMessage() : error.getMessage();
        return ErrorMessageUtils.insertParameters(message, exception.getParams());
    }

}
