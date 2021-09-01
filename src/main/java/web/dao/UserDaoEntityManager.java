package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoEntityManager implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        System.out.println(entityManager.createQuery("from User", User.class).getResultList());
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void dropById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.username = :name", User.class);
        return query.setParameter( "name", name).getSingleResult();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Role findByIdRole(Long id) {
        return entityManager.find(Role.class, id);
    }

}