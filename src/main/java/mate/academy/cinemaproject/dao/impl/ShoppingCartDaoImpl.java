package mate.academy.cinemaproject.dao.impl;

import mate.academy.cinemaproject.dao.ShoppingCartDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartDaoImpl.class);

    private SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            LOGGER.info("shoppingCart " + shoppingCart.toString() + " insert");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shoppingCart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "FROM ShoppingCart sc "
                            + "LEFT JOIN FETCH sc.tickets Ticket "
                            + "WHERE sc.user.id= :user_id");
            query.setParameter("user_id", user.getId());
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available shoppingCart", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            LOGGER.info("shoppingCart " + shoppingCart.toString() + " update");
            return;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public ShoppingCart findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "From ShoppingCart where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available shoppingCart", e);
        }
    }

}
