package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void drop(User user);
    void dropById(Long id);
    User findById(Long id);
    void updateUser(User user);
}
