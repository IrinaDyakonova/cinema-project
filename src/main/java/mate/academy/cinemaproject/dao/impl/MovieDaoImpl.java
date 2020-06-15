package mate.academy.cinemaproject.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.cinemaproject.dao.MovieDao;
import mate.academy.cinemaproject.exeption.DataProcessingException;
import mate.academy.cinemaproject.model.Movie;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    private SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            LOGGER.info("Movie " + movie.toString() + " insert");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all movies ", e);
        }
    }

    @Override
    public Movie findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery(
                    "From Movie where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available movie", e);
        }
    }
}
