package mate.academy.cinemaproject.dao.impl;

import mate.academy.cinemaproject.dao.TicketDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.Ticket;
import mate.academy.cinemaproject.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    private SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            LOGGER.info("Ticket " + ticket.toString() + " insert");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery(
                    "From Ticket where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available ticket", e);
        }
    }
}
