package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;


public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void dropById(Long id);
   User findById(Long id);
   Role findByIdRole(Long id);
   User findByName(String name);
   void updateUser(User user);
}
