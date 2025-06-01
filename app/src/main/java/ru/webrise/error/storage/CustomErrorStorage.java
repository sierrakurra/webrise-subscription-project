package ru.webrise.error.storage;

import org.springframework.stereotype.Repository;
import ru.webrise.error.model.CustomError;

@Repository
public interface CustomErrorStorage {

    CustomError getCustomErrorByCode(String code);

    CustomError getDefaultError();

    int getSize();

}
