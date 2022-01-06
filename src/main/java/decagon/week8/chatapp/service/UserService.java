package decagon.week8.chatapp.service;

import decagon.week8.chatapp.model.User;
import decagon.week8.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }
}
