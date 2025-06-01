package ru.webrise.error.storage.impl;

import org.springframework.lang.NonNull;
import ru.webrise.error.exception.CommonException;
import ru.webrise.error.model.CustomError;
import ru.webrise.error.constant.Error;
import ru.webrise.error.storage.CustomErrorStorage;

import java.util.HashMap;
import java.util.Map;

public class CustomErrorStorageImpl implements CustomErrorStorage {

    private Map<String, CustomError> errors = new HashMap<>();

    public void put(@NonNull CustomError error) {
        errors.put(error.getCode(), error);
    }

    @Override
    public CustomError getCustomErrorByCode(String code) {
        if (!errors.containsKey(code)) {
            throw new CommonException(Error.CUSTOM_ERROR_BY_CODE_NOT_FOUND_ERROR)
                    .withParameters(code);
        }

        return errors.get(code);
    }

    @Override
    public CustomError getDefaultError() {
        return CustomError.builder()
                .code(Error.INTERNAL_SERVER_ERROR)
                .message("Unexpected error")
                .status(500)
                .build();
    }

    @Override
    public int getSize() {
        return errors.size();
    }
}
