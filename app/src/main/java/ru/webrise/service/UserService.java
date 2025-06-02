package ru.webrise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.error.constant.Error;
import ru.webrise.error.exception.CommonException;
import ru.webrise.model.User;
import ru.webrise.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CommonException(Error.USER_NOT_FOUND_ERROR)
                        .withParameters(id));
    }

    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CommonException(Error.USER_WITH_EMAIL_ALREADY_EXISTS_ERROR)
                    .withParameters(user.getEmail());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        User existingUser = getUserById(user.getId());
        existingUser.setSurname(user.getSurname());
        existingUser.setName(user.getName());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
