package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpEntityManager implements UserService, UserDetailsService {

    private final UserDao dao;

    public UserServiceImpEntityManager(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public List<User> listUsers() {
        return dao.listUsers();
    }

    @Override
    public void dropById(Long id) {
        dao.dropById(id);
    }

    @Override
    public User findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Role findByIdRole(Long id) {
        return dao.findByIdRole(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByName(username);
        System.out.println(user.toString());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return user;
    }
}