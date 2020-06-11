package mate.academy.cinemaproject.dao.impl;

import java.util.List;
import mate.academy.cinemaproject.dao.OrderDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    private SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            LOGGER.info("Order " + order.toString() + " insert");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Order entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "FROM Order o "
                            + "LEFT JOIN FETCH o.tickets "
                            + "WHERE o.user.id= :user_id",Order.class);
            query.setParameter("user_id", user.getId());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available orders", e);
        }
    }

    @Override
    public Order findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "From Order where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available order", e);
        }
    }
}
