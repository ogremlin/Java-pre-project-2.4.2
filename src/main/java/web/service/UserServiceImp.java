package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao dao;

   @Override
   public void add(User user) {
      dao.add(user);
   }

   @Override
   public List<User> listUsers() {
      return dao.listUsers();
   }

   @Override
   public void drop(User user) {
      dao.drop(user);
   }
}
