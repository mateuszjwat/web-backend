package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.repositories.FiszkaCardRepository;
import pl.edu.wat.backend.repositories.FiszkaSetRepository;
import pl.edu.wat.backend.repositories.UserRepository;

import java.util.Optional;

import static pl.edu.wat.backend.controllers.LoginController.me;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addNewUser(UserImpl userImpl) {
        Optional<UserImpl> foundByEmail = userRepository.findUserByEmail(userImpl.getEmail());
        Optional<UserImpl> foundByUsername = userRepository.findUserByEmail(userImpl.getUsername());

        if (foundByEmail.isPresent())
            throw new IllegalStateException("that email is taken");
        if (foundByUsername.isPresent())
            throw new IllegalStateException("that username is taken");

        userRepository.save(userImpl);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = userRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists");
        }
        userRepository.deleteById(studentId);
    }

    public Optional<UserImpl> findById(long id) {
        return userRepository.findById(id);
    }

    //????
    public void saveMe() {
        userRepository.save(getMe());
    }

    public UserImpl getMe() {
        return findById(me().getId()).orElseThrow(NullPointerException::new);
    }

    public void save(UserImpl u) {
        userRepository.save(u);
    }
}
