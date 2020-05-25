package mate.academy.cinemaproject.dao.impl;

import java.util.Optional;
import mate.academy.cinemaproject.dao.UserDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.lib.Dao;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.util.HashUtil;
import mate.academy.cinemaproject.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user.setSalt(HashUtil.getSalt());
            user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
            Long userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            LOGGER.info("User " + user.toString() + " insert");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(
                    "From User where email = :email");
            query.setParameter("email", email);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available user", e);
        }
    }
}
