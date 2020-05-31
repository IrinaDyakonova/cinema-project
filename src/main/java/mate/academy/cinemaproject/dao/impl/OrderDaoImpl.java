package mate.academy.cinemaproject.dao.impl;

import java.util.List;
import mate.academy.cinemaproject.dao.OrderDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.lib.Dao;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery(
                    "FROM Order order "
                            + "LEFT JOIN FETCH order.tickets "
                            + "WHERE order.user.id= :user_id");
            query.setParameter("user_id", user.getId());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available orders", e);
        }
    }
}
